package adv_java;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE}) // means this annotation can be used only above class
@Retention(RetentionPolicy.RUNTIME) // make the annotation available at runtime---IMP
@interface SuhailAnnotation {
    int nOfTimes() default 1; // default value for annotation---suppose u want that this method runs nOfTimes times
} // syntax for creating an annotation

@SuhailAnnotation
public class _07_annotations {
    public static void main(String[] args) {
        System.out.println("Hello, this is my own annotation");

        // to check if annotation is there for that class
        boolean thtClassHasAnnotation = (new _07_annotations().getClass().isAnnotationPresent(SuhailAnnotation.class));
        System.out.println(thtClassHasAnnotation);
    }
}