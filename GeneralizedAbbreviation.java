class Solution {
    List<String> ans;
    String word;
    
    public List<String> generateAbbreviations(String word) {
        if (word == null || word.length() == 0) {
            return new ArrayList<>();
        }
        
        this.ans = new ArrayList<>();
        this.word = word;
        
        helper(new StringBuilder(), 0, 0);
        
        return this.ans;
    }
    
    public void helper(StringBuilder sb, int idx, int count) {
        int length = sb.length();
        
        if (idx == word.length()) {
            if (count != 0) {
                sb.append(count);
            }
            ans.add(sb.toString());
        } else {
            helper(sb, idx + 1, count + 1);
        
            if (count != 0) {
                sb.append(count);            
            }
            sb.append(word.charAt(idx));

            helper(sb, idx + 1, 0);
        }
        
        sb.setLength(length);
    }
}
