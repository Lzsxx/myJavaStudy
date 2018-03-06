package myjava.util.list;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class MyLinkedList<AnyType> implements MyList<AnyType> {
    private int theSize = 0;
    private Node<AnyType> startMark = null;
    private Node endMark = null;
    private int modCount = 0;


    public MyLinkedList() {
        clear();
    }

    @Override
    public int size() {
        return theSize;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean clear() {
        startMark = new Node(null, null, null);
        endMark = new Node(null, startMark, null);
        startMark.next = endMark;
        theSize = 0;
        modCount ++;
        return true;
    }

    @Override
    public boolean add(AnyType newValue) {
        return add(size(), newValue);
    }
    @Override
    public boolean add(int idx, AnyType newValue) {
        // 假设此时在idx上的元素为p,要添加的新元素应该在p之前
        Node<AnyType> p = getNode(idx, 0, size());  // add的时候是可以取index为size的大小的，此时表示在末尾添加
        addBefore(p, newValue);
        return true;
    }

    public void remove(Node<AnyType> node) {
        node.prev.next =node.next;
        node.next.prev = node.prev;
        theSize --;
        modCount ++;
    }
    @Override
    public AnyType remove(int idx) {
        Node<AnyType> node = getNode(idx);
        remove(node);
        return node.value;
    }

    @Override
    public boolean remove(AnyType removeValue) {
        return false;
    }

    @Override
    public boolean contains(AnyType findValue) {
        return false;
    }

    @Override
    public AnyType set(int idx, AnyType newValue) {
        Node<AnyType> node = getNode(idx);
        AnyType oldValue = node.value;
        set(node,newValue);
        return oldValue;
    }
    public void set(Node<AnyType> node, AnyType newValue) {
        node.value = newValue;
        modCount ++;
    }
    @Override
    public AnyType get(int idx) {
       Node<AnyType> node = getNode(idx);
       return node.value;
    }
    @Override
    public MyIterator<AnyType> iterator() {
        return new LinkItr();
    }

    @Override
    public MyListIterator<AnyType> listIterator() {
        return new LinkListItr();
    }


    public void addBefore( Node<AnyType> p, AnyType newValue){
        Node<AnyType> newNode = new Node<>(newValue, p.prev, p);
        p.prev.next = newNode;
        p.prev = newNode;
        theSize ++;
        modCount ++;
    }

    public Node<AnyType> getNode(int index){
       return getNode(index, 0, size() - 1);
    }

    private Node<AnyType> getNode(int index, int lower, int upper){
        // 此处传入的index应与Array List的index一致，因此index为0表示第一个有具体值的数据

        if (index < lower || index > upper){
            throw  new IndexOutOfBoundsException();
        }

        Node<AnyType> p = null;

        // 从头开始遍历
        if (index < size() / 2){
            p = startMark.next; // 当index为0时，不进入循环，但应返回第一个有效值，所以这里要取一次next
            for (int i = 0; i < index; i++){
                p = p.next; // 下面进行了 index次跳转，相当于Array从0开始，每次+1，最终值为index,也就是指向了下标为index那个值
            }

        }else { // 从尾巴开始向前遍历
            p = endMark;    // 由于是addBefore，最远处必须返回endMark,否则永远无法在最后添加元素
            for (int i = size(); i > index; i--) {
                p = p.prev;
            }
        }
        return p;
    }


    private class Node<AnyType> {
        // 由于已经是私有内部类，除了外部类以外其他都看不到内部类，所以内部类的成员无所谓是Public 还是 Private
        public AnyType value;
        public Node prev;
        public Node next;
        public Node(AnyType value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
    private class LinkItr implements MyIterator<AnyType>{
        protected boolean okToremove = false;
        protected boolean nextFlag = true;
        protected int expectedCount = modCount;
        protected Node<AnyType> current = startMark.next;     // 从下标为0的元素开始，与Array List一致

        @Override
        public AnyType next() {
            if (expectedCount != modCount){
                throw new ConcurrentModificationException();
            }
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            AnyType value = current.value;
            current = current.next;
            okToremove = true;
            nextFlag = true;
            return value;
        }

        @Override
        public boolean hasNext() {
            return current != endMark;
        }

        @Override
        public AnyType remove() {
            if (expectedCount != modCount){
                throw new ConcurrentModificationException();
            }
            if (!okToremove){
                throw new IllegalStateException();
            }
            Node<AnyType> removeNode = null;

            if (!nextFlag){
                current = current.next; // 如果之前是previous(),current提前指向下一位,然后后续就可以用于next情形下相同的操作
            }
            removeNode = current.prev;
            MyLinkedList.this.remove(current.prev); // remove之后，current指向刚才remove元素的下一个，不影响之后的遍历，
            // 这里要与ArrayList区分开，Array指向固定的位置，所以remove之后该位置元素改变，current要跟着改变，
            // 而这里current指向对象，不会随之remove而改变指向
            expectedCount ++;
            okToremove = false;
            return removeNode.value;
        }
    }

    private class LinkListItr extends LinkItr implements MyListIterator<AnyType>{

        @Override
        public AnyType previous() {
            if (expectedCount != modCount){
                throw new ConcurrentModificationException();
            }
            if (!hasPrevious()){
                throw new NoSuchElementException();
            }

            current = current.prev;
            okToremove = true;
            nextFlag = false;
            return current.value;
        }

        @Override
        public boolean hasPrevious() {
            return current != startMark.next;   // 考虑到previous输出时是指向谁就输出谁，这里只能取到index为0的元素，不能像next一样取到endMark
        }

        @Override
        public void add(AnyType newValue) {
            if (expectedCount != modCount){
                throw new ConcurrentModificationException();
            }
            if (nextFlag){
                // 添加在输出元素之后，next输出下一个元素
                addBefore(current,newValue);
            }else {
                // 添加在输出元素之前，previous输出添加元素
                addBefore(current,newValue);
            }
            expectedCount ++;
            okToremove = false;
        }

        @Override
        public void set(AnyType newValue) {
            if (expectedCount != modCount){
                throw new ConcurrentModificationException();
            }
            if (nextFlag){
                MyLinkedList.this.set(current.prev, newValue);
            }else {
                MyLinkedList.this.set(current, newValue);
            }
            expectedCount ++;
            okToremove = false;
        }
    }


}
