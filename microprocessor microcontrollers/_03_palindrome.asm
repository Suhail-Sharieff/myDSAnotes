.MODEL   SMALL 
.DATA
       MYSTR DB  "ALA"
       LEN   EQU $-MYSTR
       RSTR  DB  10 DUP('$')
       MSG   DB  "Reverse string is :$"
       MSG1  DB  13,10,"String is Palindrome $"
       MSG2  DB  13,10,"String is Not Palindrome $"
 
.CODE
MAIN PROC
              MOV  AX,@DATA
              MOV  DS,AX
              MOV  ES,AX
 
              MOV  SI,OFFSET MYSTR        ; SI points to MYSTR

              MOV  DI,OFFSET RSTR         ; DI points to RSTR
              ADD  DI,LEN-1        ; adds DI and LEN-1

              MOV  CX,LEN          ; moves LEN to CX
                   
       RPT:   MOV  BL,[SI]         ;moves the character pointed by SI to AL
              MOV  [DI],BL         ;moves AL value to location pointed by DI
              INC  SI              ; increment SI to point to next character
              DEC  DI              ; decrement DI to move next character
              LOOP RPT
       ; jumps back to RPT if CX≠0
             
              LEA  DX,MSG
              MOV  AH,09H
              INT  21H
           
              LEA  DX,RSTR
              MOV  AH,09H
              INT  21H
           
              LEA  SI,MYSTR
              LEA  DI,RSTR
              MOV  CX,LEN

              REPE CMPSB
              JNE  NOTPAL
           
              LEA  DX,MSG1
              MOV  AH,09H
              INT  21H
           
              JMP  EXIT
      
       NOTPAL:
              LEA  DX,MSG2         
              MOV  AH,09H
              INT  21H
           
       EXIT:  MOV  AH,4CH
              INT  21H
MAIN ENDP
END MAIN