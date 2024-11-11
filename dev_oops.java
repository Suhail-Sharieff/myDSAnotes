
import java.util.ArrayList;
import java.util.List;

//base level provider to show what methods we can have---level1
interface provider {

    void addElement(int e);

    void printList();

    int getSize();

}

// here we implement logic of provider methods---level2
class list_provider implements provider {

    List<Integer> li;

    public list_provider() {
        li = new ArrayList<>();
    }

    @Override
    public void addElement(int e) {
        li.add(e);
    }

    @Override
    public void printList() {
        System.out.println(li);
    }

    @Override
    public int getSize() {
        return li.size();
    }

}

// we make instance service in main and access provider methods only via service---level3
class list_service implements provider {

    list_provider provider;

    public list_service() {
        this.provider = new list_provider();
    }

    @Override
    public void addElement(int e) {
        provider.addElement(e);
    }

    @Override
    public void printList() {
        provider.printList();
    }

    @Override
    public int getSize() {
        return provider.getSize();
    }

}

//---level4-MAIN UI
public class dev_oops {

    public static void main(String[] args) {
        //ur main UI:

        //make instnce of service:
        //observe that UI has no info abot provider a all where we have all of our logic
        list_service service=new list_service();
        service.addElement(23);
        service.addElement(233);
        service.printList();
    }
}