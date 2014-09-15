public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> implements ITree<E>{

  public BinarySearchTree(){

  }
  protected Node<E> getNode(E data){
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
  protected Node<E> getRightLeftestNode(Node<E> node){
    Node<E> tNode = node.right;
    if(tNode == null)
      return tNode;
    while(tNode.left != null){
      tNode = tNode.left;
    }
    return tNode;
  }
  @Override
  public boolean add(E data){
    Node<E> newNode = new Node<E>(data);
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
      Node<E> replaceNode = getRightLeftestNode(removeNode);
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
}
