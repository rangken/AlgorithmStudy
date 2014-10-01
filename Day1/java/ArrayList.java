public class ArrayList<E> implements List<E>{

  private static final int DEFAULT_CAPACITY = 10;

  private static final Object[] EMPTY_ELEMENTDATA = {};

  private transient Object[] elementData;
  private int size;

  public ArrayList(int initialCapacity){
    super();
    this.elementData = new Object[initialCapacity];
  }
  public ArrayList(){
    super();
    this.elementData = EMPTY_ELEMENTDATA;
  }
  public void ensureCapacity(int minCapacity) {
      int minExpand = (elementData != EMPTY_ELEMENTDATA) ? 0 : DEFAULT_CAPACITY;

      if (minCapacity > minExpand) {
          ensureExplicitCapacity(minCapacity);
      }
  }
  private void ensureCapacityInternal(int minCapacity) {
      if (elementData == EMPTY_ELEMENTDATA) {
          minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
      }
      ensureExplicitCapacity(minCapacity);
  }
  private void ensureExplicitCapacity(int minCapacity) {
      if (minCapacity - elementData.length > 0)
          grow(minCapacity);
  }
  // 최대 array 크기
  private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

  private void grow(int minCapacity){
    int oldCapacity = elementData.length;
    // 얼레 크기에 2배를 늘려줌
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if(newCapacity-minCapacity < 0){
      newCapacity = minCapacity;
    }
    // 최대 ARRAY 크기를 넘음
    if(newCapacity-MAX_ARRAY_SIZE > 0){
      newCapacity = MAX_ARRAY_SIZE;
    }
    // 크기를 늘려서 Array copy
    // 그냥 java.util 에 Arrays 클래스 사용
    elementData = java.util.Arrays.copyOf(elementData,newCapacity);
  }
  public int size(){
    return size;
  }
  public boolean isEmpty(){
    return size == 0;
  }
  @SuppressWarnings("unchecked")
  E elementData(int index) {
      return (E) elementData[index];
  }
  public E get(int index) {
      return elementData(index);
  }
  public boolean add(E e){
    ensureCapacityInternal(size + 1);
    elementData[size++] = e;
    return true;
  }
  public E remove(int index) {
      E oldValue = elementData(index);

      int numMoved = size - index - 1;
      if (numMoved > 0)
          System.arraycopy(elementData, index+1, elementData, index,
                           numMoved);
      elementData[--size] = null; // clear to let GC do its work

      return oldValue;
  }
  public boolean remove(Object o){
    if (o == null){
      for(int i=0; i < size; i++){
        if(elementData[i] == null){
          fastRemove(i);
          return true;
        }
      }
    }else{
      for(int i=0; i < size; i++){
        // LinkedList 에서는 hashCode비교(==) 하지만
        // ArrayList 에서는 내용이 같은지(equals) 를 한다.
        if(o.equals(elementData[i])){
          fastRemove(i);
          return true;
        }
      }
    }
    return false;
  }
  private void fastRemove(int index) {
      int numMoved = size - index - 1;

      if (numMoved > 0){
        // 원본,읽어올부분,복사할대상,카피할 위치,얼마만큼 읽어올지
        // elementData를 index+1 부터 numMoved 만큼 읽어서 index 로 elementData 에 카피
        System.arraycopy(elementData, index+1, elementData, index,
                           numMoved);
      elementData[--size] = null; // clear to let GC do its work
    }
  }
  public Iterator<E> iterator(){
    return new ListItr(0);
  }

  private class ListItr implements ListIterator<E> {
    int cursor;       // index of next element to return
    int lastRet = -1; // index of last element returned; -1 if no such
    public ListItr(int index){
      cursor = index;
    }
    public boolean hasNext() {
        return cursor != size;
    }

    @SuppressWarnings("unchecked")
    public E next() {
        int i = cursor;
        if (i >= size)
        {

        }
        Object[] elementData = ArrayList.this.elementData;
        if (i >= elementData.length)
        {

        }
        cursor = i + 1;
        return (E) elementData[lastRet = i];
    }

    public void remove() {
        if (lastRet < 0)
        {

        }

        ArrayList.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
    }
  }
}
