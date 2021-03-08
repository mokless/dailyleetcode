/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    TreeNode x;
    TreeNode y;
    TreeNode prev;
    
    public void swap(TreeNode node1, TreeNode node2) {
        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }
    
    public void helper(TreeNode node) {
        if (node == null) {
            return;
        }
        
        helper(node.left);
        
        if (prev != null && node.val < prev.val) {
            x = node;
            if (y == null) {
                y = prev;
            } else {
                return;
            }
        }
        
        prev = node;
        
        helper(node.right);
        
        
    }
    public void recoverTree(TreeNode root) {
        helper(root);
        swap(x, y);
    }
}
