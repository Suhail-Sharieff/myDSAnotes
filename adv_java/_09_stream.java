package adv_java;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



//stream is a sequence of elements supporting sequential and parallel aggregate operations. iTs like a pipeline of data wehrer we can perform operations on data intermediately and get the final result

public class _09_stream {

    public static void main(String[] args) {
        List<Integer>li=List.of(1,2,3,4,5,6,7,8,9,10);
        int sumOfAllNums=li.stream().mapToInt(i->i).sum();//exaplanation: 1. convert list to stream 2. map each element to int 3. sum all elements

        System.out.println("Sum of all numbers: "+sumOfAllNums);


        Stream<Integer>st=Stream.iterate(2, (i)->i+2).limit(10);
        st.forEach((e)->System.out.println(e+"->"));//explanation: 1. create a stream of 10 elements starting from 2 and incrementing by 2 2. print all elements


        Stream.of("SUHAIL","APPLE","ZEBRA","BANANA","KITE").filter((e)->e.length()%2==0).collect(Collectors.toList()).forEach((e)->System.out.println(e));//[-10, -8, -6, -4, -2]


        //mapping example, map each element to lowercase
        Stream.of("A","B","C","D","E").map((e)->e.toLowerCase()).forEach((e)->System.out.println(e));

        //flatmap example, map each element to a stream and then flatmap to a single stream
        //i want all lists to be converted toa single list
        Stream.of(Arrays.asList(Arrays.asList(1,2,3,4),Arrays.asList(5,6,7,8),Arrays.asList(9,10,11,12))).flatMap((l)->l.stream().map((e)->e.reversed())).forEach((e)->System.out.print(e));



        List<Integer>nums=Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Stream<Integer>st2=nums.stream()
                          .filter((e)->e%2==0).peek((val)->System.out.println("Even numbers:"+val))
                          .map((e)->e*-1).peek((val)->System.out.println("Negative even numbers:"+val))
                          .sorted().peek((val)->System.out.println("Sorted negative even numbers:"+val));
       System.out.println(st2.collect(Collectors.toList()));




       // Parallel multitasking:
       long start = System.currentTimeMillis();
       System.out.print("Sequential processing: ");
       Stream.of(1, 2, 3, 4, 5).map((e) -> e * 2).forEach((val) -> System.out.print(val + "->"));
       long end = System.currentTimeMillis();
       System.out.println("\nTime taken for sequential processing: " + (end - start) + " ms");

       start = System.currentTimeMillis();
       System.out.print("Parallel processing: ");
       Arrays.asList(1, 2, 3, 4, 5).parallelStream().map((e) -> e + 2).forEach((val) -> System.out.print(val + "->"));
       end = System.currentTimeMillis();
       System.out.println("\nTime taken for parallel processing: " + (end - start) + " ms");
    }
}