class TrieNode {
    TrieNode[] next = new TrieNode[27];
    int times = 0;
}

class Node {
    String sentence;
    int times;

    Node(String st, int t) {
        sentence = st;
        times = t;
    }
}

class AutocompleteSystem {
    TrieNode root;
    String current;
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.root = new TrieNode();
        this.current = "";
        for (int i = 0; i < sentences.length; i++) {
            add(sentences[i], times[i]);
        }
    }
    
    public void add(String str, int times) {
        TrieNode node = root;
        for (char c : str.toCharArray()) {
            if (node.next[toInt(c)] == null) {
                node.next[toInt(c)] = new TrieNode();
            }
            node = node.next[toInt(c)];
        }
        
        node.times += times;
    }
    
    private int toInt(char c) {
        return c == ' ' ? 26 : c - 'a';
      }
    
    private List<Node> lookup(TrieNode t, String s) {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
          if (t.next[toInt(s.charAt(i))] == null) {
            return new ArrayList<>();
          }
          t = t.next[toInt(s.charAt(i))];
        }
        traverse(s, t, list);
        return list;
      }

      private void traverse(String s, TrieNode t, List<Node> list) {
        if (t.times > 0) list.add(new Node(s, t.times));
        for (char i = 'a'; i <= 'z'; i++) {
          if (t.next[i - 'a'] != null) {
            traverse(s + i, t.next[i - 'a'], list);
          }
        }
        if (t.next[26] != null) {
          traverse(s + ' ', t.next[26], list);
        }
      }

      public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
          add(current, 1);
          current = "";
        } else {
          current += c;
          List<Node> list = lookup(root, current);
          Collections.sort(
              list,
              (a, b) -> a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
          for (int i = 0; i < Math.min(3, list.size()); i++) res.add(list.get(i).sentence);
        }
        return res;
      }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
