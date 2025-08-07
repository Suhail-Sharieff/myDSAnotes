# Some genrerally asked Interview Questions

### ❓ Define OOPs

<details>
<summary>Answer</summary>

- OOPs is a way of representing a real world entity into code
- We can represent and define any real world entity using classes and objects as provided by OOP

</details>

---

### ❓ Compile VS Run time

<details>
<summary>Answer</summary>

- Compile time is the phase where the src code is converted into the machine code before execution
- Run time is the phase when the compiled code is actually executed

</details>

---

### ❓ Define Class

<details>
<summary>Answer</summary>

Class is a collection of datatypes and methods/functions. Class is a logical contruct whereas object is a physical existence

</details>

---

### ❓ Explain Object and its Life

<details>
<summary>Answer</summary>

- Object is an instance of a class that actually is stored in the memory
- Variables of an object are called instace variables
- Object is created using `new` keyword

```java
Student st;//just a reference of type Student stored in Stack that points to null at compile time
st=new Student()//allocate memory at runn time in Heap
```

- Attributes of a object can be accessed using `.` operator
- `new` keyword `dynamically` allocates memory to this pointer in `heap`
- Refernce is created at `compile time` and memory alloc happens at `run time`
- Exmaple

```java
Student x=new Student();
Student y=x;
//now y also points to x
```

- In the above example, any change using y will affect x as well
- Therefore objects are passed by reference to the
</details>

---

### ❓ Define Constructor

<details>
<summary>Answer</summary>

- Constructor is a special method that is used to init the attributes of a class during `run time` using the arguments passed into it
- Every class will have a defualt contructor even if not declared, default contructor would initalize all the attributes to their default value
- Defualt value for primitive datatypes like `int is 0`,`boolean is false`, for non-primitive its `null`
- Constructors can `never` be declared static, coz anything decalred as static can never access the other non-static attributes, if we make constructor static, how on earth can u access the most other attributes that are non static?, ie te main reason of contructor(to initilize attributes) itself is destroyed
- Contructor can be polymorphised as well, ie a class can have multiple constructors behaving differently

</details>

---

### ❓ Explain 'this' keyword

<details>
<summary>Answer</summary>

- `this` keyword is used to reder to the attributes of the current class
- Example:

```java
String name;//some class attribute
void someMeth(String name){
    this.name=name;
    //left side is of the class
    //right side is of the argument passed
}
```

- `this` can also be used to call constructor from another constructor, exmaple

```java
int x;
Student(x){//constructor 1
    this.x=x;
}
Student(){//constructor 2
    this(23);//now x is initialized as 23
}
```

</details>

---

### ❓ Explain 'final' keyword and 'immutability'

<details>
<summary>Answer</summary>

- varibales declared as final will always be immutable
- Immutable means the referece can never be changed, but value can be changed
- Example:

```java
final Student x=new Student(name:"ABC")
x.name="XYZ";//allowed
x=new Student("XYZ")//NOT allowed, sice u are trying to change reference here
```

</details>

---

### ❓ Explain 'finalize()' method

<details>
<summary>Answer</summary>

- `finalize()` method is called by the JVM just before the `garbage collector` destroyes/deacclocates the memory assigned to that object
- Its a way of telling java whether to perform some action just before destroying that object

```java
//for example u want to print bye before garbage collector destroyes object
protected void finalize(){
    print("Hello world");
}
```

- Any object of a class CANNOT call finalize method using `.` operator, coz this will be compltely handled by JVM

```java
obj.finalize();//INVALID
```

</details>

---

### ❓ Deine package

<details>
<summary>Answer</summary>

- Packages are containers containing one or more classes
- They help compartalize group of class
- In genral it just means parent folder of the class
- `Importance of packages`: usecase is even we want to create 2 or more classes of same name exactly, solution is to just create 1 folder/package for each and add them there

</details>

---

### ❓ Explain 'static' keyword

<details>
<summary>Answer</summary>

- Static keyword is used for those varibale that belongs to a class rather than to an object
- Any attribute declared as static can be accessed directly using `ClassName.staticMemberName` without us having to creat an object of that class
- Whenevr we want to access some attribute or aa function without creating an object of its parent class
- Example:

```java
class Student{
   String name;
   static int cnt;
   Student(String name){
       this.name=name;
       this.cnt++;//(or) Student.cnt++;
   }
}
main(){
   Student x=new Student("ABC");
   Student y=new Student("XYZ");
   print(Student.cnt);//prints 2
}
```

- A non-static method can never access a static method, but vice versa is not true
- There is no way in java to call a non-static context without having an object
- An object can also call static context
- Suppose u want to initialize the static variables, u can do it using `static block`, initialzed during `compile time`

```java
class A{
    static int x;
    static{
        x=23;//as soon as the class the class is loaded for first time its initilized,this happens only first time
    }
}

```

- A class can have many static inner classes, but its not possible to directly have a outer static class, coz then to whom will it belong then?
</details>

---

### ❓ Why is main method static

<details>
<summary>Answer</summary>

- This is so that JVM dont have to create an object of class that we want to run, if main is declared as static, JVM would directly call `ClassName.main()` and directly run the program

</details>

---

### ❓ Explain Singleton Class and its Uses

<details>
<summary>Answer</summary>

- Whenver we want that all the objects of some class `X` share the same instance rather than each of its own, we make use of Singleton class, we do it by creating static method `getInstance()` that objects could access, the class itself provides its own instance (which is created only once and common for all objects) via this method
- Demonstration:

```java
class MyClass{//singleton class->provides its own instance instantiated only once common to all its objects created
    private static MyClass instance;
    static MyClass getInstance(){
        if(instance==null) instance=new MyClass();
        return instance;
    }
}
class Main{
    main(){
        MyClass x=MyClass.getInstance();
        MyClass y=MyClass.getInstance();
        //both x and y refer to same instance commonly provided by MyClass
    }
}
```

</details>

---

## Inheritance

### ❓ Explain Inheritance

<details>
<summary>Answer</summary>

- inheritance is one of the principles of object oriented programming that allows a `child class` to inherit the public properties of its `parent class`
- `extends` keyword is used to implemnt inheritance

```java
class Parent{
    int x;
    private int y;//not accesible to child class
    void meth1(){...}
    private void meth2(){...}
}
class Child extends Parent{
    int y;
    void meth2(){
        meth1();//meth1 is accessile here
        print(x);//x is also accessible here
        print(y);//INVALID
        meth2();//INVALID
    }
}
```

</details>

---

### ❓ Explain 'super' keyword

<details>
<summary>Answer</summary>

- `Super` keyword is used in a child class to access public attributes and methods of its parent class
- Example

```java
class A{
    int x,y,z;
    private int p;
    public A(int x,y,z){
        //initialize x,y,z
    }
}
class B extends A{
    int k;
    public B(int x,int y,int z,int k){
        super(x,y,z);//super will initialize the superclass's attributes
        this.k=k;
        super.x;//valid as well
        super.y;//INVALID
    }
}
```

</details>

---

### ❓ What determines the accesibility of attributes of parent class? reference or object?

<details>
<summary>Answer</summary>

- Its the `reference`, that determines what can be accessed from parent class
- Example:

```java
class Parent{
    int x;
    int y;
}
class Child extends Parent{
    int z;
}
class Main{
    psvm(){
        //Type1: Reference to Parent, but object of Child
        Parent obj=new Child();
        obj.z;//VALID
        obj.x;//INVALID
        obj.y;//INVALID too

        Child obj=new Parent();//INVALID

        //Type2: Both reference and object of type Child class
        Child obj=new Child();
        obj.x;//VALID
        obj.y;//VALID
        obj.z;//VALID
    }
}
```

</details>

---

### ❓ Does the constructor of child class automatically calls the constructor of parent class or should we have to do it?

<details>
<summary>Answer</summary>

- Every child class constructor automatically calls the constructor of its parent class even if we dont call `super(...)` in the constructor of child class

```java
class A {
    int x;
    private int y;
    A(){
        System.out.println("Parent class");
    }

}
class B extends A {

    B(){
        System.out.println("Child class");
    }
}
class Main{
    psvm(){
        B obj=new B();
    }
}

//output:
//Parent Class
//Child Class
```

</details>

---

### ❓ Explain types of inheritance

<details>
<summary>Answer</summary>

<details>
<summary>Single Level</summary>

- A class extends single class

```java
class A{...}
class B extends A{...}
```

</details>

<details>
<summary>Multiple Inheritance</summary>

- A class extends more than one class, `THIS IS NOT ALLOWED IN JAVA`
- This is becoz say `A` extends `B` and `C`, lets say both `B` an `C` has some variable `x`, when u try accessing this x in `A`, JVM is confused whether to take `x` from `A` or from `B`, hence its implemented using `interfaces` in java



</details>

<details>
<summary>Herirarchial Level</summary>

- A class is inherited by many classes

```java
class A{...}
//all below classes xtends A
class B extends A{...}
class C extends A{...}
class D extends A{...}
class E extends A{...}
```

</details>
<details>
<summary>Hybrid Inheritance</summary>

- Its a combination of single and multiple `NOT ALLOWED IN JAVA`
- Looks like ring structure

</details>
</details>

---
### ❓ Explain Abstract keyword/ How to acheieve Multiple inheritance in Java
<details>
<summary>Answer</summary>

- Abstract keyword is used to achieve multiple inheritance
- Whenver we create an abstract class, its methods `dont` have any implementation and just the declaration.
- Now if any class extends an abstract class u need to implement those methods in abstract class
- Abstract class can have both abstract and non-abstract methods
- We `can` create constructor of abstract class, but abstract constructor is not possible
- Instance of abstract class cannot be created since some methods in it will not have implementation
- `Static` methods can also be created in abstract classes
- Only those methods which are abstract needs to be overrriden and implemented in child class
```java
abstract class Vehicle{
    abstract void horn();//no body, so needs to be implemented/overriden in child class
    void meth(){...}//some non-sbastract method with body
}
class CarA extends Vehicle{
    @Override
    void horn(){...}
}
class CarB extends Vehicle{
    @Override
    void horn(){...}
}
```

</details>

---

### ❓ Explain Interfaces in Java
<details>
<summary>Answer</summary>

- Interfaces is like abstract class itself, but unlike abstract class which can also have non-abstract methods,interface `cannot ` have non-asbstract methods
- Every method in interface is abstract, ie no body
- Object of interface cant be created
- A single class can extend one abstract class or a normal class, but a single class can implement many interfaces, so we can achive multiple inheritance
- A class implementing interface must imlement all methods of interface
- Attributes are static and final in interface since objects of interface also cant be made
```java
public interface Engine{
    void on();
    void off();
}
public interface Song(){
    void start();
    void stop();
}
public class Vehicle implements Engine,Song{//multiple inheritance
    //Engine methods
    @Override
    public void on(){...}
    public void off(){...}
    //Song methods
    public void start(){...}
    public void stop(){...}
}

```

- Static methods of interface must have a body snce they belong to them and never others

</details>


---
### ❓ Why interfaces are not preferred in performance heavy tasks
<details>
<summary>Answer</summary>

- Because its methods are implemented in `run time` and not `static` time, burdens the performance

</details>

---

### ❓ Can interface extend interface
<details>
<summary>Answer</summary>

- Yes, but then the class implementing the child interface must implement the methods of both interfaces

</details>

---

### ❓ Can interface methods have some default implementation
<details>
<summary>Answer</summary>

- From Jav8, Yes using `default` keyword

```java
//lets say VehicleA and VehicleB horns same
public interface Sound{
    default void horn(){...}//has body
}

class VehicleA implements Sound{
    //no need of immplemnting body of horn
}
class VehicleB implements Sound{
    //no need of immplemnting body of horn
}
```
- The reason to do this was lets say we have some interface `X`, say some classes `A` and `B` implements `X`, lets say the behavior of the method in interface is same in both `A` and `B`, but still we need to implement it separarately in `A` and `B` because classes implementing interface must implement its methods, so its basically like implementing same method of `X` repeatedly in both `A` and `B`, so by making that method default in `X`, we dont neccarily implement in `A` and `B`
</details>

---




### ❓ Differences between Abstract class and interface
<details>
<summary>Answer</summary>

- Any class should `extend` abstract class and `implement` interface
- Abstract class can have both abstract and non-abstract methods
- All methods of interface are abstract by default
- Atrributes of abstract class can be of any type,but of interface are static and final


</details>

---



## Polymorphism
### ❓ Whats Polymorphism
<details>
<summary>Answer</summary>

- Poly(many)+morphism(ways to represent)
- Example:
```java
class Vehicle{
    void horn(){...AAA...}
    void horn(int x){...ZZZ...}//static polyorphism
}
class Mercedes extends Vehicle{
    @Override
    void horn(){...BBB...}//dynamic polymorphism
}
class Audi extends Vehicle{
    @Override
    void horn(){...CCC...}
}
```
- Observe that `horn()` method behaves differently in each of child classes
</details>

---

### ❓ What are types of Polymorphism
<details>
<summary>Compile Time / Static Polymorphism</summary>

- Acheived via `method overloading and also constructor overloading`
- All this happens while compile time itself
```java
class A{
    //methods of same name but different type/number of params
    void meth(int x){...}
    void meth(int x,int y){...}
    void meth(int x,int y,int z){...}
}
```
</details>
<details>
<summary>Run Time / Dynamic Polymorphism</summary>

- Acheived via `method overriding(NOT OVERLOADING)`
- Exmaple:
```java
class Vehicle{
    void horn(){...}
}
class Audi extends Vehicle{
    @Override
    void horn(){...}
}
class Mercedes extends Vehicle{
    @Override
    void horn(){...}
}
```

</details>

---
### ❓ Inheritance and polymorphism miscellanous Question
<details>
<summary>Answer</summary>

```java
class Parent{
    int x;
    void meth();
}
class Child extends Parent{
    int y;
    @Override
    void meth();
}
```
- Now from the above code obseve following things
```java
    //Reference to parent and object of type child
    Parent obj1=new Child();
    //both ref to child
    Child obj2=new Child();

    obj1.x;//INVALID
    obj1.y;//VALID

    //IMP:now observe below
    obj1.meth();//though it refers to parent class,still the meth implementation of CHILD class will be executed
    obj2.meth();//child class impl will be mplemented normally
```
- Observe that whenever we take reference of parent class, but create object of child class, keep 2 things in mind: (1) We cannot access the attributes of parent class (2) When child class overrides the method of parent class,though the reference is of parent, still the implementation of child class is picked up using `Dynamic Method dispatch` and `Upcasting  `, since these things happend during rntime, its callled `Late Binding`

</details>

---


### ❓ How do i prevent child classes to overide some method inherited from parent class
<details>
<summary>Answer</summary>

Just make them final

```java
class Parent{
    final void meth(){...}
}
class Child extends Parent{
    @Overrride
    void meth(){...}//INVALID since its final
}
```
- Similarly we can prevrent a class from getting extended by making a class final
```java
final class A{...} 
class B extends A{...}//INVALID
```
</details>

---
### ❓ Is it possible to overide static methods of parent class too?
<details>
<summary>Answer</summary>

- Not possible

```java
class Parent{
    static void meth(){
        print("Parent");
    }
}
class Child extends Parent{
    //VALID WITHOUT `@Override`
    static void meth(){
        print("Child")
    }
}

```
- Observations:
```java
    Parent.meth();//prnits Parent
    Child.meth();//prints Child

    Child obj=new Child();
    obj.meth();//prints Child

    //IMP
    Parent obj=new Child();
    obj.meth();//prints Parent(NOT CHILD)
```

</details>

---

## Encapsulation
- It just means creating methods and variables to operate on some private data rather than direct acess and manipulation 
- Implemented using `private` keyword, `getters` and `setters`
- `WITHOUT ENCAPSULATION:`
```java
class A{
    int API_KEY=1234567;
}
class Main{
    psvm(){
        A obj=new A();
        obj.API_KEY=098765;//object is directly able to change api key which is so dangerous
    }
}
```
- `WITH ENCAPSULATION`
```java
class A{
    private int API_KEY=1234567;
    int getApiKey(){
        return API_KEY;
    }
    int setApiKey(int newV){
        API_KEY=newV;
    }
}
class Main{
    psvm(){
        A obj=new A();
        obj.API_KEY=09876;//INVALID
        obj.setApiKey(09876)//VALID
        //observe that we r not allowing diret manipulation of the APIkey now, ie we have encapsulated aout ApiKey with some method, so that any chedchad whener needs to be done on api key, must happend via these methods only and NOT directly
    }
}
```
### ❓ Whats difference between Encapsulation and Abstraction
<details>
<summary>Answer</summary>

| Feature     | Encapsulation                        | Abstraction                       |
| ----------- | ------------------------------------ | --------------------------------- |
| Purpose     | Hides internal **data**              | Hides internal **implementation** |
| Focus       | Access control                       | Simplicity and usage              |
| Achieved by | `private`, `public`, getters/setters | `abstract` classes, `interfaces`  |
| Example     | We cannot access API key directly,but only via capsule of getter                  | Suppose in caculator, user doent need to know the optimizations in algorithm, he just needs the result               |


</details>

---


## Abstraction
- Hiding uneccary detials and showing only required details
### ❓ What are Java Access Modifiers?
<details>
<summary>Answer</summary>

<br>

| Access Modifier | Class | Package | Subclass (same package) | Subclass (different package) | Outside Package |
|-----------------|:-----:|:-------:|:------------------------:|:-----------------------------:|:----------------:|
| `private`       | ✅    | ❌      | ❌                       | ❌                            | ❌               |
| _(default)_     | ✅    | ✅      | ✅                       | ❌                            | ❌               |
| `protected`     | ✅    | ✅      | ✅                       | ✅                            | ❌ *(via subclass only)* |
| `public`        | ✅    | ✅      | ✅                       | ✅                            | ✅               |

---

**Summary**:
- `private`: Only within the same class.
- `default`: Only within the same package.
- `protected`: Same package + subclass in other packages.
- `public`: Accessible everywhere.

</details>

----
 ### ❓ Name some inbuilt java packages
 <details>
 <summary>Answer</summary>
 
 - Lang
 - io
 - Util
 - applet
 - awt
 - net
 
 </details>
 
 ---

## Generics
 ### ❓ What are Generics
 <details>
 <summary>Answer</summary>
 
- Generics help us define the datatype of the data we want to work with
- Example, say i want a `TreeNode` of any type
```java
class TreeNode<T>{
    TreeNode<T>left;
    TreeNode<T>right;
    T val;
    TreeNode(T val){
        this.val=val;
    }
}
```
 - Now Suppose i want `T`, must be of type Number only(Long,Integer,Float etc..)
 ```java
 class TreeNode<T extends Number>{
    ....rest same
 }

//if i just pass List<Number>,then i cant use interger or anything,so we use below syntax
 void meth(List<? extends Number> someList){...}

 ```
 </details>
 
 ---
 ### ❓ What are functional interface
 <details>
 <summary>Answer</summary>
 
 - Interface that implements only one method is called 
 `functional interface`
 - They can be used directly using `lambda expressions`
 ```java
 interface Task{
    int meth(int x,int y);
 }
 class Main{
    psvm(){
        Task add=(x,y)->x+y;
        Task sub=(x,y)->x-y;  
    }
 }
 ```
 </details>
 
 ---
 ## Exceptios
 ### ❓ Which class handles Exception
 <details>
 <summary>Answer</summary>
 
 - Throwable
 
 </details>
 
 ---
 ### ❓ Explain Types of Exception
 <details>
 <summary>Answer</summary>
 
 - `Checked Exception:`the exception whcih arises on compile time itsef
 - Examples: ArgumentException  
 - `Unchecked Exception:`the exception whcih arises on run time 
 - Example: Arithmetic exception, ArrayIndexOutOfBounds
 </details>
 
 ---
  ### ❓ How do u add `clone()` method to some class
  <details>
  <summary>Answer</summary>
  
  - Just make that class implement `Cloneable`
  ```java
class A implements Cloneable{
    ....other methods
     @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
class Main throws Exception{
    psvm(){
        A obj=new A();
        A obj2=A.clone();
    }
}
```

- The cloned stuff is just a `shallow copy`, meaning that the change in clone `WILL ASLO` be reflected in original
```java
int arr[]={1,2,3,4,5};
int clone[]=arr.clone();
clone[2]=100;//this  affect originality of arr, ie arr is now  {1,2,100,4,5} though it was chnged in clone only becoz clone points to arr

```
  
  </details>
  
  ---
## Collections framework heirarchy 
   <img src="https://techvidvan.com/tutorials/wp-content/uploads/sites/2/2020/03/collection-framework-hierarchy-in-java.jpg">
  
 ### ❓ Tell me when to use ArrayList and when to use Vector
 <details>
 <summary>Answer</summary>
 
 - Vector is thread safe ie only 1 thread can access it at a time, ideal for multithreaded applications
 - Use ArrayList for non multithreaded apps
 
 </details>
 
 ---
 ### ❓ Explain Enums
 <details>
 <summary>Answer</summary>
 
 - Enums are the public static and final constants
 ```java
enum Color{
    Green,
    Yellow
}
//interannly its like:
//public static final Color Green=new Color();
enum Car {
    Audi(10000), Benz(200000), Mercedes(900000);

    private final int price;

    Car(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return this.name() + " cost =" + this.price;
    }
}
 ```
 
 </details>
 
 ---

 ### ❓ What r annotation
 <details>
 <summary>Answer</summary>
 
 - Meta data or some message passed to the compiler by the programmer are called annotations
 
 </details>
 
 ---
 ### ❓ How to create annotation
 <details>
 <summary>Answer</summary>
 
- Look at the below example
```java
@Target(ElementType.TYPE)//means applicable for classes only
public @interface MyAnnotation{}

@Target(ElementType.METHOD)//for methods
public interface RunFast{}
public class A{
    void meth(){...}
    @RunFast
    void meth2(){...}
}

@MyAnnotation
class Main{
    A obj=new A();
    //suppose i want all methods annotated with @RunFast runs
    for(Method m:obj.getClass().getDeclaredMethods()){
        if(m.isAnnotationPresent(RunFast.class)){
            m.invoke(obj);
        }
    }
}
```
 
 </details>
 
 ---
 
 
 
 
