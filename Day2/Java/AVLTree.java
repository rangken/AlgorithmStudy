public class AVLTreeM<E extends Comparable<E>> extends BinarySearchTree<E> implements ITree<E>{
  private enum Balance {
      LEFT_LEFT, LEFT_RIGHT, RIGHT_LEFT, RIGHT_RIGHT
  };

  public AVLTree(){

  }
  @Override
  public boolean add(E data){
    return true;
  }
  @Override
  public E remove(E value){
    return value;
  }
}
