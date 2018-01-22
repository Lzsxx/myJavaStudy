package myjava.util;

public interface MyIterable<AnyType> {
    MyIterator<AnyType> iterator();
    void forEach();
}
