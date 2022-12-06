import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "src/input.txt";
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String currentLine;
        int counter = 0;

        while ((currentLine = reader.readLine()) != null){
            String[] parts = currentLine.split(",");

            Range firstRange = new Range(Integer.parseInt(parts[0].split("-")[0]), Integer.parseInt(parts[0].split("-")[1]));
            Range secondRange = new Range(Integer.parseInt(parts[1].split("-")[0]), Integer.parseInt(parts[1].split("-")[1]));

            if(firstRange.contains(secondRange) || secondRange.contains(firstRange))
                counter++;
        }

        System.out.println(counter);
    }

    private static class Range{
        private int start;
        private int end;

        public Range(int start, int end){
            this.start = start;
            this.end = end;
        }

        public boolean contains(Range range){
            return this.start <= range.start && this.end >= range.end;
        }
    }
}
