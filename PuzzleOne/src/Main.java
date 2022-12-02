import java.io.*;

/**
 * Advent of Code, Puzzle 1
 * In the calories.txt file find the maximum sum in a group, where groups are seperated by blank lines
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "src/calories.txt";
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        //calories are positive
        long maxSum = 0;

        long currentSum = 0;

        String currentLine;
        while ((currentLine = reader.readLine()) != null){
            if (currentLine.isEmpty()){
                maxSum = Math.max(maxSum, currentSum);
                currentSum = 0;
            }
            else{
                currentSum += Long.parseLong(currentLine);
            }
        }

        System.out.println(maxSum);
    }
}
