package adv_java;

import java.lang.reflect.Field;
import java.util.Arrays;

//u can use reflections ie obj.getClass().[all reflections] to change even the attribute of the class which is final or even final private. U can use it to get attribute names and all details of class a well


public class _06_reflection {
    static  class Sampleclass {
        public int getId() {
            return id;
        }

        final private int id=23;//say we have assigned value of id as 23, we will chnge it though its final private in main class

        String name;
        int salary;

        static void staticMeth() {
        }

        // private void privateMeth() {
        // }

        public void publicMeth() {
        }
    }


    public static void main(String[] args) throws Exception {
       
        // to get all attributes of sample class:
        Sampleclass obj = new _06_reflection.Sampleclass();
        Field[] fields = obj.getClass().getDeclaredFields();
        System.out.println(Arrays.toString(fields));

        //changing id value
        obj.getClass().getDeclaredField("id").setAccessible(true);
        obj.getClass().getDeclaredField("id").set(obj, 45);

        System.out.println(obj.id);

    }
}
