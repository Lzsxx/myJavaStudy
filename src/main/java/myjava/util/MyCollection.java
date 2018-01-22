package myjava.util;

public interface MyCollection<AnyType> {
    int size();
    boolean isEmpty();
    boolean clear();
    boolean add(AnyType newValue);
    boolean remove(AnyType removeValue);
    boolean contains(AnyType findValue);
    MyIterator<AnyType> iterator();
}
