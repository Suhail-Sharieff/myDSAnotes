## LCD displaying a string
```C
#include <LPC214x.h>

// ==========================
// LCD Pin Definitions
// ==========================
#define LCD_D4   (1 << 10)     // P0.10
#define LCD_D5   (1 << 11)     // P0.11
#define LCD_D6   (1 << 12)     // P0.12
#define LCD_D7   (1 << 13)     // P0.13
#define LCD_EN   (1 << 20)     // P0.20 (Enable)
#define LCD_RS   (1 << 22)     // P0.22 (Register Select)

#define LCD_DATA_MASK  (LCD_D4 | LCD_D5 | LCD_D6 | LCD_D7)  // Mask for data lines
#define LCD_CTRL_MASK  (LCD_EN | LCD_RS)                    // Mask for control lines
#define LCD_ALL_MASK   (LCD_DATA_MASK | LCD_CTRL_MASK)      // All LCD lines

// ==========================
// Function Prototypes
// ==========================
void lcd_cmd(unsigned char cmd);
void lcd_data(unsigned char data);
void delay(int count);

// ==========================
// Main Function
// ==========================
int main(void)
{
    int i;
    unsigned char name[] = "ARM_LPC2148";

    IO0DIR = LCD_ALL_MASK;     // Set LCD pins as output
    delay(100);

    // LCD Initialization Sequence (4-bit mode)
    lcd_cmd(0x02);     // Cursor home
    lcd_cmd(0x01);     // Clear display
    lcd_cmd(0x28);     // 4-bit, 2 lines, 5x7 dots
    lcd_cmd(0x06);     // Entry mode: Increment cursor
    lcd_cmd(0x0C);     // Display ON, cursor OFF

    // Print the string on LCD
    for (i = 0; name[i] != '\0'; i++)
    {
        lcd_data(name[i]);
    }

    while (1);  // Infinite loop to keep the display
}

// ==========================
// Send Command to LCD
// ==========================
void lcd_cmd(unsigned char cmd)
{
    unsigned int temp;

    // ----- Send HIGH nibble -----
    temp = (cmd & 0xF0) << 2;        // Mask high nibble, shift to match P0.10–P0.13
    IO0CLR = LCD_ALL_MASK;           // Clear all related pins
    IO0SET = temp;                   // Set data pins
    IO0CLR = LCD_RS;                 // RS = 0 (command)
    IO0SET = LCD_EN;                 // EN = 1
    delay(500);
    IO0CLR = LCD_EN;                 // EN = 0

    delay(500);

    // ----- Send LOW nibble -----
    temp = (cmd & 0x0F) << 6;        // Shift lower nibble into correct place
    IO0CLR = LCD_ALL_MASK;
    IO0SET = temp;
    IO0CLR = LCD_RS;
    IO0SET = LCD_EN;
    delay(500);
    IO0CLR = LCD_EN;

    delay(500);
}

// ==========================
// Send Data (Character) to LCD
// ==========================
void lcd_data(unsigned char data)
{
    unsigned int temp;

    // ----- Send HIGH nibble -----
    temp = (data & 0xF0) << 2;
    IO0CLR = LCD_ALL_MASK;
    IO0SET = temp;
    IO0SET = LCD_RS;                // RS = 1 (data)
    IO0SET = LCD_EN;
    delay(500);
    IO0CLR = LCD_EN;

    delay(500);

    // ----- Send LOW nibble -----
    temp = (data & 0x0F) << 6;
    IO0CLR = LCD_ALL_MASK;
    IO0SET = temp;
    IO0SET = LCD_RS;
    IO0SET = LCD_EN;
    delay(500);
    IO0CLR = LCD_EN;

    delay(500);
}

// ==========================
// Simple Software Delay
// ==========================
void delay(int count)
{
    int i, j;
    for (i = 0; i < count; i++)
        for (j = 0; j < 600; j++);
}


```

## Stepper Motor Rotation Anticlockwise
```C
#include <LPC214x.h>

// Simple software delay function
void delay()
{
    int i, j;
    for(i = 0; i < 0xFF; i++)
        for(j = 0; j < 0x25; j++);
}

int main()
{
    // Set P0.16 to P0.19 as output (for 4 control lines)
    IO0DIR = 0x000F0000;  // Bits 16–19 = 1 → output

    while (1)
    {
        // Rotate one output HIGH at a time (anticlockwise)
        IO0CLR = 0x000F0000;      // Clear P0.16–P0.19
        IO0SET = 0x00080000;      // Set P0.19 HIGH
        delay();

        IO0CLR = 0x000F0000;
        IO0SET = 0x00040000;      // Set P0.18 HIGH
        delay();

        IO0CLR = 0x000F0000;
        IO0SET = 0x00020000;      // Set P0.17 HIGH
        delay();

        IO0CLR = 0x000F0000;
        IO0SET = 0x00010000;      // Set P0.16 HIGH
        delay();
    }
}

```

## & segment display
```C
#include <LPC214x.h>

// Hex digit to 7-segment encoding (assuming common cathode)
// Mapping: a b c d e f g -> P0.2 to P0.8
unsigned int hex_digits[16] = {
    0x0000063F, // 0
    0x00000606, // 1
    0x0000065B, // 2
    0x0000064F, // 3
    0x00000666, // 4
    0x0000066D, // 5
    0x0000067D, // 6
    0x00000607, // 7
    0x0000067F, // 8
    0x0000066F, // 9
    0x00000677, // A
    0x0000067C, // b
    0x00000639, // C
    0x0000065E, // d
    0x00000679, // E
    0x00000671  // F
};

void delay_led(unsigned long int count);

int main(void)
{
    IO0DIR = 0x00000FFC; // Set P0.2 to P0.11 as output

    while(1)
    {
        for (int i = 0; i < 16; i++)  // Loop through 0 to F
        {
            IO0CLR = 0x00000FFF;           // Clear all 7-segment bits
            IO0SET = hex_digits[i];        // Display the current digit
            delay_led(150000);             // Small delay
        }
    }
}

// Simple software delay
void delay_led(unsigned long int count)
{
    while(count--);
}

```

## Buzzer with ARM LPC2148
```C
#include <LPC214x.h>

#define BUZZER_PIN (1 << 24)   // P1.24
#define BUTTON_PIN (1 << 15)   // P0.15

void delay(unsigned long int count);

int main(void)
{
    IO1DIR |= BUZZER_PIN;     // Set P1.24 as output
    IO0DIR &= ~BUTTON_PIN;    // Set P0.15 as input (button)

    while (1)
    {
        // Check if button is pressed
        if (IO0PIN & BUTTON_PIN)
        {
            // Wait until button is released (debounce)
            while (IO0PIN & BUTTON_PIN);

            // Buzz ON
            IO1SET = BUZZER_PIN;
            delay(50000);  // Adjust as needed

            // Buzz OFF
            IO1CLR = BUZZER_PIN;
            delay(50000);  // Adjust as needed
        }
    }
}

// Simple software delay
void delay(unsigned long int count)
{
    while (count--) { }
}

```

## Moving LCD
```C
#include <LPC214x.h>

#define RS (1 << 20)
#define EN (1 << 22)
#define LCD_DATA_MASK (0x00003C00)  // P0.10–P0.13

void lcd_cmd(unsigned char cmd);
void lcd_data(unsigned char data);
void lcd_delay(int count);

int main()
{
    int i;
    unsigned char name[] = "Well Come to LPC 2148";

    // Set LCD pins as output (P0.10–P0.13, P0.20, P0.22)
    IO0DIR = LCD_DATA_MASK | RS | EN;

    lcd_cmd(0x02);     // Cursor home
    lcd_delay(10000);
    lcd_cmd(0x01);     // Clear display
    lcd_delay(10000);
    lcd_cmd(0x8F);     // Start from far right (15th position)

    for (i = 0; name[i] != '\0'; i++)
    {
        lcd_data(name[i]);   // Display character
        lcd_cmd(0x18);       // Shift display left
        lcd_delay(100000);   // Delay for visible effect
    }

    while (1);
}

// ========== Send Command ==========
void lcd_cmd(unsigned char cmd)
{
    int temp;

    // High nibble
    temp = (cmd & 0xF0) << 2;
    IO0CLR = LCD_DATA_MASK;
    IO0CLR = RS;     // RS = 0 for command
    IO0SET = temp;
    IO0SET = EN;
    lcd_delay(500);
    IO0CLR = EN;

    // Low nibble
    temp = (cmd << 4 & 0xF0) << 2;
    IO0CLR = LCD_DATA_MASK;
    IO0CLR = RS;
    IO0SET = temp;
    IO0SET = EN;
    lcd_delay(500);
    IO0CLR = EN;
}

// ========== Send Data ==========
void lcd_data(unsigned char data)
{
    int temp;

    // High nibble
    temp = (data & 0xF0) << 2;
    IO0CLR = LCD_DATA_MASK;
    IO0SET = RS;     // RS = 1 for data
    IO0SET = temp;
    IO0SET = EN;
    lcd_delay(500);
    IO0CLR = EN;

    // Low nibble
    temp = (data << 4 & 0xF0) << 2;
    IO0CLR = LCD_DATA_MASK;
    IO0SET = RS;
    IO0SET = temp;
    IO0SET = EN;
    lcd_delay(500);
    IO0CLR = EN;
}

// ========== Delay ==========
void lcd_delay(int count)
{
    int i, j;
    for (i = 0; i < count; i++)
        for (j = 0; j < 35; j++);
}

```


## LCD hex counter
```c
#include <LPC214x.h>

void delay();

int main(void)
{
    unsigned int num = 0x00;         // Counter: 0x00 to 0x0F
    unsigned int val;

    IO1DIR = 0x00FF0000;             // Set P1.16 to P1.23 as output

    while(1)
    {
        val = (num << 16);           // Shift num to align with P1.16
        IO1CLR = 0x00FF0000;         // Clear previous value
        IO1SET = val;                // Output current value

        num++;                       // Increment counter

        if(num > 0x0F)
            num = 0x00;              // Wrap around after 0x0F

        delay();                     // Simple software delay
    }
}

// Simple delay function
void delay()
{
    unsigned int i, j;
    for(i = 0; i < 0x700; i++)
        for(j = 0; j < 0x70; j++);
}

```