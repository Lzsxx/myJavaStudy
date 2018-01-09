package myjava.util;

public interface ListIterator<AnyType> extends Iterator<AnyType> {
    AnyType previous();
    boolean hasPrevious();
    void add(AnyType x);
    void set(AnyType newValue);
}
