package myjava.util;

public interface List<E> extends Collection<E>{
    E set(int idx, E newValue);
    E get(int idx);
    boolean add(int idx, E newValue);   // 继承Collection本身需要有不带形参的add和remove,但根据List的特点，还需要设置指定index加入数据的方法
    E remove(int idx);
    ListIterator<E> listIterator();
}
