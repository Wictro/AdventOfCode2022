import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "src/input.txt";
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        UniqueFixedLengthString str = new UniqueFixedLengthString(14);

        int index = -1;
        int lastCharacter;
        while ((lastCharacter = reader.read()) != -1){
            char character = (char) lastCharacter;
            if(str.nextCharacter(character, ++index)){
                System.out.println(index + 1);
                break;
            }
        }
    }

    private static class UniqueFixedLengthString{
        int currentLength = 0;

        int targetLength;

        int index = 0;

        HashMap<Character, Integer> map;

        public UniqueFixedLengthString(int length){
            targetLength = length;
            map = new HashMap<>(length);
        }

        public boolean nextCharacter(char character, int index){
            Integer lastIndex = map.get(character);

            if(lastIndex == null){
                map.put(character, index);
                currentLength++;
                if (currentLength == targetLength)
                    return true;
            }
            else{
                map.put(character, index);
                Iterator it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    if ((Integer)(pair.getValue()) < lastIndex)
                        it.remove();
                }
                currentLength = index - lastIndex;
            }

            this.index = index;

            return false;
        }
    }
}

