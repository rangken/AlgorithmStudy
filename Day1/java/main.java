public class main{
  static public void main(String argv[]){
    LinkedList<String> l = new LinkedList<String>();
    l.add("A");
    l.add("B");
    l.add("C");

    for(Iterator<String> i = l.listIterator(); i.hasNext();){
        String str = i.next();
        System.out.println(str);
    }
    /*
    for(String str : l){

    }
    */
  }
}

