public class TSTNode {
    private char letter;
    // Children
    private TSTNode left;
    private TSTNode middle;
    private TSTNode right;
    private TSTNode parent;
    // Whether to node stores a valid word or not
    private boolean isWord;
    private boolean added;

    public TSTNode(char letter){
        this.letter = letter;
        isWord = false;
        added = false;
    }

    public TSTNode(char letter, TSTNode parent){
        this.letter = letter;
        isWord = false;
        added = false;
        this.parent = parent;
    }

    // Methods to add children
    public void addLeft(char letter){
        left = new TSTNode(letter, this);
    }
    public void addMiddle(char letter){
        middle = new TSTNode(letter, this);
    }
    public void addRight(char letter){
        right = new TSTNode(letter, this);
    }

    public void setWord(boolean word) {
        isWord = word;
    }

    public TSTNode getLeft() {
        return left;
    }

    public TSTNode getMiddle() {
        return middle;
    }

    public TSTNode getRight() {
        return right;
    }

    public char getLetter() {
        return letter;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setParent(TSTNode parent) {
        this.parent = parent;
    }

    public TSTNode getParent() {
        return parent;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    public boolean isAdded() {
        return added;
    }

}
