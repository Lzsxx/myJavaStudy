package myjava.utilTest.tree;

import myjava.util.tree.AvlTree;

public class AvlTree_Test {
    public static void main(String[] args) {
        AvlTree avlTree = new AvlTree();
        avlTree.insert(3);
        avlTree.insert(2);
        avlTree.insert(1);
        avlTree.insert(4);
        avlTree.insert(5);
        avlTree.insert(6);
        avlTree.insert(7);

        avlTree.printTree(avlTree.getRoot());
        System.out.println("======================");
        // 双旋转
        avlTree.insert(16);
        avlTree.insert(15);

        // 双旋转
        avlTree.insert(14);
        // 单旋转
        avlTree.insert(13);
        // 单旋转
        avlTree.insert(12);
        // 单旋转
        avlTree.insert(11);
        // 单旋转
        avlTree.insert(10);

        avlTree.insert(8);
        // 双旋转
        avlTree.insert(9);

        avlTree.printTree(avlTree.getRoot());
    }
}
