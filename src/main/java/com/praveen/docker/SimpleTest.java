package com.praveen.docker;


import com.sun.source.tree.Tree;

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i=0;i< size;i++){
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
            }
            result.add(list);
        }
        return result;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(list,root);
        return list;
    }

    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postOrder(list,root);
        return list;
    }

    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrder(list,root);
        return list;
    }


    void preOrder(List<Integer> list, TreeNode root){
        if(root!=null){
            list.add(root.val);
            preOrder(list,root.left);
            preOrder(list,root.right);
        }
    }

    void postOrder(List<Integer> list, TreeNode root){
        if(root!=null){
            postOrder(list,root.left);
            postOrder(list,root.right);
            list.add(root.val);
        }
    }

    void inOrder(List<Integer> list, TreeNode root){
        if(root!=null){
            inOrder(list,root.left);
            list.add(root.val);
            inOrder(list,root.right);
        }
    }

    public List<Integer> inOrder(TreeNode treeNode) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = treeNode;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                 curr = stack.pop();
                list.add(curr.val);
                curr = curr.right;
            }
        }
        return list;
    }

    public List<Integer> preOrder(TreeNode treeNode) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);
        TreeNode curr = stack.peek();
        while (!stack.isEmpty()) {
            curr = stack.pop();
            list.add(curr.val);
            if(curr.right!=null) stack.push(curr.right);
            if(curr.left!=null) stack.push(curr.left);
        }
        return list;
    }

    public List<Integer> postOrder(TreeNode treeNode) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);
        TreeNode curr = null;
        while (!stack.isEmpty()) {
            curr = stack.pop();
            list.addFirst(curr.val);
            if(curr.left!=null) stack.push(curr.left);
            if(curr.right!=null) stack.push(curr.right);
        }
        return list;
    }
    int maxDiameter  = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return maxDiameter;
    }
    public int depth(TreeNode root){
        if(root==null) return 0;
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        maxDiameter = Math.max(maxDiameter,(leftDepth+rightDepth));
        return Math.max(leftDepth,rightDepth)+1;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || root==p || root==q) return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left!=null && right!=null) return root;
        return (left!=null) ? root.left :root.right;
    }

    public Node cloneGraph(Node node) {
        if(node==null) return null;
        HashMap<Node,Node> map = new HashMap<>();
        return dfs(node,map);
    }
    private Node dfs(Node node,Map<Node,Node> map){
        if(map.containsKey(node)) return map.get(node);

        Node clone = new Node(node.val);
        map.put(node,clone);
        for(Node neighbour : node.neighbors){
            clone.neighbors.add(dfs(neighbour,map));
        }
        return clone;
    }
}

public class SimpleTest {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode rt = new TreeNode(20);
        root.right = rt;
        rt.left =   new TreeNode(15);
        rt.right = new TreeNode(7);
        System.out.println(s.levelOrder(root));
        System.out.println(s.inorderTraversal(root));
        System.out.println(s.inOrder(root));
        System.out.println(s.preOrderTraversal(root));
        System.out.println(s.preOrder(root));
        System.out.println(s.postOrderTraversal(root));
        System.out.println(s.postOrder(root));
        System.out.println(s.diameterOfBinaryTree(root));
        System.out.println(s.lowestCommonAncestor(root,rt,rt.left));
        Queue<TreeNode> queue = new LinkedList<>();

    }
}

