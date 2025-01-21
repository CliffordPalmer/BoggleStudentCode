public class TST {
    // Instance variable to store the root node of the TST
    private TSTNode root;

    // Helper function which calls the recursive function with necessary parameters.
    public void insert(String s){
        if(root == null){
            root = new TSTNode(s.charAt(0));
        }
        insert(s, root);
    }

    // Recursive function to insert a word into the TST
    public void insert(String s, TSTNode current){
        // Base case: if there is only one character left, and that character matches the current
        // node, set the word to true and return
        if(s.length() == 1 && current.getLetter() == s.charAt(0)){
            current.setWord(true);
            return;
        }

        // Case for creating a middle child
        if(current.getLetter() == s.charAt(0)){
            // If the next node is null, create a new node with the first character
            if(current.getMiddle() == null) {
                current.addMiddle(s.charAt(1));
            }
            // Recurse but remove the first letter of the String, because the first letter
            // was accepted
            insert(s.substring(1), current.getMiddle());
        }

        // Case for creating a left child
        else if(s.charAt(0) < current.getLetter()){
            if(current.getLeft() == null){
                current.addLeft(s.charAt(0));
            }
            // Recurse with the entire string, because the first letter didn't get accepted
            insert(s, current.getLeft());
        }

        // Case for getting a right child
        else if(s.charAt(0) > current.getLetter()){
            if(current.getRight() == null){
                current.addRight(s.charAt(0));
            }
            insert(s, current.getRight());
        }

    }

    // Helper function which calls the recursive function with necessary parameters.
    public TSTNode lookup(String s){
        return lookup(s, root);
    }

    public TSTNode lookup(String s, TSTNode current) {
        // Base cases:
        // Return early if the String runs out or if running into a null node.
        if(current == null){
            return null;
        }
        if(s.length() == 0){
            return current.getParent();
        }
        // If on the last letter, it matches the node, and the node is a valid word, return true
        if(s.length() == 1 && s.charAt(0) == current.getLetter() && current.isWord()) {
            return current;
        }

        // Case for moving to the middle child
        if (current.getLetter() == s.charAt(0)) {
            // Same as above
            return lookup(s.substring(1), current.getMiddle());
        }
        // Case for moving to the left child
        else if (s.charAt(0) < current.getLetter()) {
            // Same as above
            return lookup(s, current.getLeft());
        }
        // Case for moving to the right child
        else {
            return lookup(s, current.getRight());
        }
    }

}
