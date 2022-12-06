import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "src/input.txt";
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        Set<Character> firstItems = new HashSet<>();
        Set<Character> secondItems = new HashSet<>();
        Set<Character> thirdItems = new HashSet<>();

        long currentScore = 0;

        String firstLine;
        while ((firstLine = reader.readLine()) != null){
            char[] firstLineChars = firstLine.toCharArray();
            char[] secondLineChars = reader.readLine().toCharArray();
            char[] thirdLineChars = reader.readLine().toCharArray();

            putInSet(firstLineChars, firstItems);
            putInSet(secondLineChars, secondItems);
            putInSet(thirdLineChars, thirdItems);

            firstItems.retainAll(secondItems);
            firstItems.retainAll(thirdItems);

            currentScore += getPriority(firstItems.stream().findFirst().get());

            firstItems.clear();
            secondItems.clear();
            thirdItems.clear();
        }

        System.out.println(currentScore);
    }

    private static void putInSet(char[] arr, Set<Character> set){
        for (char character : arr){
            set.add(character);
        }
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

