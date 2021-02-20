class Solution {
    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        
        List<List<String>> ans = new ArrayList<>();
        
        helper(ans, 0, new ArrayList<>(), s);
        
        return ans;
    }
    
    public void helper(List<List<String>> ans, int idx, List<String> list, String s) {
        if (idx >= s.length()) {
            ans.add(new ArrayList<>(list));
            return;
        }
        
        for (int i = idx; i < s.length(); i++) {
            if (valid(idx, i, s)) {
                list.add(s.substring(idx, i + 1));
                helper(ans, i + 1, list, s);
                list.remove(list.size() - 1);
            }
            
        }
    }
    
    public boolean valid(int start, int end, String s) {
        while (start <= end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        
        return true;
    }
}
