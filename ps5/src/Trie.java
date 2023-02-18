import java.util.ArrayList;

public class Trie {

    // Wildcards
    final char WILDCARD = '.';

    private class TrieNode {
        // TODO: Create your TrieNode class here.
        TrieNode[] presentChars = new TrieNode[256];
        boolean end = false;
        boolean isEmpty = true;

        public void setEnd(int i) {
            this.end = true;
        }
    }
    TrieNode root;
    public Trie() {
        // TODO: Initialise a trie class here.
        root = new TrieNode();
    }

    /**
     * Inserts string s into the Trie.
     *
     * @param s string to insert into the Trie
     */
    void insert(String s) {
        // TODO
        if(s.length() == 0) {
            root.isEmpty = false;
        }
        TrieNode curr = root;
        if(root.isEmpty) {
            for(int i = 0; i < s.length(); i++) {
                int charint = (int) s.charAt(i);
                curr.presentChars[charint] = new TrieNode();
                curr.isEmpty = false;
                curr = curr.presentChars[charint];
            }
        } else {
            for(int i = 0; i < s.length(); i++) {
                int charint = (int) s.charAt(i);
                if(curr.presentChars[charint] == null) {
                    curr.presentChars[charint] = new TrieNode();
                    curr.isEmpty = false;
                    curr = curr.presentChars[charint];
                    System.out.println(charint);
                } else {
                    curr = curr.presentChars[charint];
                }

            }
        }
        curr.end = true;

    }

    /**
     * Checks whether string s exists inside the Trie or not.
     *
     * @param s string to check for
     * @return whether string s is inside the Trie
     */
    boolean contains(String s) {
        // TODO
        if(root.isEmpty) {
            return false;
        }
        if(s.length() == 0) {
            return true;
        }
        TrieNode curr = root;
        for(int i = 0; i < s.length(); i++) {
            int charint = (int) s.charAt(i);
            if(curr.presentChars[charint] == null) {
                return false;
            }
            curr = curr.presentChars[charint];
        }

        if(curr.end == true) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Searches for strings with prefix matching the specified pattern sorted by lexicographical order. This inserts the
     * results into the specified ArrayList. Only returns at most the first limit results.
     *
     * @param s       pattern to match prefixes with
     * @param results array to add the results into
     * @param limit   max number of strings to add into results
     */
    void prefixSearch(String s, ArrayList<String> results, int limit) {
        // TODO

        class Search {
            int count = 0;
            int limit;
            String str;

            ArrayList<String> found;

            public Search(int limit, String str, ArrayList<String> found) {
                this.limit = limit;
                if(str == "") {
                    this.str = ".";
                } else {
                    this.str = str;
                }

                this.found = found;
            }

            public void searching(TrieNode node, StringBuilder s, int pointer) {
                if(this.count == this.limit) {
                    return;
                }

                if(pointer >= this.str.length()) {
                    if(node.end == true) {
                        this.found.add(s.toString());
                        this.count++;
                        for(int i = 0; i < 256; i++) {
                            if(node.presentChars[i] != null) { //if the node is not null
                                s.append((char) i); // add the found letter to the string
                                StringBuilder news = new StringBuilder();
                                news.append(s);
                                s.deleteCharAt(pointer);
                                searching(node.presentChars[i], news, pointer + 1);
                            }
                        }
                    } else {
                        for(int i = 0; i < 256; i++) {
                            if(node.presentChars[i] != null) { //if the node is not null
                                s.append((char) i); // add the found letter to the string
                                StringBuilder news = new StringBuilder();
                                news.append(s);
                                s.deleteCharAt(pointer);
                                searching(node.presentChars[i], news, pointer + 1);
                            }
                        }
                    }
                } else {
                    if((int) this.str.charAt(pointer) == 46) { // if the char is .
                        //look through all the possible letters;
                        for(int i = 0; i < 256; i++) {
                            if(node.presentChars[i] != null) { //if the node is not null
                                s.append((char) i); // add the found letter to the string
                                StringBuilder news = new StringBuilder();
                                news.append(s);
                                s.deleteCharAt(pointer);
                                searching(node.presentChars[i], news, pointer + 1);
                            }
                        }
                    } else {
                        if(node.presentChars[(int) this.str.charAt(pointer)] == null) { //is this note has the particular letter as a child node
                            return;
                        } else {
                            s.append(this.str.charAt(pointer)); //add current character to the string;
                            StringBuilder news = new StringBuilder();
                            news.append(s);
                            s.deleteCharAt(pointer);
                            searching(node.presentChars[(int) this.str.charAt(pointer)], news, pointer + 1);
                        }
                    }
                }


            }

            public ArrayList<String> getFound() {
                return found;
            }
        }

        Search searcher = new Search(limit, s, results);
        searcher.searching(root, new StringBuilder(), 0);


    }


    // Simplifies function call by initializing an empty array to store the results.
    // PLEASE DO NOT CHANGE the implementation for this function as it will be used
    // to run the test cases.
    String[] prefixSearch(String s, int limit) {
        ArrayList<String> results = new ArrayList<String>();
        prefixSearch(s, results, limit);
        return results.toArray(new String[0]);
    }


    public static void main(String[] args) {
        Trie t = new Trie();
//        t.insert("peter");
//        t.insert("piper");
//        t.insert("picked");
//        t.insert("a");
//        t.insert("peck");
//        t.insert("of");
//        t.insert("pickled");
//        t.insert("peppers");
//        t.insert("pepppito");
//        t.insert("pepi");
//        t.insert("pik");
//
//        String[] result1 = t.prefixSearch("pe", 10);
//        String[] result2 = t.prefixSearch("pe.", 10);
        t.insert("a");
        t.insert("aa");
        t.insert("aaa");
        System.out.println(t.contains("aaa"));
        // result1 should be:
        // ["peck", "pepi", "peppers", "pepppito", "peter"]
        // result2 should contain the same elements with result1 but may be ordered arbitrarily
    }
}
