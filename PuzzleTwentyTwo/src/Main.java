import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static List<Monkey> monkeys = new ArrayList<>();
    private static long modulo = 1;

    public static void main(String[] args) throws IOException {
        String fileName = "src/input.txt";
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String currentLine;
        while ((currentLine = reader.readLine()) != null){
            Monkey mon = new Monkey();

            Pattern pattern = Pattern.compile("\\d+");

            // Get a Matcher object
            Matcher matcher = pattern.matcher(reader.readLine());

            while (matcher.find()){
                mon.items.add(Long.parseLong(matcher.group()));
            }

            mon.operation = reader.readLine().trim().split(" ", 2)[1];

            mon.modulo = Integer.parseInt(reader.readLine().trim().split(" ")[3]);

            mon.trueCase = Integer.parseInt(reader.readLine().trim().split(" ")[5]);

            mon.falseCase = Integer.parseInt(reader.readLine().trim().split(" ")[5]);

            monkeys.add(mon);

            reader.readLine();
        }

        Set<Integer> moduli = new HashSet<>();

        for (Monkey m: monkeys){
            moduli.add(m.modulo);
        }

        for (Integer i : moduli){
            modulo *= i;
        }

        for (int i = 0; i < 10000; i++){
            for (int j = 0; j < monkeys.size(); j++){
                monkeys.get(j).inspectAndThrow();
            }
        }

        monkeys.sort((a, b) -> {
            return (int) (b.inspected - a.inspected);
        });

        System.out.println();
    }

    private static class Monkey{
        public Queue<Long> items = new LinkedBlockingQueue<>();
        public String operation;

        public int modulo;

        public int trueCase;

        public int falseCase;

        public long inspected = 0;

        public long newWorryLevel(long oldWorryLevel){
            String[] tokens = operation.split(" ");

            long firstArg = oldWorryLevel, secondArg = 0;
            String op;

            if(tokens[4].equals("old")){
                secondArg = oldWorryLevel;
            }
            else{
                secondArg = Integer.parseInt(tokens[4]);
            }

            op = tokens[3];

            switch(op){
                case "+":
                    return firstArg + secondArg;
                case "-":
                    return firstArg - secondArg;
                case "*":
                    return firstArg * secondArg;
                case "/":
                    return firstArg / secondArg;
            }

            return 0;
        }

        public void inspectAndThrow(){
            while (!items.isEmpty()){
                Long currentItemWorryLevel = items.remove();

                long newWorryLevel = newWorryLevel(currentItemWorryLevel) % Main.modulo;

                if (newWorryLevel % modulo == 0){
                    monkeys.get(trueCase).items.add(newWorryLevel);
                }
                else{
                    monkeys.get(falseCase).items.add(newWorryLevel);
                }

                inspected++;
            }
        }
    }
}

