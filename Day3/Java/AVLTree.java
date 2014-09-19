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
    Node<E> t = super.addAndGetNode(data);
    AVLNode<E> addedNode = (AVLNode<E>)t;

    while(addedNode != null){
      addedNode.updateHeight();
      balnaceAfterInsert(addedNode);
      addedNode = (AVLNode<E>) addedNode.parent;

    }
    return true;
  }

  @Override
  public E remove(E data){
    Node<E> nodeToRemoved = this.getNode(data);
    if (nodeToRemoved != null) {
      Node<E> replacementNode = this.getReplacementNode(nodeToRemoved);

      // 삭제후에 제조정 되어할 노드
      AVLNode<E> nodeToRefactor = null;
      if (replacementNode != null) // 부모부터
          nodeToRefactor = (AVLNode<E>) replacementNode.parent;
      if (nodeToRefactor == null) // 대체할 노드가 널인경우는 leaf 노드 이므로 삭제할 노드의 부모부터 제조정
          nodeToRefactor = (AVLNode<E>) nodeToRemoved.parent;
      // 재조정 노드가 삭제할 노드랑 같다면 부모부터가 아닌 대체할 노드부터
      if (nodeToRefactor != null && nodeToRefactor.equals(nodeToRemoved))
          nodeToRefactor = (AVLNode<E>) replacementNode;

      replaceNodeWithNode(nodeToRemoved, replacementNode);

      if (nodeToRefactor != null) {
            while (nodeToRefactor != null) {
                nodeToRefactor.updateHeight();
                balanceAfterDelete(nodeToRefactor);
                nodeToRefactor = (AVLNode<E>) nodeToRefactor.parent;
            }
        }
    }
    return data;
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
        rotateRight(node);
      }else if(balnace == Balance.LEFT_RIGHT){
        System.out.println("LR");
        rotateLeft(parent);
        print();
        rotateRight(node);
        print();
      }else if(balnace == Balance.RIGHT_LEFT){
        System.out.println("RL");
        rotateRight(parent);
        rotateLeft(node);
      }else if(balnace == Balance.RIGHT_RIGHT){
        System.out.println("RR");
        rotateLeft(node);
      }
      print();
      node.updateHeight();
      child.updateHeight();
      parent.updateHeight();
    }
  }

  private void balanceAfterDelete(AVLNode<E> node) {
    int balanceFactor = node.getBalanceFactor();
    if (balanceFactor == -2 || balanceFactor == 2) {
      // TODO
      if (balanceFactor == -2) {
        // 왼쪽이 더 높이가 높다
        // 1. (LL) 왼쪽 자식의 왼쪽이 높이가 더 높다면 => 오른쪽 회전(노드) => 노드, 노드의 부모 높이 재조정
        // 2. (LR) 왼쪽 자식의 오른쪽이 높이가 더 높다면 => 왼쪽 회전(왼쪽자식),오른쪽회전(노드) => 노드의 부모, 노드의 양쪽자식 높이 재조정

      }
      else if(balanceFactor == 2){
        // 오른쪽이 더 높이가 높다.
        // 3. (RR) 오른쪽 자식의 오른쪽이 높이가 더 높다면 => 왼쪽 회전(노드) => 노드, 노드의 부모 높이 재조정
        // 4. (RLL) 오른쪽 자식의 왼쪽이 높이가 더 높다면 => 오른쪽 회전(오른쪽),왼쪽회전(노드) => 노드의 부모, 노드의 양쪽자식 높이 재조정
      }
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
