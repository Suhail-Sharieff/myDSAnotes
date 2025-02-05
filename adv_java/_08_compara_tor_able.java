package adv_java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class _08_compara_tor_able {
    static 
    class Way1 {
        String name;
        int age;
    
        public Way1(String name, int age) {
            this.name = name;
            this.age = age;
        }
    
        @Override
        public String toString() {
            return "Way1 [name=" + name + ", age=" + age + "]";
        }
    }

    public static void main(String[] args) {
        List<Way1> Way1s = new ArrayList<>();
        Way1s.add(new Way1("A", 100));
        Way1s.add(new Way1("B", 200));
        Way1s.add(new Way1("C", 300));
        // now we want to sort the Way1s based on their age
        // we can use comparable interface
        // using Comparator:
        Collections.sort(Way1s, new Comparator<Way1>() {
            @Override
            public int compare(Way1 o1, Way1 o2) {
                return o1.age - o2.age;
            }
        });

        System.out.println(Way1s);

        //using lamba expression
        Collections.sort(Way1s, (o1, o2) -> o1.age - o2.age);

        List<Way2> Way2s = new ArrayList<>();
        Way2s.add(new Way2("A", 100));
        Way2s.add(new Way2("B", 200));
        Way2s.add(new Way2("C", 300));
        Collections.sort(Way2s);
        System.out.println(Way2s);
        
    }

    static      //using comparable interface
    class Way2 implements Comparable<Way2> {
       String name;
       int age;
   
       public Way2(String name, int age) {
           this.name = name;
           this.age = age;
       }
   
       @Override
       public String toString() {
           return "Way2 [name=" + name + ", age=" + age + "]";
       }
   
       @Override//nwo in main method we can use Collections.sort(Way2s);
       public int compareTo(Way2 o) {
           return this.age - o.age;
       }
   }
}


