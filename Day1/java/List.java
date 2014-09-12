public interface List<E> extends Collection<E> {
  int size();
  boolean isEmpty();
  boolean add(E e);
  boolean remove(Object o);
}
