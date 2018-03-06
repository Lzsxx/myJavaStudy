package myjava.utilTest.tree;


import myjava.util.tree.BinarySearchTree;

public class BinarySearchTree_Test {

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(3);
        tree.insert(6);
        tree.insert(5);
        tree.insert(2);
        tree.insert(7);
        tree.insert(7);
        tree.insert(8);
        tree.insert(9);
        tree.insert(1);

        int max = tree.findMax(tree.getRoot(), true);
        System.out.println("The Max is :" + max);

        int min = tree.findMin(tree.getRoot(), true);
        System.out.println("The Min is :" + min);

        System.out.println("Print the Tree:");
        tree.printTree(tree.getRoot());

        boolean flag = tree.contains(1);
        System.out.println("If contains 1 ? " + flag);

         flag = tree.contains(99);
        System.out.println("If contains 99 ? " + flag);

//        System.out.println("Remove leave Node: 1");
//        tree.remove(1);
//        tree.printTree(tree.getRoot());

//        System.out.println("Remove one-child Node: 8");
//        tree.remove(8);
//        tree.printTree(tree.getRoot());

        System.out.println("Remove two-child Node: 6");
        tree.remove(6);
        tree.printTree(tree.getRoot());


    }

}
