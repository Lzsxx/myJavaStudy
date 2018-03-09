package myjava.utilTest.tree;

import myjava.util.tree.AvlTree;

public class AvlTree_Test {
    public static void main(String[] args) {
        example_2();
    }

    public static void example_1(){
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

    public static void example_2(){
        AvlTree avlTree = new AvlTree();
        avlTree.insert(10);
        avlTree.insert(85);
        avlTree.insert(15);
        avlTree.insert(70);
        avlTree.insert(20);
        avlTree.insert(60);
        avlTree.insert(30);
        avlTree.insert(50);
        avlTree.insert(65);
        avlTree.insert(80);
        avlTree.insert(90);
        avlTree.insert(40);
        avlTree.insert(5);
        avlTree.insert(55);

        avlTree.printTree(avlTree.getRoot());
    }
}
