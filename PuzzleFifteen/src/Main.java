import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
