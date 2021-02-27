class TrieNode {
    TrieNode[] next = new TrieNode[2];
    int val;

    public void add(TrieNode root, int n) {
        TrieNode node = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (n >> i) & 1;
            if (node.next[bit] == null) {
                node.next[bit] = new TrieNode();
            }
            node = node.next[bit];
        }
        node.val = n;
    }

    public int search(TrieNode root, int n) {
        TrieNode node = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (n >> i) & 1;
            int requiredBit = bit == 1 ? 0 : 1;
            if (node.next[requiredBit] != null) {
                node = node.next[requiredBit];
            } else {
                node = node.next[bit];
            }
        }
        return node.val ^ n;
    }
}

class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        if (nums == null || nums.length == 0 || queries == null || queries.length == 0) {
            return new int[0];
        }
        
        int length = queries.length;
        int[] ans = new int[length];
        int[][] tmp = new int[length][3];
        
        for (int i = 0; i < length; i++) {
            tmp[i][0] = queries[i][0];
            tmp[i][1] = queries[i][1];
            tmp[i][2] = i;
        }
        
        Arrays.sort(tmp, (a, b) -> (a[1] - b[1]));
        Arrays.sort(nums);
        
        int idx = 0;
        TrieNode root = new TrieNode();
        
        
        for (int query[] : tmp) {
            while (idx < nums.length && nums[idx] <= query[1]) {
                root.add(root, nums[idx]);
                idx++;
            }
            int tempAns = -1;
            if (idx != 0) {
                tempAns = root.search(root, query[0]);
            }
            ans[query[2]] = tempAns;
        }

        return ans;
        
    }
}
