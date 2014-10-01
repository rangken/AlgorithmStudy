
public class main{
  static public void main(String argv[]){
    System.out.println("---------AVL TREE------------");
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
    System.out.println("REOMOVE 50");
    avlt.remove(new Integer(50));
    avlt.print();
    System.out.println("REOMOVE 6");
    avlt.remove(new Integer(6));
    avlt.print();

    System.out.println("---------RED-BLLACK TREE------------");
    RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
    rbt.add(new Integer(50));
    rbt.add(new Integer(10));
    rbt.add(new Integer(25));
    rbt.add(new Integer(5));
    rbt.add(new Integer(5));
    rbt.add(new Integer(6));
    rbt.add(new Integer(10));
    rbt.print();
  }
}

