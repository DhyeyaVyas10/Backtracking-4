// Time Complexity : O(n)
// Space Complexity : O(n)

class Solution {
    private List<String> words = new ArrayList<>();

    public String[] expand(String s) {
        backtracking(0, s, new StringBuilder());
        words.sort((w1, w2) -> w1.compareTo(w2));
        return words.toArray(new String[words.size()]);
    }

    private void backtracking(int i, String s, StringBuilder current) {
        if (i == s.length()) {
            words.add(current.toString());
            return;
        }

        if (s.charAt(i) == '{') {
            List<Character> options = new ArrayList<>();
            i++;
            while (s.charAt(i) != '}') {
                if (s.charAt(i) != ',') {
                    options.add(s.charAt(i));
                }
                i++;
            }
            for (char op : options) {
                current.append(op);
                backtracking(i + 1, s, current);
                current.deleteCharAt(current.length() - 1);
            }
        } else {
            current.append(s.charAt(i));
            backtracking(i + 1, s, current);
            current.deleteCharAt(current.length() - 1);
        }
    }
}