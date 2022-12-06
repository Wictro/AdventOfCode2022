import java.io.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "src/input.txt";
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<Stack<Character>> stacks = new ArrayList<Stack<Character>>(9);

        String currentLine;
        while ((currentLine = reader.readLine()) != null){
            if (currentLine.charAt(1) == '1')
                break;

            for (int i = 0; i < 9; i++){
                try {
                    char a = currentLine.charAt(4 * i + 1);
                    if (stacks.size() <= i || stacks.get(i) == null){
                        stacks.add(i, new Stack<>());
                    }

                    if (a != ' ')
                        stacks.get(i).push(a);
                }
                catch (IndexOutOfBoundsException e){
                    break;
                }
            }
        }

        reverseStacks(stacks);

        reader.readLine();

        while ((currentLine = reader.readLine()) != null){
            Matcher m = Pattern.compile("-?\\d+").matcher(currentLine);

            int fromStackIndex = -1, toStackIndex = -1, times = -1;
            if(m.find())
                times = Integer.parseInt(m.group());
            if(m.find())
                fromStackIndex = Integer.parseInt(m.group()) - 1;
            if(m.find())
                toStackIndex = Integer.parseInt(m.group()) - 1;

            Stack<Character> fromStack = stacks.get(fromStackIndex);
            Stack<Character> toStack = stacks.get(toStackIndex);

            Stack<Character> temp = new Stack<>();

            for (int i = 0; i < times; i++){
                temp.push(fromStack.pop());
            }

            while (!temp.isEmpty())
                toStack.push(temp.pop());
        }

        String answer = "";

        for (Stack<Character> stack : stacks){
            answer += stack.peek();
        }

        System.out.println(answer);

    }

    private static void reverseStacks(List<Stack<Character>> stacks) {
        Queue<Character> queue = new LinkedBlockingQueue<>();

        stacks.forEach(stack -> {
            while (!stack.isEmpty()){
                queue.add(stack.pop());
            }

            while (!queue.isEmpty())
                stack.push(queue.poll());
        });
    }
}

