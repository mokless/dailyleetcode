class WordFilter {
    TrieNode root;

    public WordFilter(String[] words) {
        this.root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            String word = words[i] + "{";
            for (int j = 0; j < word.length(); j++) {
                TrieNode node = root;
                node.idx = i;
                
                for (int k = j; k < 2 * word.length() - 1; k++) {
                    int m = word.charAt(k % word.length()) - 'a';
                    if (node.next[m] == null) {
                        node.next[m] = new TrieNode();
                    }
                    
                    node = node.next[m];
                    node.idx = i;
                }
            }
        }
    }
    
    public int f(String prefix, String suffix) {
        TrieNode node = root;
        for (char c : (suffix + '{' + prefix).toCharArray()) {
            if (node.next[c-'a'] == null) {
                return -1;
            }
            node = node.next[c-'a'];
        }
        
        return node.idx;
    }
}

class TrieNode {
    TrieNode[] next;
    int idx;
    
    public TrieNode() {
        this.next = new TrieNode[27];
        this.idx = 0;
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */
