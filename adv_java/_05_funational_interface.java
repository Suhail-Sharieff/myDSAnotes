package adv_java;

import java.util.function.Function;
import java.util.function.Predicate;

//it means an interface with a single abstrct method

//example:
interface NoramalInterface {//has 2 abstract methods, and any other type methods vald
    void meth1();//as same as void meth1() coz by default interface methods r abstct    
    void meth2();
    static void meth3(){};
}

//functional interface
@FunctionalInterface
interface FuncInter{
    void meth1();
    // void meth2();//invalid,coz in functional interface we can have meax 1 abstract method, we alreaddy ahve meth1
    static void meth3(){}
}


//how its helpful?
class Way1 implements FuncInter{

    @Override
    public void meth1() {
       System.out.println("This is meth1");
    }
    //now in main methods create obj and access like obj.meth1()
}

//but whenver u have a functional interface, u can directly use lambda expression to call the only abstratc metho it has instaed of creating a class wich extends it and then calling it like way1
//ex: direct way
public class _05_funational_interface {

    public static void main(String[] args) {
        FuncInter obj=()->{};

        obj.meth1();

        // //this accepts T returns R
        Function<Integer,String>i_to_s=(Integer i)->{
            return Integer.toString(i);
        };

        System.out.println(i_to_s.apply(34));

        //this accepts T  always returns bool
        Predicate<Integer> is_even=(Integer e)->{return e%2==0;};
        System.out.println(is_even.test(44));

    }
}

//ex for functional interface: callable, Consumer,Supplier etc
//https://www.youtube.com/watch?v=YXRBBOjgrFU&list=PL6W8uoQQ2c63f469AyV78np0rbxRFppkx&index=17&ab_channel=Concept%26%26Coding-byShrayansh