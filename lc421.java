class TrieNode {
    TrieNode[] next = new TrieNode[2];
}


class Solution {
    public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int maxNum = nums[0];
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }
        
        int length = Integer.toBinaryString(maxNum).length();
        
        int n = nums.length;
        int bitmask = 1 << length;
        String[] strNums = new String[n];
        
        
        for (int i = 0; i < n; i++) {
            strNums[i] = Integer.toBinaryString(bitmask | nums[i]).substring(1);
        }
        
        TrieNode root = new TrieNode();
        int ans = 0;
        
        for (String num : strNums) {
            TrieNode node = root;
            TrieNode xorNode = root;
            int current = 0;
            
            for (char c : num.toCharArray()) {
                if (node.next[c-'0'] == null) {
                    node.next[c-'0'] = new TrieNode();
                }
                
                node = node.next[c-'0'];
                
                char bit = c == '1' ? '0' : '1';
                
                if (xorNode.next[bit-'0'] != null) {
                    current = (current << 1) | 1;
                    xorNode = xorNode.next[bit-'0'];
                } else {
                    current = current << 1;
                    xorNode = xorNode.next[c-'0'];
                }
            }
            
            ans = Math.max(ans, current);
        }
        
        return ans;
    }
}

