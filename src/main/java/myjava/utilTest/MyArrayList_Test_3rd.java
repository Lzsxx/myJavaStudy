package myjava.utilTest;

import myjava.util.MyArrayList;
import myjava.util.MyListIterator;

import java.util.ArrayList;
import java.util.ListIterator;

public class MyArrayList_Test_3rd {
    public static void main(String[] args) {
        /**
         * Test set() remove()
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
        System.out.println(myItr.next());   // 2
        myItr.set(-1);
        System.out.println(myItr.previous());   // -1
//        System.out.println(myItr.next());   // -1
//        System.out.println(myItr.next());   // 3
        // 此时是 0 1 -1 3 4 （指向 -1 ， remove会向前移动一位再移除，所以被移除的是1，余下 0 -1 3 4
        myItr.remove();

        MyListIterator myItr2 = myArrayList.listIterator();
        while (myItr2.hasNext()){
            System.out.println(myItr2.next()); // 0 1 -1 3 4
        }

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
        System.out.println(itr.next());   // 2
        itr.set(-1);
        System.out.println(itr.previous());   // -1
//        System.out.println(itr.next());   // -1
//        System.out.println(itr.next());   // 3
        itr.remove();

        ListIterator itr2 = arrayList.listIterator();
        while (itr2.hasNext()){
            System.out.println(itr2.next());
        }

    }
}
