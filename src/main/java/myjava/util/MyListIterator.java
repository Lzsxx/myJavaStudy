package myjava.util;

public interface MyListIterator<AnyType> extends MyIterator<AnyType> {
    AnyType previous();
    boolean hasPrevious();
    void add(AnyType newValue);
    void set(AnyType newValue);
}
