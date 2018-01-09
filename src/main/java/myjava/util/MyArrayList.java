package myjava.util;

public class MyArrayList<E> implements List<E>{
    private static final int DEFAULT_CAPAITY = 5; //初始化总容量
    private int theSize;    // 当前容量
    private  E [] theItems;

    private void ensureCapacity(int newCapacity){
        if (newCapacity < theSize){
            return; // 本函数的意义是为了确保数组长度足够大，如果传入值小于当前已有size，则说明需求的大小已经满足，直接返回
        }
        // 否则，新建扩增后的数组，并将原数组内容复制到新数组中
        E [] oldItems = theItems;
        theItems = (E[]) new Object[newCapacity];
        for (int i = 0; i < theSize; i++) {
            theItems[i] = oldItems[i];
        }
    }

    @Override
    public int size() {
        return theSize;
    }

    @Override
    public boolean isEmpty() {
        return theSize == 0;
    }

    @Override
    public void clear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPAITY);
    }

    @Override
    public boolean add(int idx, E newValue) {
        /**
         * 1、add时首先要注意theSize的递增
         * 2、要注意idx的范围只能是0 -- theSize
         * 3、要注意处理空间不足的情况
         */

        if (idx < 0 || idx > theSize){
            throw new ArrayIndexOutOfBoundsException(); //
        }
        if (newValue == null){
            throw new NullPointerException();   //期望newValue为一个对象，但是却传入了空值，此时抛出null异常
        }

        // 空间不足时自动扩容
        if (theSize == DEFAULT_CAPAITY){
            ensureCapacity(DEFAULT_CAPAITY * 2 + 1);
        }

        for (int i = theSize; i > idx ; i--) {
            theItems[i] = theItems[i - 1];
        }
        theItems[idx] = newValue;
        theSize ++;
        return true;
    }

    @Override
    public void add(E newValue) {
        this.add(size(),newValue);
    }

    @Override
    public E remove(int idx) {
        /**
         * remove要注意theSize的递减
         */
        if (idx < 0 || idx > theSize){
            throw new ArrayIndexOutOfBoundsException(); //
        }
        E oldValue = theItems[idx];
        for (int i = idx; i < size() - 1; i++) {
            theItems[i] = theItems[i + 1];
        }
        theSize -- ;
        return oldValue;
    }

    @Override
    public E remove() {
        return this.remove(size() - 1);
    }

    @Override
    public E set(int idx, E newValue) {
        if (idx < 0 || idx > theSize){
            throw new ArrayIndexOutOfBoundsException(); //
        }
        if (newValue == null){
            throw new NullPointerException();   //期望newValue为一个对象，但是却传入了空值，此时抛出null异常
        }

        E oldValue = theItems[idx];
        theItems[idx] = newValue;
        return oldValue;
    }

    @Override
    public E get(int idx) {
        if (idx < 0 || idx > theSize){
            throw new ArrayIndexOutOfBoundsException(); //
        }
        return theItems[idx];
    }

    @Override
    public boolean contains(E findValue) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

}
