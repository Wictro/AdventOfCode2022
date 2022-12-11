import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static int cycle = 0;
    private static int X = 1;

    private static int crtPosition = 0;

    public static void main(String[] args) throws IOException {
        String fileName = "src/input.txt";
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String currentLine;
        while ((currentLine = reader.readLine()) != null){
            String[] tokens = currentLine.split(" ");

            //if noop
            if(tokens.length == 1){
                cycle++;
                crtPosition = (cycle - 1) % 40;
                if (crtPosition == X-1 || crtPosition == X || crtPosition == X + 1)
                    System.out.print("#");
                else
                    System.out.print(".");

                if (crtPosition == 39)
                    System.out.println();
            }
            else{
                cycle++;
                crtPosition = (cycle - 1) % 40;

                if (crtPosition == X-1 || crtPosition == X || crtPosition == X + 1)
                    System.out.print("#");
                else
                    System.out.print(".");

                if (crtPosition == 39)
                    System.out.println();

                cycle++;
                crtPosition = (cycle - 1) % 40;

                if (crtPosition == X-1 || crtPosition == X || crtPosition == X + 1)
                    System.out.print("#");
                else
                    System.out.print(".");

                if (crtPosition == 39)
                    System.out.println();

                X += Integer.parseInt(tokens[1]);
            }
        }

        System.out.println(cycle);
    }
}

