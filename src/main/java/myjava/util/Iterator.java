package myjava.util;

public interface Iterator<E> {
     E next();
     boolean hasNext();
     E remove(E removeValue);
}
