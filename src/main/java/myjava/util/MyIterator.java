package myjava.util;

public interface MyIterator<AnyType> {
     AnyType next();
     boolean hasNext();
     AnyType remove();
}
