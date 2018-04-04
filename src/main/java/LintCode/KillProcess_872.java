package LintCode;

import java.util.*;

class KillProcess {
     public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
         // Write your code here
         // 思路整理：pid是进程id,ppid是对应进程的父进程，每当要杀掉一个进程，就在ppid中查找，它是否是某个其他进程的父进程，如果是，
         // 则继续找该进程是否是某个进程的父进程，需要注意的是，每次找到的父进程，可能不只是一个进程的父亲，可能是多个的父亲，所以要从头到尾遍历
         List<Integer> list = new LinkedList<>();
         Queue<Integer> queue = new LinkedList<>();
         if (kill <= 0){
             return list;
         }
         queue.add(kill);
         while ( !queue.isEmpty() ){
             int temp = queue.remove();
             list.add(temp);
             for (int i = 0; i < ppid.size(); i++){
                 if (temp == ppid.get(i)){
                     queue.add(pid.get(i)); //传入的kill有孩子 pid.get(i),而且可能不止一个
                 }
             }
         }
         return list;
     }

     /***** 递归版 *****/
//     List<Integer> list = new ArrayList<>();
//    Set<Integer> set = new LinkedHashSet<>();
//    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
//        // Write your code here
//        // 思路：kill可能不仅在pid中，也在ppid中，则需要扫描ppid,对应的id也要再拿来扫描ppid
//        if (!set.contains(kill)){
//            list.add(kill);
//            set.add(kill);
//        }
//        for (int i = 0; i < ppid.size(); i++) {
//            if (kill == ppid.get(i)){   //有子进程
//                killProcess(pid, ppid, pid.get(i));
//            }
//        }
//        return list;
//    }

}
