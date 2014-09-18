
public class main{
  static public void main(String argv[]){
    System.out.println("---------AVL 트리------------");
    AVLTree<Integer> avlt = new AVLTree<Integer>();
    avlt.add(new Integer(50));
    avlt.add(new Integer(10));
    avlt.add(new Integer(25));
    avlt.add(new Integer(5));
    avlt.add(new Integer(55));
    avlt.add(new Integer(5));
    avlt.add(new Integer(6));
    avlt.add(new Integer(10));
    avlt.add(new Integer(18));
    avlt.add(new Integer(22));
    avlt.print();
    System.out.println("삭제");
    avlt.remove(new Integer(5));
    avlt.print();
  }
}

