package myjava.util.list;

public interface MyIterator<AnyType> {
     AnyType next();
     boolean hasNext();
     AnyType remove();
}
