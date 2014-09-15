public class BinaryTree<E>{

  protected Node<E> root;

  protected int size = 0;

  public BinaryTree(){
    root = null;
  }
  final public void insertLeft(E data, Node<E> node){
    if(node.left != null){
        System.out.println("왼쪽 노드가 이미 차있음");
        return ;
    }
    if(root == null){
        root = node;
    }
    Node<E> newNode = new Node<E>(data);
    node.left = newNode;
  }
  final public void insertRight(E data, Node<E> node){
    if(node.right != null){
        System.out.println("오른쪽 노드가 이미 차있음");
        return ;
    }
    if(root == null){
        root = node;
    }
    Node<E> newNode = new Node<E>(data);
    node.right = newNode;
  }

  protected int getMaxLevel(Node<E> node){
    if(node == null)
        return 0;
    return java.lang.Math.max(getMaxLevel(node.left), getMaxLevel(node.right)) + 1;
  }

  public void printPreOrder(){
    BinaryTreePrinter.printPreOrder(root);
  }
  public void printInOrder(){
    BinaryTreePrinter.printInOrder(root);
  }
  public void printPostOrder(){
    BinaryTreePrinter.printPostOrder(root);
  }
  public void printLevelOrder(){
    BinaryTreePrinter.printLevelOrder(root);
  }
  public void printBFS(){
    BinaryTreePrinter.printBFS(root);
  }
  public void print(){
    BinaryTreePrinter.print(root);
  }

  public static class Node<E>{
    // Inner class 에서는 private 라도 멤버 변수 접근 가능함!
    public E element;
    public Node<E> parent;
    public Node<E> left;
    public Node<E> right;
    public Node(){
        this.left = null;
        this.right = null;
    }
    public Node(E _element){
        this.element = _element;
        this.left = null;
        this.right = null;
    }
    public Node(E _element,Node<E> parent){
        this.parent = parent;
        this.element = _element;
        this.left = null;
        this.right = null;
    }
    public void visit(){
        System.out.print(element);
    }
  }
}
