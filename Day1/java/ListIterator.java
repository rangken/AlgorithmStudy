public interface ListIterator<E> extends Iterator<E> {
    boolean hasNext();
    E next();
    void remove();
}
