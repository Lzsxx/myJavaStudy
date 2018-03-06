package myjava.utilTest.list;

import myjava.util.list.MyArrayList;
import myjava.util.list.MyListIterator;

import java.util.ArrayList;
import java.util.ListIterator;

public class MyArrayList_Test_2nd {
    public static void main(String[] args) {

        /**
         * test add()
         */

        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(new Integer(0));
        myArrayList.add(new Integer(1));
        myArrayList.add(new Integer(2));
        myArrayList.add(new Integer(3));
        MyListIterator myItr = myArrayList.listIterator();
        while (myItr.hasNext()){
            int current = (int) myItr.next();
//            System.out.println(current);
            if ( current > 1 && current < 5 ){
                myItr.add(99);      // 2,3后面添加99，但99不会被下一次遍历到，得 0 1 2 99 3 99
            }
        }

        while (myItr.hasPrevious()){
            int current = (int) myItr.previous();
            System.out.println(current);
            if (current > 1) {
                // itr.add(99);    // 死循环了，因为新加的99会被遍历到
                myItr.add(-1);    // 2 99 3 99 前面加上-1，下一次会被遍历到，但由于 !> 1 不进入循环,得 0 1 -1 2 -1 99 -1 3 -1 99
            }
        }

        System.out.println("----------------");

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(new Integer(0));
        arrayList.add(new Integer(1));
        arrayList.add(new Integer(2));
        arrayList.add(new Integer(3));

        ListIterator itr = arrayList.listIterator();
        while (itr.hasNext()){
            int current = (int) itr.next();
//            System.out.println(current);
            if ( current > 1 && current < 5 ){
                itr.add(99);      // 2,3后面添加99，但99不会被下一次遍历到，得 0 1 2 99 3 99
            }
        }

        while (itr.hasPrevious()){
            int current = (int) itr.previous();
            System.out.println(current);
            if (current > 1 ) {
                // itr.add(99);    // 死循环了，因为新加的99会被遍历到
                itr.add(-1);    // 2 99 3 99 前面加上-1，下一次会被遍历到，但由于 !> 1 不进入循环,得 0 1 -1 2 -1 99 -1 3 -1 99
            }
        }
    }
}
