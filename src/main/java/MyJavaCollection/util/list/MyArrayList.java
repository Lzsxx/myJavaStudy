package MyJavaCollection.util.list;

import java.util.NoSuchElementException;

public class MyArrayList<AnyType> implements MyList<AnyType> {

    public static final int DEFAULT_CAPACITY = 5;
    private int theSize;
    private AnyType [] theItems;

    public MyArrayList() {
        clear();
    }

    private void ensureCapacity(int newCapacity){
        if (newCapacity < theSize){
            return;
        }
        AnyType[] oldItems = theItems;
        theItems = (AnyType []) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            theItems[i] = oldItems[i];
        }
    }

    @Override
    public boolean add(int idx, AnyType newValue) {
        if (idx < 0 || idx > size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        if (newValue == null){
            throw new NullPointerException();
        }
        if (theItems.length == size()){
            ensureCapacity(size() * 2 + 1);
        }
        for (int i = size(); i > idx; i--) {
            theItems[i] = theItems[i - 1];
        }
        theItems[idx] = newValue;
        theSize ++;

        return true;
    }


    @Override
    public AnyType set(int idx, AnyType newValue) {
        if (idx < 0 || idx > size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        AnyType oldValue = theItems[idx];
        theItems[idx] = newValue;
        return oldValue;
    }

    @Override
    public AnyType get(int idx) {
        if (idx < 0 || idx > size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[idx];
    }

    @Override
    public AnyType remove(int idx) {
        if (idx < 0 || idx > size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        AnyType oldValue = theItems[idx];
        for (int i = idx; i < size() - 1; i++) {
            theItems[i] = theItems[i + 1];
        }
        theSize -- ;
        return oldValue;
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
    public boolean clear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
        return true;
    }

    // 在尾部增加元素
    @Override
    public boolean add(AnyType newValue) {
        add(size(),newValue);
        return true;
    }

    // 移除尾部的元素
    @Override
    public boolean remove(AnyType removeValue) {
        remove(size() - 1);
        return true;
    }

    @Override
    public boolean contains(AnyType findValue) {
        for (int i = 0; i < size(); i++) {
            if (findValue.equals(theItems[i])){
                return true;
            }
        }
        return false;
    }

    @Override
    public MyIterator iterator() {
        return new ArrayItr();
    }
    @Override
    public MyListIterator listIterator() {
        return new ArrayListItr();
    }

    private class ArrayItr implements MyIterator<AnyType> {
        protected int current = 0 ;
        protected boolean nextFlag = true;
        protected boolean okToRemove = false;
        @Override
        public AnyType next() {
            if (! hasNext()){
                throw new NoSuchElementException();
            }
            // 由于current从0开始, 先输出数据，再将指针下标后移
            nextFlag = true;
            okToRemove = true;
            return (AnyType) theItems[current ++ ];
        }

        @Override
        public boolean hasNext() {
            return current < theSize;
        }

        @Override
        public AnyType remove() {
            // next真修改current
            /**
             * 不管之前是previous还是next , 修改的均是上一刻输出的值，
             * 前一个是next要改变指针位置前移一位，且是在调用remove之前
             * 前一个操作是previous不改变指针current的位置
             */

            if (!okToRemove){
                throw new IllegalStateException();
            }

            okToRemove = false;

            if (nextFlag){   // 调用下一个next()之前，current都指向前一个元素，current要前移一位，使得remove能取得正确的值
                return (AnyType) MyArrayList.this.remove( -- current);
            } else{    // remove之后，current指向下一个要访问的元素
                return (AnyType) MyArrayList.this.remove(current);
            }
        }
    }

    private class ArrayListItr extends ArrayItr implements MyListIterator<AnyType> {

        @Override
        public boolean hasPrevious() {
            return current  > 0;
        }

        @Override
        public AnyType previous() {
            if (! hasPrevious()){
                throw new NoSuchElementException();
            }
            nextFlag = false;
            okToRemove = true;
            return theItems[ -- current ];
        }

        @Override
        public void add(AnyType newValue) {
            /**
             * add 完，next的情况指针不变
             * add 完，previous的情况指针要后移一位
             */

            okToRemove = false;
            // 每次 previous() 或 next()之后才能调用，
            if (nextFlag){
                // next()之后要添加新元素在当前访问元素之后，此时current指向下一个输出元素，则可以直接添加
                //  添加后指针位置在新元素和下一个元素之间
                // 要求下一次点击previous会输出新元素，点击Next会略过新元素输出下一个元素
                // （实际指向新元素的下一位，
                MyArrayList.this.add(current, newValue);
                current ++;
            }
            else if ( !nextFlag ){
                // previous()要添加在输出元素之前，此时current指向输出元素，则current不变，
                // 添加后指针在新元素和当前元素之间
                // 要求下一次点击previous会输出新元素，点击next会输出和上一次同样的元素
                MyArrayList.this.add(current, newValue);
                current ++; // 修正使满足要求，指向新元素的右边一位
            }
        }

        @Override
        public void set(AnyType newValue) {

            // next假修改current

            /**
             * 不管之前是previous还是next , 修改的均是上一刻输出的值，不改变指针current的位置
             */
            okToRemove = false;
            if (nextFlag){  // // 修改的是上一刻的输出值，如果之前是next,下一个接next会略过新修改的值，下一个接previous会输出刚才修改的值
                MyArrayList.this.set(current - 1,newValue);
            }else{// 修改的是上一刻的输出值，如果之前是previous,下一个接previous会略过，下一个接next会输出刚才修改的值
                MyArrayList.this.set(current,newValue);
            }
        }


    }
}
