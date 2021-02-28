class TrieNode {
    TrieNode[] next = new TrieNode[26];
    int isWordIdx = -1;
    List<Integer> palindromePrefixRemaining = new ArrayList<>();
}


class Solution {
    
    public boolean hasPalindromeRemaining(String s, int i) {
        int p1 = i;
        int p2 = s.length() - 1;
        
        while (p1 < p2) {
            if (s.charAt(p1) != s.charAt(p2)) {
                return false;
            }
            
            p1++;
            p2--;
        }
        
        return true;
    }
    public List<List<Integer>> palindromePairs(String[] words) {
        TrieNode root = new TrieNode();
        for (int wordId = 0; wordId < words.length; wordId++) {
            String word = words[wordId];
            String reversedWord = new StringBuilder(word).reverse().toString();
            TrieNode node = root;
            for (int i = 0; i < reversedWord.length(); i++) {
                if (hasPalindromeRemaining(reversedWord, i)) {
                    node.palindromePrefixRemaining.add(wordId);
                }
                
                char c = reversedWord.charAt(i);
                if (node.next[c-'a'] == null) {
                    node.next[c-'a'] = new TrieNode();
                }
                
                node = node.next[c-'a'];
            }
            node.isWordIdx = wordId;
        }
        
        List<List<Integer>> ans = new ArrayList<>();
        for (int wordId = 0; wordId < words.length; wordId++) {
            String word = words[wordId];
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                if (node.isWordIdx != -1 && hasPalindromeRemaining(word, i)) {
                    ans.add(Arrays.asList(wordId, node.isWordIdx));
                }
                
                char c = word.charAt(i);
                node = node.next[c-'a'];
                if (node == null) {
                    break;
                }
                
                
            }
            if (node == null) {
                continue;
            }
            
            if (node.isWordIdx != -1 && node.isWordIdx != wordId) {
                ans.add(Arrays.asList(wordId, node.isWordIdx));
            }
            
            for (int other : node.palindromePrefixRemaining) {
                ans.add(Arrays.asList(wordId, other));
            }
        }
        
        return ans;
    }
}
