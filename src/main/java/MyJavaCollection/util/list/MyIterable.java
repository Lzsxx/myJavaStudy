package MyJavaCollection.util.list;

public interface MyIterable<AnyType> {
    MyIterator<AnyType> iterator();
    void forEach();
}
