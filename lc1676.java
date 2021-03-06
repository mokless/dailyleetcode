class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        if (root == null) {
            return null;
        }
        
        for (TreeNode node : nodes) {
            if (root == node) {
                return root;
            }
        }
        
        TreeNode left = lowestCommonAncestor(root.left, nodes);
        TreeNode right = lowestCommonAncestor(root.right, nodes);
        
        if (left == null) {
            return right;
        }
        
        if (right == null) {
            return left;
        }
        
        return root;
    }
}
