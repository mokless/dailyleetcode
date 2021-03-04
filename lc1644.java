class Solution {
    int count = 0;
	
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode LCA = helper(root, p, q);
        return count == 2 ? LCA : null;
    }
    
    public TreeNode helper(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }
        
        TreeNode left = helper(root.left, p, q);   
        
        TreeNode right = helper(root.right, p, q);
        
        if (root == p || root == q) {
            count++;
            return root;
        }
        
        return left == null ? right : right == null ? left : root;
    }
}
