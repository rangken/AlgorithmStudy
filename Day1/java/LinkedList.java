public class LinkedList<E> implements List<E>{
  transient int size = 0;

  transient Node<E> first;
  transient Node<E> last;

  public LinkedList() {
  }
  public E get(int index) {
      return node(index).item;
  }
  //@TODO BASE로
  public ListIterator<E> listIterator() {
      return listIterator(0);
  }
  public ListIterator<E> listIterator(int index) {
      return new ListItr(index);
  }
  public int size(){
    return size;
  }
  public boolean isEmpty(){
    return size == 0;
  }
  public boolean add(E e){
    linkLast(e);
    return true;
  }
  public boolean remove(Object o){
    if (o == null) {
        for (Node<E> x = first; x != null; x = x.next) {
            if (x.item == null) {
                unlink(x);
                return true;
            }
        }
    } else {
        for (Node<E> x = first; x != null; x = x.next) {
            if (o.equals(x.item)) {
                unlink(x);
                return true;
            }
        }
    }
    return false;
  }
  private void linkFirst(E e) {
      final Node<E> f = first;
      final Node<E> newNode = new Node<>(null, e, f);
      first = newNode;
      if (f == null)
          last = newNode;
      else
          f.prev = newNode;
      size++;
  }
  void linkLast(E e){
      final Node<E> l = last;
      final Node<E> newNode = new Node<>(l, e, null);
      last = newNode;
      if (l == null)
          first = newNode;
      else
          l.next = newNode;
      size++;
  }
  E unlink(Node<E> x) {
      // assert x != null;
      final E element = x.item;
      final Node<E> next = x.next;
      final Node<E> prev = x.prev;

      if (prev == null) {
          first = next;
      } else {
          prev.next = next;
          x.prev = null;
      }

      if (next == null) {
          last = prev;
      } else {
          next.prev = prev;
          x.next = null;
      }

      x.item = null;
      size--;
      return element;
  }

  Node<E> node(int index) {
      if (index < (size >> 1)) {
          Node<E> x = first;
          for (int i = 0; i < index; i++)
              x = x.next;
          return x;
      } else {
          Node<E> x = last;
          for (int i = size - 1; i > index; i--)
              x = x.prev;
          return x;
      }
  }

  //@TODO BASE로 변경
  public Iterator<E> iterator() {
      return new ListItr(0);
  }
  // 외부에서는 접근 불가능한 InnerClass
  // static Inner class 안에는 static variable 선언 가능함
  private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next){
      this.prev = prev;
      this.item = element;
      this.next = next;
    }
  }

  private class ListItr implements ListIterator<E> {
    private Node<E> lastReturned = null;
    private Node<E> next;
    private int nextIndex;
    ListItr(int index) {
        next = (index == size) ? null : node(index);
        nextIndex = index;
    }
    public boolean hasNext() {
        return nextIndex < size;
    }
    public E next() {
        if (!hasNext())
        {

        }
        lastReturned = next;
        next = next.next;
        nextIndex++;
        return lastReturned.item;
    }
    public void remove() {
        if (lastReturned == null)
        {

        }

        Node<E> lastNext = lastReturned.next;
        unlink(lastReturned);
        if (next == lastReturned)
            next = lastNext;
        else
            nextIndex--;
        lastReturned = null;
    }
  }
}
