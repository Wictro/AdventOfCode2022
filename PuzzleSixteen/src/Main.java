import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "src/input.txt";
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String currentLine = reader.readLine();
        byte[][] matrix = new byte[currentLine.length()][currentLine.length()];
        int counter = 0;
        do{
            for (int i = 0; i < currentLine.length(); i++){
                matrix[counter][i] = (byte) (currentLine.charAt(i) - 48);
            }

            counter++;
        }
        while ((currentLine = reader.readLine()) != null);

        Set<Tuple> trees = new HashSet<>();

        byte maxTree;
        //check from the right
        for (int row = 0; row < matrix.length; row++){
            maxTree = -1;
            for (int col = 0; col < matrix.length; col++){
                byte currentTreeLength = matrix[row][col];

                if(currentTreeLength > maxTree){
                    maxTree = currentTreeLength;
                    trees.add(new Tuple(row, col));
                }
            }
        }

        //check from the left
        for (int row = 0; row < matrix.length; row++){
            maxTree = -1;
            for (int col = matrix.length - 1; col > 0; col--){
                byte currentTreeLength = matrix[row][col];

                if(currentTreeLength > maxTree){
                    maxTree = currentTreeLength;
                    trees.add(new Tuple(row, col));
                }
            }
        }

        //check from the top
        for (int col = 0; col < matrix.length; col++){
            maxTree = -1;
            for (int row = 0; row < matrix.length; row++){
                byte currentTreeLength = matrix[row][col];

                if(currentTreeLength > maxTree){
                    maxTree = currentTreeLength;
                    trees.add(new Tuple(row, col));
                }
            }
        }

        //check from the bottom
        for (int col = 0; col < matrix.length; col++){
            maxTree = -1;
            for (int row = matrix.length - 1; row >= 0; row--){
                byte currentTreeLength = matrix[row][col];

                if(currentTreeLength > maxTree){
                    maxTree = currentTreeLength;
                    trees.add(new Tuple(row, col));
                }
            }
        }

        System.out.println(trees.size());

        AtomicLong maxScore = new AtomicLong();

        trees.forEach(tree -> {
            int down = 0;

            //look down
            for (int row = tree.row + 1; row < matrix.length; row++){
                if(matrix[row][tree.col] >= matrix[tree.row][tree.col]){
                    down++;
                    break;
                }
                else{
                    down++;
                }
            }

            int up = 0;
            //look up
            for (int row = tree.row - 1; row >= 0; row--){
                if(matrix[row][tree.col] >= matrix[tree.row][tree.col]){
                    up++;
                    break;
                }
                else{
                    up++;
                }
            }

            int right = 0;
            //look right
            for (int col = tree.col + 1; col < matrix.length; col++){
                if(matrix[tree.row][col] >= matrix[tree.row][tree.col]){
                    right++;
                    break;
                }
                else{
                    right++;
                }
            }

            int left = 0;
            //look left
            for (int col = tree.col - 1; col >= 0; col--){
                if(matrix[tree.row][col] >= matrix[tree.row][tree.col]){
                    left++;
                    break;
                }
                else{
                    left++;
                }
            }

            long score = up * down * left * right;

            maxScore.set(Math.max(score, maxScore.get()));
        });

        System.out.println(maxScore.get());
    }

    private static class Tuple{
        public int row;
        public int col;

        public Tuple(int row, int col){
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Tuple))
                return false;

            return (row == ((Tuple) obj).row && col == ((Tuple) obj).col);
        }

        //a really bad hashcode
        @Override
        public int hashCode() {
            return (row + col) * (row << col);
        }
    }
}

