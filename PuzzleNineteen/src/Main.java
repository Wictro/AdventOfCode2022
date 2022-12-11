import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static int cycle = 0;
    private static int X = 1;
    private static long signalSum = 0;

    public static void main(String[] args) throws IOException {
        String fileName = "src/input.txt";
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String currentLine;
        while ((currentLine = reader.readLine()) != null && cycle <= 220){
            String[] tokens = currentLine.split(" ");

            //if noop
            if(tokens.length == 1){
                cycle++;
                if (cycle % 40 == 20){
                    signalSum += (X * cycle);
                }
            }
            else{
                cycle++;
                if (cycle % 40 == 20){
                    signalSum += (X * cycle);
                }
                cycle++;
                if (cycle % 40 == 20){
                    signalSum += (X * cycle);
                }
                X += Integer.parseInt(tokens[1]);
            }
        }

        System.out.println(signalSum);


    }
}
