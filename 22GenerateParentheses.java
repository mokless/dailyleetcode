class Solution {
    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        
        List<String> ans = new ArrayList<>();
        
        helper("", 0, 0, n, ans);
        
        return ans;
    }
    
    public void helper(String tmp, int start, int end, int n, List<String> ans) {
        if (tmp.length() == 2 * n) {
            ans.add(tmp);
            return;
        }
        
        if (start < n) {
            
            helper(tmp + "(", start + 1, end, n, ans);
        }
        
        if (end < start) {
            
            helper(tmp + ")", start, end + 1, n, ans);
        }
    }
}
