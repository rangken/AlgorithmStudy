public class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E> implements ITree<E>, BinaryTree.INodeCreator<E>{
  private enum Balance {
      LEFT_LEFT, LEFT_RIGHT, RIGHT_LEFT, RIGHT_RIGHT
  };

  public int height = 1;

  public AVLTree(){
    this.creator = this;
  }

  @Override
  public boolean add(E data){
    Node<E> t = super.addRecursive(data);
    AVLNode<E> addedNode = (AVLNode<E>)t;

    while(addedNode != null){
      addedNode.updateHeight();
      balnaceAfterInsert(addedNode);
      addedNode = (AVLNode<E>) addedNode.parent;

    }
    return true;
  }

  @Override
  public E remove(E value){
    return value;
  }

  private void balnaceAfterInsert(AVLNode<E> node){
    int balanceFactor = node.getBalanceFactor();

    if(balanceFactor < -1 || balanceFactor > 1){
      // 크기 차이가 2보다 커짐
      AVLNode<E> parent = null;
      AVLNode<E> child = null;
      Balance balnace = null;
      if(balanceFactor < 0){
        // 왼쪽에 추가됨
        parent = (AVLNode<E>)node.left;
        balanceFactor = parent.getBalanceFactor();
        if(balanceFactor < 0){
          // 추가된 노드가 부모의 왼쪽에
          child = (AVLNode<E>)parent.left;
          balnace = Balance.LEFT_LEFT;
        }else{
          // 추가된 노드에 부모의 오른쪽에
          child = (AVLNode<E>)parent.right;
          balnace = Balance.LEFT_RIGHT;
        }
      }else{
        // 오른쪽에 추가됨
        parent = (AVLNode<E>)node.right;
        balanceFactor = parent.getBalanceFactor();
        if(balanceFactor < 0 ){
          // 추가된 노드가 부모의 왼쪽에
          child = (AVLNode<E>)parent.left;
          balnace = Balance.RIGHT_LEFT;
        }else{
          child = (AVLNode<E>)parent.right;
          balnace = Balance.RIGHT_RIGHT;
        }
      }
      print();
      if(balnace == Balance.LEFT_LEFT){
        System.out.println("LL");
        rotateLeft(node);
      }else if(balnace == Balance.LEFT_RIGHT){
        System.out.println("LR");
        rotateRight(parent);
        print();
        rotateLeft(node);
        print();
      }else if(balnace == Balance.RIGHT_LEFT){
        System.out.println("RL");
        rotateLeft(parent);
        rotateRight(node);
      }else if(balnace == Balance.RIGHT_RIGHT){
        System.out.println("RR");
        rotateRight(node);
      }
      print();
      node.updateHeight();
      child.updateHeight();
      parent.updateHeight();
    }
  }
  @Override
  public Node<E> createNewNode(E element, Node<E> parent) {
      return (new AVLNode<E>(element, parent));
  }

  protected static class AVLNode<E extends Comparable<E>> extends Node<E>{
    public int height = 1;

    protected AVLNode(E element,Node<E> parent) {
        super(element, parent);
    }

    protected void updateHeight() {
        int leftHeight = 0;
        int rightHeight = 0;
        if (left != null) {
            AVLNode<E> leftNode = (AVLNode<E>) left;
            leftHeight = leftNode.height;
        }
        if (right != null) {
            AVLNode<E> rightNode = (AVLNode<E>) right;
            rightHeight = rightNode.height;
        }

        if (leftHeight > rightHeight) {
            this.height = leftHeight + 1;
        } else {
            this.height = rightHeight + 1;
        }
    }

    protected int getBalanceFactor(){
        int leftHeight = 0;
        int rightHeight = 0;
        if(this.left != null){
          AVLNode<E> avlNode = (AVLNode<E>)left;
          leftHeight = avlNode.height;
        }
        if(this.right != null){
          AVLNode<E> avlNode = (AVLNode<E>)right;
          rightHeight = avlNode.height;
        }
        return rightHeight-leftHeight;
    }
    @Override
    public String toString(){
      return "element : " + element +" : height : "+ height+ " balanceFactor : " + getBalanceFactor();
    }
  }
}
