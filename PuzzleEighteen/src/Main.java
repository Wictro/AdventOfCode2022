import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static Coordinate[] knots = new Coordinate[500];

    public static void main(String[] args) throws IOException {
        String fileName = "src/input.txt";
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        for (int i = 0; i < knots.length; i++){
            knots[i] = new Coordinate(0, 0);
        }

        Set<Coordinate> visited = new HashSet<>();

        String currentLine;
        while ((currentLine = reader.readLine()) != null){
            String[] tokens = currentLine.split(" ");
            char direction = tokens[0].charAt(0);
            int distance = Integer.parseInt(tokens[1]);
            Vector initialVector = new Vector(direction);

            for (int i = 0; i < distance; i++){
                Coordinate previousKnot = null;
                Vector vector = initialVector;

                for(int j = 0; j < knots.length; j++){
                    vector = moveKnot(knots[j], previousKnot, vector);
                    previousKnot = knots[j];

                    if(j == knots.length - 1){
                        visited.add(new Coordinate(knots[j].x, knots[j].y));
                    }
                }
            }
        }

        System.out.println(visited.size());
    }

    /**
     * Moves a knot according to its pull vector
     * @return the movement of this knot
     */
    private static Vector moveKnot(Coordinate knot, Coordinate previousKnot, Vector previousVector) {
        //if the previous one didn't move
        if (previousVector == null)
            return null;

        //if it's the head
        if (previousKnot == null){
            return knot.move(previousVector);
        }
        else{
            //here, we assume that the head just moved
            double distance = Math.sqrt(Math.pow(knot.x - previousKnot.x, 2) + Math.pow(knot.y - previousKnot.y, 2));

            //if their distance is 1 we don't move
            //if their distance is sqrt(2) we don't move
            //if their distance is 0 we don't move
            if(equal(distance, 1.0) || equal(distance, Math.sqrt(2)) || equal(distance, 0.0))
                return null;

            //if their distance is 2 we close the distance
            if(equal(distance, 2.0)){
                if(previousKnot.x == knot.x){
                    if(previousKnot.y > knot.y){
                        return knot.move(new Vector(0, 1));
                    }
                    else{
                        return knot.move(new Vector(0, -1));
                    }
                }
                else{
                    if(previousKnot.x > knot.x){
                        return knot.move(new Vector(1, 0));
                    }
                    else{
                        return knot.move(new Vector(-1, 0));
                    }
                }
            }

            //if their distance is sqrt(5) or sqrt(8) we close the distance
            if(equal(distance, Math.sqrt(5)) || equal(distance, Math.sqrt(8))){
                return knot.move(new Vector(previousKnot.x > knot.x? 1 : -1, previousKnot.y > knot.y? 1 : -1));
            }
        }

        return null;
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

        public Coordinate(){}

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

        public Vector move(Vector vector) {
            this.x += vector.x;
            this.y += vector.y;
            return vector;
        }

        @Override
        public String toString() {
            return String.format("[%d, %d]", x, y);
        }
    }

    private static class Vector extends Coordinate{
        public Vector(int x, int y){
            super(x, y);
        }

        public Vector(char direction){
            switch (direction){
                case 'R':
                    this.x = 1;
                    break;
                case 'U':
                    this.y = 1;
                    break;
                case 'L':
                    this.x = -1;
                    break;
                case 'D':
                    this.y = -1;
                    break;
            }
        }
    }
}

