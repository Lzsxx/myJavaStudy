package MyJavaCollection.utilTest.list;

import MyJavaCollection.util.list.MyArrayList;
import MyJavaCollection.util.list.MyListIterator;

import java.util.ArrayList;
import java.util.ListIterator;

public class MyArrayList_Test {
    public static void main(String[] args) {
        /**
         * Test next() previous()
         */
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(new Integer(0));
        myArrayList.add(new Integer(1));
        myArrayList.add(new Integer(2));
        myArrayList.add(new Integer(3));
        myArrayList.add(new Integer(4));
        MyListIterator myItr = myArrayList.listIterator();
        System.out.println(myItr.next());   // 0
        System.out.println(myItr.next());   // 1
        System.out.println(myItr.previous());   // 1
        System.out.println(myItr.next());   // 1
        System.out.println(myItr.next());   // 2
        System.out.println(myItr.next());   // 3
        System.out.println(myItr.next());   // 4
        System.out.println(myItr.previous());   // 4
        System.out.println(myItr.previous());   // 3


        System.out.println("----------------");

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(new Integer(0));
        arrayList.add(new Integer(1));
        arrayList.add(new Integer(2));
        arrayList.add(new Integer(3));
        arrayList.add(new Integer(4));

        ListIterator itr = arrayList.listIterator();
        System.out.println(itr.next());   // 0
        System.out.println(itr.next());   // 1
        System.out.println(itr.previous());   // 1
        System.out.println(itr.next());   // 1
        System.out.println(itr.next());   // 2
        System.out.println(itr.next());   // 3
        System.out.println(itr.next());   // 4
        System.out.println(itr.previous());   // 4
        System.out.println(itr.previous());   // 3
    }
}
