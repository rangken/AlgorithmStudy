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
  protected Node<E> addAndGetNode(E data){
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
    Node<E> replaceNode = getReplacementNode(removeNode);
    replaceNodeWithNode(removeNode, replaceNode);
    return data;
  }
  public Node<E> removeAndGetNode(E data){
    Node<E> removeNode = getNode(data);
    if(removeNode != null){
      Node<E> replaceNode = getReplacementNode(removeNode);
      replaceNodeWithNode(removeNode, replaceNode);
    }
    return removeNode;
  }
  protected Node<E> getReplacementNode(Node<E> nodeToRemoved) {
    Node<E> replaceNode = null;
    if(nodeToRemoved.left != null && nodeToRemoved.right == null){
      replaceNode = nodeToRemoved.left;
    }else if(nodeToRemoved.right != null && nodeToRemoved.left == null){
      replaceNode = nodeToRemoved.right;
    }else if(nodeToRemoved.left != null && nodeToRemoved.right != null){
      // 오른쪽 노드에서 가장 왼쪽에 있는 노드를 찾는다.
      // 왼쪽 노드에서 가장 오른쪽에 있는 노드를 찾아도 된다.
      replaceNode = getLeafLeftNode(nodeToRemoved.right);
      if(replaceNode == null){
        // 삭제할 노드에 오른쪽 노드가 왼쪽 노드가 없다면
        // 그냥 오른쪽 노드로 대신함
        replaceNode = nodeToRemoved.right;
      }
    }
    return replaceNode;
  }
  protected void replaceNodeWithNode(Node<E> nodeToRemoved, Node<E> replacementNode) {
    // leaf 노드를 삭제한다면 replacementNode 가 null 일수도 있다.
    if(replacementNode != null){
      // 대체 할 노드의 오른쪽 왼쪽을 바꾸기 전에 미리 저장해 놓는다. 처음에 대체할 노드의 오른쪽 왼쪽부터 바꾼다.
      // 1.왼쪽에 가장 오른쪽을 선택했을경우 데체할 노드의 왼쪽 자식은 있을수도 있다.
      Node<E> replacementNodeLeft = replacementNode.left;
      // 2.오른쪽에 가징왼쪽을 선택했을경우 대체할 노드의 오른쪽 자식은 있을수도 있다.
      Node<E> replacementNodeRight = replacementNode.right;

      // 대체할 노드 <-> 삭제할 노드의 양쪽 자식
      // ** 삭제할 노드와 대체할노드가 바로 붙어 있다면 삭제할 노드의 부모를 대체할노드로 바꿔주기만하면됨**
      // 삭제할 노드에 양쪽 자식을 대체할 노드에 넣어준다.
      Node<E> nodeToRemoveLeft = nodeToRemoved.left;
      // 삭제할 노드의 왼쪽이 있다면, **근데 삭제할노드의 왼쪽이 대체할노드가 아니라면**
      if(nodeToRemoveLeft != null && !nodeToRemoveLeft.equals(replacementNode)){
        replacementNode.left = nodeToRemoveLeft;
        nodeToRemoveLeft.parent = replacementNode;
      }
      Node<E> nodeToRemoveRight = nodeToRemoved.right;
      // 삭제할 노드의 오른쪽이 있다면, **근데 삭제할 노드의 오른쪽이 대체할 노드가 아니라면**
      if(nodeToRemoveRight != null && !nodeToRemoveRight.equals(replacementNode)){
        replacementNode.right = nodeToRemoveRight;
        nodeToRemoveRight.parent = replacementNode;
      }

      // 대체할 노드의 부모 <-> 대체할 노드의 자식 (대체할 노드가 빠지므로)
      // 삭제할 노드의 오른쪽 노드의 맨왼쪽 노드(대체할노드) 의 오른쪽 자식은 있을수도 있다.

      Node<E> replacementParent = replacementNode.parent;
      // 대체할 노드의 부모가 있고, **대체할 노드의 부모가 삭제할것은 아니라면**
      if(replacementParent != null && !replacementParent.equals(nodeToRemoved)){
        // 대체할 노드가 부모의 왼쪽이라면(1.오른쪽에서 가장 왼쪽을 선택했을경우)
        if(replacementParent.left != null && replacementParent.left.equals(replacementNode)){
          // 대체할 노드의 오른쪽을 대체할 노드의 부모의 왼쪽으로 연결해준다.
          replacementParent.left = replacementNodeRight;
          if(replacementNodeRight != null){
            // 만약 대체노드의 오른쪽이 자식이 있다면 대체할 노드의 오른쪽의 부모를 대체할 노드의 부모로
            replacementNodeRight.parent = replacementParent;
          }
        }else if(replacementParent.right != null && replacementParent.right.equals(replacementNode)){
          // 1.삭제할 노드의 오른쪽의 가징 왼쪽 선택했으므로 대체할 노드의 부모의 오른쪽에 있을일이 없다.
          replacementParent.right = replacementNodeLeft;
          if(replacementNodeLeft != null){
            replacementNodeLeft.parent = replacementParent;
          }
        }
      }
    }
    // 삭제할 노드의 부모 <-> 대체 노드
    Node<E> parent = nodeToRemoved.parent;
    if(parent == null){
      // 삭제할 노드가 루트 노드
      root = replacementNode;
      if(root != null)
        root.parent = null;
    }else if(parent.left != null && parent.left.equals(nodeToRemoved)){
      // 삭제할노드가 부모의 왼쪽 이라면
      parent.left = replacementNode;
      if(replacementNode != null)
        replacementNode.parent = parent;

    }else if(parent.right != null && parent.right.equals(nodeToRemoved)){
      // 삭제할노드가 부모의 오른쪽 이라면
      parent.right = replacementNode;
      if(replacementNode != null)
        replacementNode.parent = parent;
    }
  }

  public E remove2(E data){
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
    System.out.println("ROTATE RIGHT AT " + node);
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
