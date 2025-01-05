package _6_Recursion;

import java.util.Scanner;

/**
 * anagrams
Anagrams are words or phrases formed by rearranging the letters of another word or phrase, using all the original letters exactly once. For example, "listen" is an anagram of "silent," and "earth" is an anagram of "heart." Anagrams can be used for word play, puzzles, or even as a cryptographic technique.


 */
public class anagrams {
   public static int size;
   public static int count;
   public static char[]arr=new char[100];

   public static void doAnagram(int newSize){
      if (newSize==1) {
         return ;
      }
      for (int i = 0; i < newSize; i++) {
         doAnagram(newSize-1);
         if (newSize==2) {
            displayWord();
         }
         rotate(newSize);
      }
   }
   public static void displayWord(){
      for (int i = 0; i < size; i++) {
         System.out.print(arr[i]);
      }
      System.out.print("-->");
   }
   public static void rotate(int newSize){
      int i;
      int pos=size-newSize;
      char temp=arr[pos];
      for ( i = pos+1; i < size; i++) {
         arr[i-1]=arr[i];
      }
      arr[i-1]=temp;
   }
   public static void main(String[] args) {
      System.out.println("Enter word whose anagrams you need to know:");
      Scanner sc = new Scanner(System.in);
      String s = sc.nextLine();
      sc.close();
      
      size = s.length(); // Update the size variable
      arr = s.toCharArray(); // Populate arr with characters from the input string
      doAnagram(size);
  }
  
}