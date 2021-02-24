class TrieNode {
    TrieNode[] next;
    boolean isWord;
    
    public TrieNode() {
        this.next = new TrieNode[26];
        this.isWord = false;
    }
}


class WordDictionary {
    TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        this.root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.next[c-'a'] == null) {
                node.next[c-'a'] = new TrieNode();
            }
            
            node = node.next[c-'a'];
        }
        
        node.isWord = true;
    }
    
    public boolean search(String word) {
        TrieNode node = root;
        return helper(node, 0, word);
    }
    
    public boolean helper(TrieNode node, int start, String word) {
        if (start == word.length()) {
            return node.isWord;
        }
        
        char c = word.charAt(start);
        
        if (c == '.') {
            for (int i = 0; i < 26; i++) {
                if (node.next[i] != null) {
                    if (helper(node.next[i], start + 1, word)) {
                        return true;
                    }
                }
            }
            
            return false;
        }
        
        if (node.next[c-'a'] == null) {
            return false;
        }
        
        return helper(node.next[c-'a'], start + 1, word);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
