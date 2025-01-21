import java.util.ArrayList;
import java.util.Arrays;

public class Boggle {
    private static TST dict;
    private static boolean[][] visited;
    public static String[] findWords(char[][] board, String[] dictionary) {

        ArrayList<String> goodWords = new ArrayList<String>();

        // Create a new TST for quick dictionary lookups
        dict = new TST();
        // Add dictionary words to the TST
        for(String word : dictionary){
            dict.insert(word);
        }
        visited = new boolean[board.length][board[0].length];

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length;j++){
                dfs(i, j, board, goodWords);
            }
        }
        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }

    // Function deals with the first space searched, which has a different sequence of events
    public static void dfs(int row, int col, char[][] board, ArrayList<String> goodWords){
        // Begin the word with the starting character
        String word = Character.toString(board[row][col]);
        // Search for the word in the dictionary
        TSTNode node = dict.lookup(word);
        // Return if the search failed
        if(node == null){
            return;
        }
        // If the search worked, add the word to goodWords
        if(node.isWord()){
            if(!node.isAdded()){
                node.setAdded(true);
                goodWords.add(word);
            }
        }
        // Set this square to visited
        visited[row][col] = true;
        // Start the recursive call in each cardinal direction with proper parameters
        dfs(row + 1, col, word, board, goodWords);
        dfs(row, col + 1, word, board, goodWords);
        dfs(row - 1, col, word, board, goodWords);
        dfs(row, col - 1, word, board, goodWords);
        // Unvisit this square
        visited[row][col] = false;
    }
    // Recursive dfs function
    public static void dfs(int row, int col, String word, char[][] board, ArrayList<String> goodWords){
        // Base case: if out of bounds or if this square has been visited
        if(row < 0 || row >= board.length || col < 0 || col >= board.length || visited[row][col] == true){
            return;
        }
        // Add this square to the word
        word += board[row][col];
        // Search the dictionary for the word
        TSTNode node = dict.lookup(word);
        // If the search fails, return
        if(node == null){
            return;
        }
        // If the search worked, add the word to goodWords
        if(node.isWord()){
            if(!node.isAdded()){
                node.setAdded(true);
                goodWords.add(word);
            }
        }
        // Set this square to visited
        visited[row][col] = true;
        // Recursively search in the 4 cardinal directions
        dfs(row + 1, col, word, board, goodWords);
        dfs(row, col + 1, word, board, goodWords);
        dfs(row - 1, col, word, board, goodWords);
        dfs(row, col - 1, word, board, goodWords);
        // Unvisit this square
        visited[row][col] = false;
    }
}