package LintCode;

import com.sun.corba.se.spi.orb.ParserImplBase;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 *  */
   interface NestedInteger {

      // @return true if this NestedInteger holds a single integer,
      // rather than a nested list.
      public boolean isInteger();

      // @return the single integer that this NestedInteger holds,
      // if it holds a single integer
      // Return null if this NestedInteger holds a nested list
      public Integer getInteger();

      // @return the nested list that this NestedInteger holds,
      // if it holds a nested list
      // Return null if this NestedInteger holds a single integer
      public List<NestedInteger> getList();
  }

class NestedIterator implements Iterator {

    private List<NestedInteger> list;
    private int nextItem = -1;

    public NestedIterator(List<NestedInteger> nestedList) {
        // Initialize your data structure here.
        list = nestedList;
    }


    // @return {int} the next element in the iteration
    @Override
    public Integer next() {
        // Write your code here
        return nextItem;
    }

    // @return {boolean} true if the iteration has more element or false
    @Override
    public boolean hasNext() {
        // Write your code here
        boolean flag = false;
        if ( list.size() > 0){
            while ( list.size() > 0 ){
                NestedInteger item = list.remove(0);
                if (item.isInteger()){
                    flag = true;
                    nextItem = item.getInteger();
                    return flag;
                }else {
                    List<NestedInteger> itemList = item.getList();
                    if (itemList.size() > 0){
                        for (int i = itemList.size() - 1; i >= 0 ; i--) {
                            list.add(0, itemList.get(i));
                        }
                    }
                }
            }
        }
        return flag;
    }

    @Override
    public void remove() {}
}



/***** 递归版，个人感觉无错，但会超时 *****/
// class NestedIterator implements Iterator {
//
//        private List<Integer> list = null;
//        private int curr = -1;
//
//         public NestedIterator(List<NestedInteger> nestedList) {
//             // Initialize your data structure here.
//             list = new LinkedList<>();
//             processList(nestedList);
//         }
//
//         public void processList( List<NestedInteger> nestedList ){
//             for (NestedInteger item: nestedList ) {
//                 if (item.isInteger()){
//                     list.add(item.getInteger());
//                 }else {
//                     processList(item.getList());
//                 }
//             }
//         }
//
//         // @return {int} the next element in the iteration
//         @Override
//         public Integer next() {
//             // Write your code here
//             return list.get( ++curr );
//         }
//
//         // @return {boolean} true if the iteration has more element or false
//         @Override
//         public boolean hasNext() {
//             // Write your code here
//             return curr < list.size() - 1 ;
//         }
//
//         @Override
//         public void remove() {}
//   }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v.add(i.next());
 */
