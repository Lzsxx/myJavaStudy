package MyJavaCollection.util.list;

public interface MyList<AnyType> extends MyCollection<AnyType> {
    AnyType set(int idx, AnyType newValue);
    AnyType get(int idx);
    boolean add(int idx, AnyType newValue);
    AnyType remove(int idx);
    MyListIterator<AnyType> listIterator();
}
