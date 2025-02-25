# Some things to keep in mind while dealing with pointers:

## Direct Manipulation is disatorous

For example u have `head` of the Node, say u want to print it:

- Wrong method:

```java
public void print(Node head){
    while(head!=null) {
        print(head.val);
        head=head.next;//this would modify the original head
    }
}
```

- Correct method:

```java
public void print(Node head){
    Node ptr=head;
    while(ptr!=null) {
        print(ptr.val);
        ptr=ptr.next;//this would NOT modify the original head
    }
}
```

## Addition of new nodes

- Wrong method:

```java
public void add(Node head){
    while(head!=null) {
        head.next=new Node(someVal);
        head=head.next;
    }
}
```

- Correct method:

```java
public add(Node head){
    Node ptr=head;
    while(ptr!=null) {
        ptr.next=new Node(someVal);
        ptr=ptr.next;
    }
    //same would apply even for deletion
}
```

## Merging of two linked list (say sorted)

- Space Inefficient method:

```java
public Node merge(Node l1,Node l2){
    Node ans=new Node(-1),ans_ptr=ans;
    while(l1!=null && l2!=null) {
       if(l1.val<l2.val){
        ans_ptr.next=new Node(l1.val);
        l1=l1.next;
       }else if(l1.val>l2.val){
        ans_ptr.next=new Node(l2.val);
        l2=l2.next;
       }else{
        //nodes have same value
        ans_ptr.next=new Node(l1.val);ans_ptr=ans_ptr.next;
        ans_ptr.next=new Node(l2.val);
        l1=l1.next;
        l2=l2.next
       }
       ans_ptr=ans_ptr.next;
    }
    return ans;
}
```

- Space efficient method:

```java
public add(Node head){
    Node ans=new Node(-1),ans_ptr=ans;
    while(l1!=null && l2!=null) {
       if(l1.val<l2.val){
        ans_ptr.next=l1;//note that we dont have to create new node
        l1=l1.next;
       }else if(l1.val>l2.val){
        ans_ptr.next=l2;
        l2=l2.next;
       }else{
        //nodes have same value
        ans_ptr.next=l1;ans_ptr=ans_ptr.next;
        ans_ptr.next=l2;
        l1=l1.next;
        l2=l2.next
       }
       ans_ptr=ans_ptr.next;
    }
    return ans;
}
```
