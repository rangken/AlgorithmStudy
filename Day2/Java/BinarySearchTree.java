public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> implements ITree<E>{
   protected enum Position {
      LEFT, RIGHT
  };
  public BinarySearchTree(){

  }
  public Node<E> getNode(E data){
    Node<E> node = root;
    while(node != null){
      if(data.compareTo(node.element) == 0){
        return node;
      }else if(data.compareTo(node.element) < 0){
        node = node.left;
      }else{
        node = node.right;
      }
    }
    return null;
  }
  protected Node<E> getLeafLeftNode(Node<E> node){
    Node<E> tNode = node;
    if(tNode == null)
      return tNode;
    while(tNode.left != null){
      tNode = tNode.left;
    }
    return tNode;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean add(E data){
    Node<E> newNode = null;
    if(this.creator == null)
      newNode = new Node<E>(data);
    else
      newNode = this.creator.createNewNode(data, null);

    if(root == null){
      root = newNode;
      root.parent = null;
    }else{
      Node<E> parent;
      Node<E> t = root;
      int cmp;
      do{
        parent = t;
        cmp = data.compareTo(parent.element);
        if(cmp < 0){
          t = t.left;
        }else if(cmp > 0){
          t = t.right;
        }else{
          // 얼레는 그냥 덮어씌우는게 일반적인듯..?
          System.out.println("데이터 중복안됨");
          return false;
        }
      }while( t != null);
      if(cmp < 0){
        parent.left = newNode;
        newNode.parent = parent;
      }else{
        parent.right = newNode;
        newNode.parent = parent;
      }
    }
    return true;
  }

  @SuppressWarnings("unchecked")
  protected Node<E> addRecursive(E data){
    Node<E> newNode = null;
    if(this.creator == null)
      newNode = new Node<E>(data);
    else
      newNode = this.creator.createNewNode(data, null);
    if(root == null){
      root = newNode;
      root.parent = null;
    }
    insertNode(root, newNode);
    return newNode;
  }
  // recursive add
  private Node<E> insertNode(Node<E> currentParent, Node<E> newNode) {
    if (currentParent == null)
      return newNode;
    else if (newNode.element.compareTo(currentParent.element) > 0){
      currentParent.right = insertNode(currentParent.right, newNode);
      currentParent.right.parent = currentParent;
    }
    else if (newNode.element.compareTo(currentParent.element) < 0 ){
      currentParent.left = insertNode(currentParent.left, newNode);
      currentParent.left.parent = currentParent;
    }

    return currentParent;
  }
  @Override
  public E remove(E data){
    Node<E> removeNode = getNode(data);
    if(removeNode.left == null && removeNode.right == null){
      // leaf node
      Node<E> parent = removeNode.parent;
      if(parent.left == removeNode){
        parent.left = null;
      }else if(parent.right == removeNode){
        parent.right = null;
      }
      removeNode = null;
    }
    else if(removeNode.left != null && removeNode.right == null){
      // 자식이 한개밖에없다.
      Node<E> parent = removeNode.parent;
      Node<E> child = removeNode.left;
      if(parent.left == removeNode){
        parent.left = child;
      }else if(parent.right == removeNode){
        parent.right = child;
      }
      removeNode = null;
    }
    else if(removeNode.right != null && removeNode.left == null){
      // 자식이 한개밖에없다.
      Node<E> parent = removeNode.parent;
      Node<E> child = removeNode.right;
      if(parent.left == removeNode){
        parent.left = child;
      }else if(parent.right == removeNode){
        parent.right = child;
      }
      removeNode = null;
    }
    else if(removeNode.right != null && removeNode.left != null){

      Node<E> parent = removeNode.parent;
      Node<E> replaceNode = getLeafLeftNode(removeNode.right);
      //System.out.println(replaceNode.element);

      if(parent.left == removeNode){
        parent.left = replaceNode;
        replaceNode.left = removeNode.left;
        if(replaceNode.right == null){
          if(replaceNode != removeNode.right)
            replaceNode.right = removeNode.right;
        }
        replaceNode.parent.left = null;
      }else if(parent.right == removeNode){
        parent.right = replaceNode;
        replaceNode.left = removeNode.left;
        // 대신할 노드에 왼쪽은 항상 없지만 오른쪽은 있을수도 있다.
        if(replaceNode.right == null){
          // 삭제할 노드에 오른쪽 노드가 대신할노드랑 같으면 서로 참조하게됨
          if(replaceNode != removeNode.right)
            replaceNode.right = removeNode.right;
        }
        replaceNode.parent.left = null;
      }
      removeNode = null;
    }

    return data;
  }

  public void rotateLeft(Node<E> node){
    System.out.println("ROTATE LEFT AT " + node);
    Node<E> parent = node.parent;
    Position parentPosition = null;
    if(parent != null){
      if(node.equals(parent.left)){
        parentPosition = Position.LEFT;
      }else{
        parentPosition = Position.RIGHT;
      }
    }
    Node<E> greater = node.right;
    Node<E> lesser = greater.left;
    node.right = null;
    greater.left = node;
    node.parent = greater;
    node.right = lesser;
    if(lesser != null)
      lesser.parent = node;

    if(parentPosition != null){
      if(parentPosition == Position.LEFT){
        parent.left = greater;
      }else{
        parent.right = greater;
      }
      greater.parent = parent;
    }else{
      root = greater;
      greater.parent = null;
    }
  }

  public void rotateRight(Node<E> node){
    System.out.println("ROTATE Right AT " + node);
    Node<E> parent = node.parent;
    Position parentPosition = null;
    if(parent != null){
      if(node.equals(parent.left)){
        parentPosition = Position.LEFT;
      }else{
        parentPosition = Position.RIGHT;
      }
    }
    Node<E> lesser = node.left;
    Node<E> greater = lesser.right;
    node.left = null;
    lesser.right = node;
    node.parent = lesser;
    node.left = greater;
    if(greater != null)
      greater.parent = node;

    if(parentPosition != null){
      if(parentPosition == Position.LEFT){
        parent.left = lesser;
      }else{
        parent.right = lesser;
      }
      lesser.parent = parent;
    }else{
      root = lesser;
      lesser.parent = null;
    }
  }
}
