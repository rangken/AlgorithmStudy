//import java.util.LinkedList;
//import java.util.Iterator;

public class main{
  static public void main(String argv[]){
    LinkedList<String> l = new LinkedList<String>();
    l.add("A");
    l.add("B");
    l.add("C");
    l.remove("A");

    for(Iterator<String> i = l.iterator(); i.hasNext();){
        String str = i.next();
        System.out.println(str);
    }
  }
}

