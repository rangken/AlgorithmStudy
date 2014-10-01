//import java.util.LinkedList;
//import java.util.Iterator;

public class main{
  static public void main(String argv[]){
    LinkedList<String> l = new LinkedList<String>();
    l.add("A");
    l.add("B");
    l.add("C");
    l.remove("A");
    System.out.println("-----LinkedList-----");
    for(Iterator<String> i = l.iterator(); i.hasNext();){
        String str = i.next();
        System.out.println(str);
    }

    System.out.println("-----ArrayList-----");
    ArrayList<String> a = new ArrayList<String>();
    a.add("A");
    a.add("B");
    for(Iterator<String> i = a.iterator(); i.hasNext();){
        String str = i.next();
        System.out.println(str);
    }
  }
}

