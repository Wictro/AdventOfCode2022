import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static Coordinate head;
    private static Coordinate tail;

    public static void main(String[] args) throws IOException {
        String fileName = "src/input.txt";
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        //We need to count how MANY positions it visits - not necessarily which ones
        head = new Coordinate(0, 0);
        tail = new Coordinate(0, 0);

        Set<Coordinate> visited = new HashSet<>();

        String currentLine;
        while ((currentLine = reader.readLine()) != null){
            String[] tokens = currentLine.split(" ");
            char direction = tokens[0].charAt(0);
            int distance = Integer.parseInt(tokens[1]);

            for(int i = 0; i < distance; i++){
                moveHead(direction);
                moveTail(direction);
                visited.add(new Coordinate(tail.x, tail.y));
            }
        }

        System.out.println(visited.size());
    }

    private static void moveTail(char lastHeadMovement) {
        //here, we assume that the head just moved
        double distance = Math.sqrt(Math.pow(head.x - tail.x, 2) + Math.pow(head.y - tail.y, 2));

        //if their distance is 1 we don't move
        //if their distance is sqrt(2) we don't move
        //if their distance is 0 we don't move
        if(equal(distance, 1.0) || equal(distance, Math.sqrt(2)) || equal(distance, 0.0))
            return;

        //if their distance is 2 we close the distance
        if(equal(distance, 2.0)){
            moveCoordinate(tail, lastHeadMovement);
            return;
        }

        //if their distance is sqrt(3) we close the distance
        if(equal(distance, Math.sqrt(5))){
            //fix the offset
            if(Math.abs(head.x - tail.x) == 1){
                tail.x = head.x;
            }
            else{
                tail.y = head.y;
            }

            //move in the direction
            moveCoordinate(tail, lastHeadMovement);
        }
    }

    private static void moveHead(char direction) {
        moveCoordinate(head, direction);
    }

    private static void moveCoordinate(Coordinate c, char direction){
        switch (direction){
            case 'R':
                c.x = c.x + 1;
                break;
            case 'U':
                c.y = c.y + 1;
                break;
            case 'L':
                c.x = c.x - 1;
                break;
            case 'D':
                c.y = c.y - 1;
                break;
        }
    }

    private static boolean equal(double a, double b){
        double epsilon = 0.0001d;
        return Math.abs(a - b) < epsilon;
    }

    private static class Coordinate{
        public int x;
        public int y;

        public Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            return x == ((Coordinate)obj).x && y == ((Coordinate)obj).y;
        }

        @Override
        public int hashCode() {
            int result = 61;

            result = 37 * result + x;
            result = 37 * result + y;

            return result;
        }
    }
}
