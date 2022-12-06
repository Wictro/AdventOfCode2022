import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "src/input.txt";
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        long currentScore = 0;

        String currentLine;
        while ((currentLine = reader.readLine()) != null){
            char theirSign = currentLine.charAt(0);
            char yourSign = currentLine.charAt(2);

            currentScore += getScore(theirSign, yourSign);
        }

        System.out.println(currentScore);
    }

    private static int getScore(char theirSign, char yourSign){
        int shapeScore = getShapeScore(yourSign);
        int roundScore = getRoundScore(theirSign, yourSign);

        return shapeScore + roundScore;
    }

    private static int getRoundScore(char theirSign, char yourSign) {
        if(theirSign == 'A'){
            if(yourSign == 'X'){
                return 3;
            }
            else if (yourSign == 'Y'){
                return 6;
            }
            else{
                return 0;
            }
        } else if (theirSign == 'B') {
            if(yourSign == 'X'){
                return 0;
            }
            else if (yourSign == 'Y'){
                return 3;
            }
            else{
                return 6;
            }
        }
        else{
            if(yourSign == 'X'){
                return 6;
            }
            else if (yourSign == 'Y'){
                return 0;
            }
            else{
                return 3;
            }
        }
    }

    private static int getShapeScore(char yourSign) {
        switch (yourSign){
            case 'X':
                return 1;
            case 'Y':
                return 2;
            case 'Z':
                return 3;
        }

        throw new RuntimeException("Invalid Hand Sign!");
    }
}
