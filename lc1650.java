class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        if (p == null || q == null) {
            return null;
        }
        
        Node pc = p, qc = q;
        
        while (pc != qc) {
            pc = pc == null ? q : pc.parent;
            qc = qc == null ? p : qc.parent;
        }
        
        return pc;
    }
}
