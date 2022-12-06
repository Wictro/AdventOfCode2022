import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "src/input.txt";
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        Set<Character> partOneChars = new HashSet<>();

        long currentScore = 0;

        String currentLine;
        while ((currentLine = reader.readLine()) != null){
            char[] chars = currentLine.toCharArray();

            for (int i = 0; i < chars.length / 2; i++){
                partOneChars.add(chars[i]);
            }

            for(int i = chars.length / 2; i < chars.length; i++){
                if (partOneChars.contains(chars[i])){
                    currentScore += getPriority(chars[i]);
                    break;
                }
            }

            partOneChars.clear();
        }

        System.out.println(currentScore);
    }

//    Lowercase item types a through z have priorities 1 through 26.
//    Uppercase item types A through Z have priorities 27 through 52.
    private static int getPriority(char item) {
        if(Character.isUpperCase(item)){
            return item - 65 + 26 + 1;
        }
        else{
            return item - 97 + 1;
        }
    }
}
