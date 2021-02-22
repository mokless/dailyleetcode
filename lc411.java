/*411. Minimum Unique Word Abbreviation*/

class TrieNode {
    TrieNode[] next;
    boolean isWord;
    
    public TrieNode() {
        this.next = new TrieNode[26];
        this.isWord = false;
    }
    
    public TrieNode buildTrie(Set<String> dictionary) {
        TrieNode root = new TrieNode();
        
        for (String word : dictionary) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.next[c-'a'] == null) {
                    node.next[c-'a'] = new TrieNode();
                }
                
                node = node.next[c-'a'];
            }
            
            node.isWord = true;
        }
        
        return root;
    }
    
    public boolean isValidAbbr(String word, int num) {
        if (num > 0) {
            for (TrieNode node : next){ 
                    if (node != null && node.isValidAbbr(word, num-1)) return true; 
                }
                return false;
        }
        if (word.length() == 0) {
            return isWord;
        }

        int pointer = 0;

        while (pointer < word.length() && word.charAt(pointer) >= '0' && word.charAt(pointer) <= '9') {
            num = num * 10 + word.charAt(pointer++) - '0';
            
        }

        if (num > 0) {
            return isValidAbbr(word.substring(pointer), num);
        }

        if (next[word.charAt(0)-'a'] == null) {
            return false;
        }

        return next[word.charAt(0)-'a'].isValidAbbr(word.substring(1), 0);
        
        
    }
}

class Solution {
    public String minAbbreviation(String target, String[] dictionary) {
        if (target == null || target.length() == 0) {
            return target;
        }
        
        if (dictionary == null || dictionary.length == 0) {
            return target.length() + "";
        }
        
        Set<String> set = new HashSet<>();
        int length = target.length();
        
        for (String word : dictionary) {
            if (word.length() != length) {
                continue;
            }
            set.add(word);
        }
        
        
        
        if (set.size() == 0) {
            return length + "";
        }
        
        
        TrieNode root = new TrieNode();
        root = root.buildTrie(set);
        
        String ans = null;
        
        int left = 1, right = length;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            List<String> listAbbs = new ArrayList<>();
            
            
            helper(target, new StringBuilder(), 0, listAbbs, mid);

            
            boolean conflict = true;
            
            for (String abb : listAbbs) {
                if (!root.isValidAbbr(abb, 0)) {
                    conflict = false;
                    ans = abb;
                    break;
                }
            }

            
            if (conflict) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return ans;
    }
    
    public void helper(String target, StringBuilder sb, int idx, List<String> list, int length) {
        boolean pre = (sb.length() > 0) && (sb.charAt(sb.length() - 1) >= '0') && (sb.charAt(sb.length() - 1) <= '9');
        
        if (length == 1) {
            if (idx < target.length()) {
                if (idx == target.length() - 1) {
                    list.add(sb.toString() + target.charAt(idx));
                }
                
                if (!pre) {
                    list.add(sb.toString() + (target.length() - idx));
                }
            }
            return;
        }
        
        int preLen = sb.length();
        
        for (int i = idx + 1; i < target.length(); i++) {
            if (!pre) {
                sb.append(i - idx);
                helper(target, sb, i, list, length - 1);
                sb.setLength(preLen);
            }
            
            if (i == idx + 1) {
                sb.append(target.substring(idx, i));
                helper(target, sb, i, list, length - 1);
                sb.setLength(preLen);
            }
        }
    }
}
