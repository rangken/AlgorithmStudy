public class RedBlackTree<E extends Comparable<E>> extends BinarySearchTree<E> implements ITree<E>, BinaryTree.INodeCreator<E>{
  protected static final boolean BLACK = false;
  protected static final boolean RED = true;

  public RedBlackTree() {
      this.creator = this;
  }
  @Override
  public boolean add(E data){
    RedBlackNode<E> nodeAdded = null;
    boolean added = false;
    if(root == null){
      root = this.creator.createNewNode(data, null);
      ((RedBlackNode<E>) root).color = BLACK;
      root.left = this.creator.createNewNode(null, root);
      ((RedBlackNode<E>) root.left).color = BLACK;
      root.right = this.creator.createNewNode(null, root);
      ((RedBlackNode<E>) root.right).color = BLACK;

      nodeAdded = (RedBlackNode<E>) root;
      added = true;
    }else{
      Node<E> node = root;
      while (node != null) {
        if (node.element == null) {
          node.element = data;
          ((RedBlackNode<E>) node).color = RED;
          node.left = this.creator.createNewNode(null, node);
          ((RedBlackNode<E>) node.left).color = BLACK;
          node.right = this.creator.createNewNode(null, node);
          ((RedBlackNode<E>) node.right).color = BLACK;
          nodeAdded = (RedBlackNode<E>) node;
          added = true;
          break;
        }else if (data.compareTo(node.element) <= 0) {
            node = node.left;
        } else {
            node = node.right;
        }
      }
    }
    if (added == true) {
        balanceAfterInsert(nodeAdded);
    }
    return true;
  }
  private void balanceAfterInsert(RedBlackNode<E> node) {
    RedBlackNode<E> parent = (RedBlackNode<E>) node.parent;

    if (parent == null) {
      // 1.현재노드의 루트
      node.color = BLACK;
      return;
    }
    if (parent.color == BLACK) {
      // 2. 현재노드의 부모가 블랙 => 문제없다.
      return;
    }
    RedBlackNode<E> grandParent = node.getGrandParent();
    RedBlackNode<E> uncle = node.getUncle();
    if (parent.color == RED && uncle.color == RED) {
        // 3. 부모와 삼춘이 모두 RED => 두사람 모두 블랙이되고 할머니를 레드로
        parent.color = BLACK;
        uncle.color = BLACK;
        if (grandParent != null) {
            grandParent.color = RED;
            balanceAfterInsert(grandParent);
        }
    } else {
        if (parent.color == RED && uncle.color == BLACK) {
            // 4. 부모는 레드 삼촌은 블랙
            // 부모의 오른쪽에 있는 노드라면, 엄마가 할머니의 왼쪽에 있다면
            if (node.equals(parent.right) && parent.equals(grandParent.left)) {
                // right-left
                rotateLeft(parent);
                node = (RedBlackNode<E>) node.left;

                grandParent = node.getGrandParent();
                parent = (RedBlackNode<E>) node.parent;
                uncle = node.getUncle();
            } else if (node.equals(parent.left) && parent.equals(grandParent.right)) {
                // left-right
                rotateRight(parent);
                node = (RedBlackNode<E>) node.right;

                grandParent = node.getGrandParent();
                parent = (RedBlackNode<E>) node.parent;
                uncle = node.getUncle();
            }
        }

        if (parent.color == RED && uncle.color == BLACK) {
            // 5. 부모가 레드 삼촌이 블랙
            parent.color = BLACK;
            grandParent.color = RED;
            if (node.equals(parent.left) && parent.equals(grandParent.left)) {
                // left-left
                rotateRight(grandParent);
            } else if (node.equals(parent.right) && parent.equals(grandParent.right)) {
                // right-right
                rotateLeft(grandParent);
            }
        }
    }
  }
  @Override
  public Node<E> createNewNode(E element, Node<E> parent) {
      return (new RedBlackNode<E>(element, parent, BLACK));
  }
  protected static class RedBlackNode<E extends Comparable<E>> extends Node<E> {
    protected boolean color = BLACK;

    protected RedBlackNode(E element, Node<E> parent,boolean color) {
        super(element, parent);
        this.color = color;
    }
    protected RedBlackNode<E> getGrandParent() {
        if (parent == null || parent.parent == null) return null;
        return (RedBlackNode<E>) parent.parent;
    }

    protected RedBlackNode<E> getUncle() {
        RedBlackNode<E> grandParent = getGrandParent();
        if (grandParent == null) return null;
        if (grandParent.left != null && grandParent.left.equals(parent)) {
            // 부모에가 할머니에 왼쪽에 있다면 => 삼촌은 오른쪽
            return (RedBlackNode<E>) grandParent.right;
        } else if (grandParent.right != null && grandParent.right.equals(parent)) {
            // 부모에가 할머니에 오른쪽에 있다면 => 삼촌은 왼쪽
            return (RedBlackNode<E>) grandParent.left;
        }
        return null;
    }

    protected RedBlackNode<E> getSibling() {
        if (parent == null) return null;
        if (parent.left.equals(this)) {
            // 내가 부모의 왼쪽이라면 형재자매는 오른쪽
            return (RedBlackNode<E>) parent.right;
        } else if (parent.right.equals(this)) {
            // 내가 부모의 오른쪽이라면 형재자매는 왼쪽
            return (RedBlackNode<E>) parent.left;
        } else {
            System.err.println("opps no sibling");
        }
        return null;
    }

    protected boolean isLeaf() {
        // RedBlack 에서는 자식 모두 null 이면 leat
        if (left != null) return false;
        if (right != null) return false;
        return true;
    }

    @Override
    public String toString(){
      String colorStr = color == BLACK ? "BLACK" : "RED";
      return "element : " + element +" : COLOR : " + colorStr;
    }
  }

}
