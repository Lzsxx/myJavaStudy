package LintCode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/***** 题目：为最近最少使用（LRU）缓存策略设计一个数据结构，它应该支持以下操作：获取数据（get）和写入数据（set）。
 获取数据get(key)：如果缓存中存在key，则获取其数据值（通常是正数），否则返回-1。
 写入数据set(key, value)：如果key还没有在缓存中，则写入其数据值。当缓存达到上限，
 它应该在写入新数据之前删除最近最少使用的数据用来腾出空闲位置。  *****/

/***** 解题思路：主要是用一个双向链表来存储节点，节点中包含键值对，map中存储key和节点，
 * 通过在map中查找key来定位node，所有最近被访问的数据被放在链表尾部，因此get方法中需要
 * 在查找到value之后将节点取出放到尾部，set中类似的考虑。 *****/

// 总结，其实就是自己实现一个能够访问后重排序的 LinkedHashMap

class LRUCache {
    public class Node{
        int val;
        int key;
        Node prev;
        Node next;

        public Node( int key, int val) {
            this.val = val;
            this.key = key;
            this.prev = null;
            this.next = null;
        }
    }
    public int capacity;
    public Node head;   //双向链表的头节点
    public Node tail;   //尾节点
    public Map<Integer, Node> map;  //基础map
    /*
     * @param capacity: An integer
     */public LRUCache(int capacity) {
        // do intialization if necessary
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        // write your code here
        // 每次访问，根据key来确定是否存在，如果map中不存在，就返回-1；
        // 如果存在，就返回已有的值，并将刚刚访问过的节点移动到链表尾部，需要注意的是，移动节点只是修改链表的指针，不改动map
        if (map.get(key) == null){
            return -1;
        }
        // 找到了
        Node node = map.get(key);
        // 先处理周围的关系
        deleteNodeFromLink(node);
        move_to_tail(node);
        return node.val;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        // write your code here
        // set时先看map里是否存在，如果有，就只是简单地更新值
        // 如果没有，还要先看有没有空间，如果空间不足，就要先删除链表头的元素，再添加
        // 注意，添加元素的时候，是直接添加到链表尾部

        if (map.get(key) != null){  // 已存在
            Node node = map.get(key);
            node.val = value;
            deleteNodeFromLink(node);
            move_to_tail(node);
            return;
        }
        // 没有已存在的Node，就创建，创建之前判断容量
        if (map.size() == capacity){
            // 删除头节点后的Node
            map.remove(head.next.key);  //要先删除，再移动链表指针，否则会找不到在map中要删除的是哪个Node
            deleteNodeFromLink(head.next);
        }
        Node newlyNode = new Node(key, value);
        map.put(key, newlyNode);
        move_to_tail(newlyNode);
    }
    public void deleteNodeFromLink(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    public void move_to_tail(Node node){
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }
}