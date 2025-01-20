package adv_java;
//single ton class menas only one object can be created for a class, for example wile dealing with database, we may want to create an objectof Dbhandling class only

//different ways of creating  a singleton class

//eager initialization:
class Exmaple_for_Early_Initialization {
    private static Exmaple_for_Early_Initialization obj = new Exmaple_for_Early_Initialization();// this is an object of self type

    // to make sure none makes a new obj of A make a private constructor
    private Exmaple_for_Early_Initialization() {
    }// now only this class memebers can make its construtor

    public static Exmaple_for_Early_Initialization getObj() {
        return obj;
    }
}//diadv is that wven if we dont call it, an obj is cretaed in memory(nw A()) so we solve it using  lazy initailation


class Example_for_lazy_initialization{
    private static Example_for_lazy_initialization obj;
    private Example_for_lazy_initialization(){}
    public static Example_for_lazy_initialization getObj(){
        if(obj==null) obj=new Example_for_lazy_initialization();
        return obj;//not it gets stored in memoru only hen this method is called
    }
}//disadv is that, suppose we have 2 threads simultaneously, each will create separate obj, solve using synchrnoized class, suppose we have somany threads also, it takes 1 thread at a time, if instance is null, instantiates it and returns instance, for further threads, it returns the instatntiated value
class Exmaple_for_Synchronized_Initialization{
    private static Exmaple_for_Synchronized_Initialization obj;
    private Exmaple_for_Synchronized_Initialization(){}
    synchronized public static Exmaple_for_Synchronized_Initialization getObj(){
        if(obj==null) obj=new Exmaple_for_Synchronized_Initialization();
        return obj;
    }
}//disadv this is very slow, coz every time thread it checks, to make it only onece, double lock class

class Example_for_double_lock{
    //volatile modifies the memory trathe than in cache
    private static volatile Example_for_double_lock obj;
    private Example_for_double_lock(){}
    public static Example_for_double_lock getObj(){
        if(obj==null){
            synchronized(Example_for_double_lock.class){
                if(obj==null) obj=new Example_for_double_lock();
            }
        }
        return obj;
    }
}


