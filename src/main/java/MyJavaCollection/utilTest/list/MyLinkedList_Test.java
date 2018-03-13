package MyJavaCollection.utilTest.list;

import MyJavaCollection.util.list.MyIterator;
import MyJavaCollection.util.list.MyLinkedList;
import MyJavaCollection.util.list.MyListIterator;

import java.util.LinkedList;
import java.util.ListIterator;

// test Add Set Get
public class MyLinkedList_Test {
    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(new Integer(0));
        myLinkedList.add(new Integer(1));
        myLinkedList.add(new Integer(2));
        myLinkedList.add(new Integer(3));
        myLinkedList.add(new Integer(4));
        myLinkedList.add(new Integer(5));
        MyIterator<Integer> myLinkItr = myLinkedList.iterator();
        MyListIterator<Integer> myLinkListItr = myLinkedList.listIterator();

        /*** Test Set() ***/
//        myLinkedList.set(3,2);
//        myLinkedList.set(2,3);

        /*** Test remove() ***/
//        myLinkedList.remove(2);

        /*** Test get() ***/
//        System.out.println(myLinkedList.get(0));
//        System.out.println(myLinkedList.get(1));
//        System.out.println(myLinkedList.get(2));
//        System.out.println(myLinkedList.get(3));
//        System.out.println(myLinkedList.get(4));
//
//        /*** Test ListIterator()-remove() ***/
//        if (myLinkListItr.hasNext()){
//            System.out.println(myLinkListItr.next());   // 0
//            System.out.println(myLinkListItr.next());   // 1
//            System.out.println("remove:" + myLinkListItr.remove()); // remove 1
//            System.out.println(myLinkListItr.next());   // 2
//            System.out.println(myLinkListItr.next());   // 3
//            System.out.println(myLinkListItr.previous());   // 3
//            System.out.println(myLinkListItr.previous());   // 2
//            System.out.println("remove:" + myLinkListItr.remove()); // remove 2
//
//            while (myLinkListItr.hasPrevious()){
//                System.out.println(myLinkListItr.previous());
//            }
//            while (myLinkListItr.hasNext()){
//                System.out.println(myLinkListItr.next());
//            }
//        }
//        /*** Test iterator()-add()  ***/
//        if (myLinkListItr.hasNext()){
//            System.out.println(myLinkListItr.next());   // 0
//            System.out.println(myLinkListItr.next());   // 1
//            System.out.println("add");
//            myLinkListItr.add(-1);   // 1之后add -1
//            System.out.println(myLinkListItr.next());   // 2
//            System.out.println(myLinkListItr.next());   // 3
//            System.out.println(myLinkListItr.previous());   // 3
//            System.out.println(myLinkListItr.previous());   // 2
//            System.out.println("add");
//            myLinkListItr.add(-2); // 2之前add -2
//
//            while (myLinkListItr.hasPrevious()){
//                System.out.println(myLinkListItr.previous());
//            }
//            while (myLinkListItr.hasNext()){
//                System.out.println(myLinkListItr.next());
//            }
//        }



//        /*** Test ListIterator()-next() ***/
//        while (myLinkListItr.hasNext()){
//            System.out.println(myLinkListItr.next());
//        }
//        /*** Test ListIterator()-previous() ***/
//        while (myLinkListItr.hasPrevious()){
//            System.out.println(myLinkListItr.previous());
//        }

//        /*** Test ListIterator()-previous() & next() ***/
//        System.out.println(myLinkListItr.next());   // 0
//        System.out.println(myLinkListItr.next());   // 1
//        System.out.println(myLinkListItr.previous());   // 1
//        System.out.println(myLinkListItr.next());   // 1
//        System.out.println(myLinkListItr.next());   // 2
//        System.out.println(myLinkListItr.previous());   // 2
//        System.out.println(myLinkListItr.previous());   // 1


        System.out.println("--------Test set()--------");
        System.out.println(myLinkListItr.next());   // 0
        System.out.println(myLinkListItr.next());   // 1
        myLinkListItr.set(-1);    // 1 变为 -1
        System.out.println(myLinkListItr.previous());   // 1
        System.out.println(myLinkListItr.next());   // 1
        System.out.println(myLinkListItr.next());   // 2
        System.out.println(myLinkListItr.previous());   // 2
        myLinkListItr.set(-2);    // 2 to -2
        System.out.println(myLinkListItr.previous());   // 1


        System.out.println("=================================");

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(new Integer(0));
        linkedList.add(new Integer(1));
        linkedList.add(new Integer(2));
        linkedList.add(new Integer(3));
        linkedList.add(new Integer(4));
        linkedList.add(new Integer(5));
        ListIterator<Integer> linkListItr = linkedList.listIterator();

//        linkedList.set(3,2);
//        linkedList.set(2,3);

//        linkedList.remove(2);
//
//        System.out.println(linkedList.get(0));
//        System.out.println(linkedList.get(1));
//        System.out.println(linkedList.get(2));
//        System.out.println(linkedList.get(3));
//        System.out.println(linkedList.get(4));
//
//        /*** Test iterator()-remove()  ***/
//        if (linkListItr.hasNext()){
//            System.out.println(linkListItr.next());   // 0
//            System.out.println(linkListItr.next());   // 1
//            System.out.println("remove");
//            linkListItr.remove();   // remove 1
//            System.out.println(linkListItr.next());   // 2
//            System.out.println(linkListItr.next());   // 3
//            System.out.println(linkListItr.previous());   // 3
//            System.out.println(linkListItr.previous());   // 2
//            System.out.println("remove");
//            linkListItr.remove(); // remove 2
//
//            while (linkListItr.hasPrevious()){
//                System.out.println(linkListItr.previous());
//            }
//            while (linkListItr.hasNext()){
//                System.out.println(linkListItr.next());
//            }
//        }

//        /*** Test iterator()-add()  ***/
//        if (linkListItr.hasNext()){
//            System.out.println(linkListItr.next());   // 0
//            System.out.println(linkListItr.next());   // 1
//            System.out.println("add");
//            linkListItr.add(-1);   // 1之后add -1
//            System.out.println(linkListItr.next());   // 2
//            System.out.println(linkListItr.next());   // 3
//            System.out.println(linkListItr.previous());   // 3
//            System.out.println(linkListItr.previous());   // 2
//            System.out.println("add");
//            linkListItr.add(-2); // 2之前add -2
//
//            while (linkListItr.hasPrevious()){
//                System.out.println(linkListItr.previous());
//            }
//            while (linkListItr.hasNext()){
//                System.out.println(linkListItr.next());
//            }
//        }

//        /*** Test ListIterator()-previous() & next() ***/
//        System.out.println(linkListItr.next());   // 0
//        System.out.println(linkListItr.next());   // 1
//        System.out.println(linkListItr.previous());   // 1
//        System.out.println(linkListItr.next());   // 1
//        System.out.println(linkListItr.next());   // 2
//        System.out.println(linkListItr.previous());   // 2
//        System.out.println(linkListItr.previous());   // 1

        System.out.println("--------Test set()--------");
        System.out.println(linkListItr.next());   // 0
        System.out.println(linkListItr.next());   // 1
        linkListItr.set(-1);    // 1 变为 -1
        System.out.println(linkListItr.previous());   // 1
        System.out.println(linkListItr.next());   // 1
        System.out.println(linkListItr.next());   // 2
        System.out.println(linkListItr.previous());   // 2
        linkListItr.set(-2);    // 2 to -2
        System.out.println(linkListItr.previous());   // 1


    }
}
