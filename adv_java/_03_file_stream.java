package adv_java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class _03_file_stream {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        A obj=new A(23, "Suhail", 2300000);

        //serialize object
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(new File("sample.txt")));
        oos.writeObject(obj);
        oos.flush();oos.close();

        //deserialize object
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File("sample.txt")));
        System.out.println(ois.readObject());
        ois.close();ois.close();
    }
}


class A implements Serializable{
    @Override
    public String toString() {
        return "A [id=" + id + ", name=" + name + ", salary=" + salary + "]";
    }


    int id;String name;int salary;


    public A(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

   
    
   
}