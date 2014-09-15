
public class main{
  static public void main(String argv[]){
    System.out.println("---------바이너리 트리------------");
    BinaryTree.Node<String> node = new BinaryTree.Node<String>("1");
    BinaryTree<String> bt = new BinaryTree<String>();
    bt.insertLeft("2",node);
    bt.insertRight("3",node);
    bt.insertLeft("4",node.left);
    bt.insertRight("5",node.left);
    bt.insertLeft("6",node.right);
    bt.insertLeft("7",node.right.left);

    bt.print();
    System.out.println("전위 순회");
    bt.printPreOrder();
    System.out.println("");

    System.out.println("중위 순회");
    bt.printInOrder();
    System.out.println("");

    System.out.println("후위 순회");
    bt.printPostOrder();
    System.out.println("");

    System.out.println("레벨 순회");
    bt.printLevelOrder();
    System.out.println("");

    System.out.println("BFS");
    bt.printBFS();
    System.out.println("");

    class Nexters implements Comparable<Nexters>{
      // Comparable 은 기본 구현(Integer), Comparator는 추가 구현
      public String name;
      public Integer age;
      public Nexters(String name, Integer age){
          this.name=name;
          this.age=age;
      }
      @Override
      public int compareTo(Nexters n){
        if(this.age > n.age){
          return 1;
        }else if(this.age == n.age){
          return 0;
        }else{
          return -1;
        }
      }
      @Override
      public String toString(){
        return name+"(" + age +")";
      }
    }
    System.out.println("---------바이너리 서치 트리------------");
    /*
    BinarySearchTree<Nexters> bst = new BinarySearchTree<Nexters>();

    bst.add(new Nexters("MOK",27));
    bst.add(new Nexters("SOYOON",25));
    bst.add(new Nexters("GAJIN",24));
    bst.add(new Nexters("JAEYOUNG",28));
    bst.add(new Nexters("GYUTAE",26));
    bst.add(new Nexters("WHO",33));
    bst.print();
    */
    BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
    bst.add(new Integer(10));
    bst.add(new Integer(15));
    bst.add(new Integer(9));
    bst.add(new Integer(5));
    bst.add(new Integer(8));
    bst.add(new Integer(4));
    bst.add(new Integer(14));
    bst.add(new Integer(18));
    bst.add(new Integer(22));
    bst.add(new Integer(21));
    bst.add(new Integer(16));
    bst.add(new Integer(23));

    bst.print();
    System.out.println("제거");
    bst.remove(new Integer(18));
    bst.print();
  }
}

