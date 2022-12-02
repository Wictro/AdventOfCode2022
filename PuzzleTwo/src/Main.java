import java.io.*;

/**
 * Advent of Code, Puzzle 2
 * In the calories.txt file find the 3 maximum sums in a group, where groups are seperated by blank lines
 * Sum these three maximums
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "src/calories.txt";
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        //calories are positive
        long[] maxSums = new long[3];

        long currentSum = 0;

        String currentLine;
        while ((currentLine = reader.readLine()) != null){
            if (currentLine.isEmpty()){
                for (int i = 0; i < maxSums.length; i++){
                    if(currentSum >= maxSums[i]){
                        insertMaxSum(maxSums, i, currentSum);
                        break;
                    }
                }
                currentSum = 0;
            }
            else{
                currentSum += Long.parseLong(currentLine);
            }
        }

        long finalSum = 0;

        for (long max: maxSums)
            finalSum += max;

        System.out.println(finalSum);
    }

    private static void insertMaxSum(long[] maxSums, int index, long value){
        long previous = value;
        for (int i = index; i < maxSums.length; i++){
            long current = maxSums[i];
            maxSums[i] = previous;
            previous = current;
        }
    }
}
