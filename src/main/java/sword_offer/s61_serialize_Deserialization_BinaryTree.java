package sword_offer;

// 请实现两个函数，分别用来序列化和反序列化二叉树

// 思路：序列化用先序遍历，为空的地方用#

public class s61_serialize_Deserialization_BinaryTree {
    public static void main(String[] args) {
        s61_serialize_Deserialization_BinaryTree test = new s61_serialize_Deserialization_BinaryTree();
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);

        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;

        String str = test.Serialize(t1);
        System.out.println(str);

    }

//    String Serialize(TreeNode root) {
//        StringBuilder sb = new StringBuilder();
//        if(root == null){
//            sb.append("#,");
//            return sb.toString();
//        }
//        sb.append(root.val + ",");
//        sb.append(Serialize(root.left));
//        sb.append(Serialize(root.right));
//        return sb.toString();
//  }

    String Serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null){
            return "#";
        }
        processSub(root, sb);
        return sb.toString();
    }
    StringBuilder processSub(TreeNode root, StringBuilder sb) {
        if (sb.length() == 0){
            sb.append( root.val );
        }else {
            sb.append("," +  root.val );
        }
        if (root.left != null){
            sb = processSub(root.left, sb);
        }else {
            sb.append("," + "#");    // 用逗号分割
        }
        if (root.right != null){
            sb = processSub(root.right, sb);
        }else {
            sb.append("," + "#");    // 用逗号分割
        }
        return sb;
    }
    int index = -1;
    TreeNode Deserialize(String str) {
        index ++;
        String[] chs = str.split(",");
        TreeNode node = null;
        if ( !chs[index].equals("#")){
            node = new TreeNode(Integer.parseInt(chs[index]));
            node.left = Deserialize(str);
            node.right = Deserialize(str);
        }
        return node;    // 如果遇到＃号，就返回了，不往下，也不新建对象
    }


    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
}
