package com.hwx.daydayup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * created by houwanxing1 on 2019/2/16
 */
public class TreeNodeType {

    class TreeNode{
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val){
            this.val = val;
        }
    }

    class ListNode{
        private int val;
        private ListNode next;

        public ListNode(int val){
            this.val = val;
        }
    }

    /**
     * 中序遍历二叉树
     * @param root
     * @return
     */
    private static List<Integer> inOrderTraversal(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        inOrderHelper(root, result);

        return result;
    }

    private static void inOrderHelper(TreeNode root, List list){
        if(root == null){
            return;
        }

        inOrderHelper(root.left, list);
        list.add(root.val);
        inOrderHelper(root.right, list);
    }

    /**
     * 前序遍历二叉树
     * @param root
     * @return
     */
    public static List<Integer> preOrderTraversal(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        preOrderHelper(root, result);

        return result;
    }

    private static void preOrderHelper(TreeNode root, List list){
        if(root == null){
            return;
        }

        list.add(root.val);
        inOrderHelper(root.left, list);
        inOrderHelper(root.right, list);
    }

    /**
     * 后序遍历二叉树
     * @param root
     * @return
     */
    public static List<Integer> postOrderTraversal(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        postOrderHelper(root, result);

        return result;
    }

    private static void postOrderHelper(TreeNode root, List list){
        if(root == null){
            return;
        }

        inOrderHelper(root.left, list);
        inOrderHelper(root.right, list);
        list.add(root.val);
    }

    /**
     * 层次遍历二叉树DFS
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderDFS(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        layerHelper(root, 1 , result);

        return result;
    }

    private static void layerHelper(TreeNode root, int layer, List<List<Integer>> list){
        if(root == null){
            return;
        }

        if(layer > list.size()){
            list.add(new ArrayList<>());
        }

        list.get(layer - 1).add(root.val);
        layerHelper(root.left, layer + 1, list);
        layerHelper(root.right, layer + 1, list);
    }

    /**
     * 层次遍历二叉树BFS
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList();

        if(root == null){
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for(int i = 0;i < levelSize;i++){
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);
                if(currentNode.left != null){
                    queue.add(currentNode.left);
                }
                if(currentNode.right != null){
                    queue.add(currentNode.right);
                }
            }

            result.add(currentLevel);
        }

        return result;
    }

    /**
     * 验证二叉搜索树
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root){
        return isValidBSTHelper(root, (long)Integer.MIN_VALUE - 1, (long)Integer.MAX_VALUE + 1);
    }

    /**
     * 验证搜索二叉树的helper
     * @param root
     * @param min
     * @param max
     * @return
     */
    private static boolean isValidBSTHelper(TreeNode root, long min, long max){
        boolean flag = true;
        if(root == null){
            return flag;
        }

        if(root.val <= min || root.val >= max){
            return false;
        }

        flag = isValidBSTHelper(root.left, min, root.val);
        if(flag){
            flag = isValidBSTHelper(root.right, root.val, max);
        }

        return flag;
    }

    /**
     * 寻找最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        if(root == null){
            return root;
        }

        if(root == p || root == q){
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null){
            return root;
        }else if(left != null){
            return left;
        }else if(right != null){
            return right;
        }else{
            return null;
        }
    }

    /**
     * 二叉树的最大深度
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root){
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 二叉树的最小深度
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root){
        if(root == null){
            return 0;
        }

        if(root.left == null){
            return minDepth(root.right) + 1;
        }

        if(root.right == null){
            return minDepth(root.left) + 1;
        }

        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    private static int depthOfTree(TreeNode root){
        if(root == null){
            return 0;
        }

        if(root.left == null){
            return depthOfTree(root.right) + 1;
        }

        if(root.right == null){
            return depthOfTree(root.left) + 1;
        }

        return Math.max(depthOfTree(root.left), depthOfTree(root.right)) + 1;
    }

    /**
     * 生成有效括号
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateOneByOne("", result, n, n);
        return result;
    }

    private static void generateOneByOne(String subList, List<String> list, int left, int right){
        if(left == 0 && right == 0){
            list.add(subList);
            return;
        }

        if(left > 0){
            generateOneByOne(subList + "(", list, left - 1, right);
        }

        if(right > left){
            generateOneByOne(subList + ")", list, left, right - 1);
        }
    }

    /**
     * 翻转二叉树
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }

        if(root.left == null && root.right == null){
            return root;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        //翻转第一层级
        root.right = left;
        root.left = right;

        //翻转下一层级
        if(root.right != null){
            invertTree(root.right);
        }
        if(root.left != null){
            invertTree(root.left);
        }

        return root;
    }

    /**
     * 使用BFS方式翻转二叉树
     * @param root
     * @return
     */
    public TreeNode invertTreeBFS(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root == null){
            return root;
        }
        queue.add(root);
        while(!queue.isEmpty()){
            int count = queue.size();
            while(count > 0){
                TreeNode node = queue.poll();
                TreeNode left = node.left;
                node.left = node.right;
                node.right = left;
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
                count--;
            }
        }
        return root;
    }

    /**
     * 完全二叉树的节点个数
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        //递推公式：height(node) = height(node.left) + height(node.right) + 1
        if(root == null){
            return 0;
        }

        TreeNode left = root;
        TreeNode right = root;
        int height = 0;

        while(right != null){
            left = left.left;
            right = right.right;
            height++;
        }

        //满二叉树
        if(left == null){
            return (1 << height) - 1;
        }else{
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    /**
     * 二叉树展开为链表
     * @param root
     */
    //把左子树作为右子树，并把原右子树拼接在现右子树的最右端
    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }

        flatten(root.left);
        flatten(root.right);

        if(root.left != null){
            //记录右节点
            TreeNode right = root.right;
            root.right = root.left;
            //将左节点置空
            root.left = null;
            TreeNode last = root.right;
            while(last.right != null){
                last = last.right;
            }

            last.right = right;
        }
    }

    /**
     * 二叉搜索树中插入元素
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }

        if(root.val == val){
            return root;
        }

        if(root.val < val){
            if(root.right == null){
                root.right = new TreeNode(val);
            }else{
                insertIntoBST(root.right, val);
            }
        }else if(root.val > val){
            if(root.left == null){
                root.left = new TreeNode(val);
            }else{
                insertIntoBST(root.left, val);
            }
        }

        return root;
    }

    /**
     * 有序链表转换为二叉搜索树
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null){
            return null;
        }

        return sortedListToBST(head, null);
    }

    private TreeNode sortedListToBST(ListNode head, ListNode tail){
        if(head == tail){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast != tail && fast.next != tail){
            slow = slow.next;
            fast = fast.next.next;
        }
        //到中点了
        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head, slow);
        root.right = sortedListToBST(slow.next, tail);

        return root;
    }
    
        /**
     * 二叉树的层平均值
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<Double>();
        if(root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            int count = size;
            double sum = 0.0;
            while(size > 0){
                TreeNode node = queue.poll();
                sum += node.val;
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
                size--;
            }
            res.add(sum/count);
        }
        return res;
    }
    
    /**
     * 二叉树中和为某一值的路径
     * @param root
     * @param expectedSum
     * @return
     */
    public List<List<Integer>> findPath(TreeNode root, int expectedSum){
        List<List<Integer>> pathList = new ArrayList<>();
        
        if(root == null){
            return pathList;
        }
        
        Stack<Integer> path = new Stack<>();
        findPathHelper(root, expectedSum, path, pathList);
        
        return pathList;
    }
    
    private void findPathHelper(TreeNode root, int expectedSum, Stack<Integer> path, List<List<Integer>> pathList){
        if(root == null){
            return;
        }
        
        if(root.left == null && root.right == null){
            if(root.val == expectedSum){
                List<Integer> list = new ArrayList<>();
                for(int value : path){
                    list.add(value);
                }
                list.add(root.val);
                pathList.add(list);
            }
        }else{
            path.push(root.val);
            findPathHelper(root.left, expectedSum - root.val, path, pathList);
            findPathHelper(root.right, expectedSum - root.val, path, pathList);
            
            path.pop();
        }
    }
}
