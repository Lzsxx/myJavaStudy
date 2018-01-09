package myjava.util;

public interface Collection<E> {
    int size();
    boolean isEmpty();
    void clear();
    void add(E newValue);
    E remove();
    boolean contains(E findValue);
    Iterator<E> iterator();
}
