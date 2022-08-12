import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int amountOfRows = 5;
        int lengthOfRows = 10;
        int[][]field = new int[lengthOfRows][amountOfRows];
        int totalRisk = 0;

        try (Scanner myReader = new Scanner(new File("Input.txt"))) {
            int y = 0;
            while (myReader.hasNextLine()) {
                int[] numbers = Arrays.stream(myReader.nextLine().split("(?!^)")).mapToInt(Integer::parseInt).toArray();
                for (int x = 0;x < lengthOfRows;x++) {
                    field[x][y] = numbers[x];
                }
                y++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }


        int[] deltaX = {-1,0,0,1};
        int[] deltaY = {0,1,-1,0};
        ArrayList<Point> localMin = new ArrayList<>();


        for (int x = 0; x < lengthOfRows;x++) {
            for (int y = 0; y < amountOfRows;y++) {
                boolean safe = false;
                for (int neighbour = 0; neighbour < 4;neighbour++) {
                    int neighbourX = x+deltaX[neighbour];
                    int neighbourY = y+deltaY[neighbour];
                    if (neighbourX < 0 || neighbourX >= lengthOfRows || neighbourY < 0 || neighbourY >= amountOfRows) {
                        continue; //Neighbour punt bestaat niet
                    }
                    if (field[neighbourX][neighbourY] <= field[x][y]) {
                        safe = true;
                        break; //Neighbour is even hoog of kleiner, dus kan het niet gevaarlijk zijn
                    }
                    //Pas na 2-4 keer erdoorheen, weet je of je hier veilig bent
                }
                if (!safe) {
                    localMin.add(new Point(x,y));
                    totalRisk += 1 + field[x][y];
                }
            }
        }
        System.out.println(totalRisk);
        System.out.println(localMin);

        //Part 2
        HashSet<Point> basinPoints = new HashSet<>();
        HashMap<Point, Integer> allBasins = new HashMap<>();
        Point testPoint = new Point(1,0);






    }

    public static void burenEnZijnBuren(int[][] field, Point point, HashSet<Point> beenThere) {
        int[] deltaX = {-1,0,0,1};
        int[] deltaY = {0,1,-1,0};

        if (point.x == point.y && field[point.x][point.y] != 9) {
            return;
        }

        for (int n = 0;n < 4;n++) {
            Point neighbour = new Point(point.x + deltaX[n], point.y + deltaY[n]);
            if (neighbour.x < 0 || neighbour.x >= 5 || neighbour.y < 0 || neighbour.y >= 5) {
                continue; //Neighbour punt bestaat niet
            }
            if (beenThere.contains(neighbour)) {
                continue;
            }
            if (neighbour.x == neighbour.y && field[neighbour.x][neighbour.y] != 9) { //Hier ook uitzondering zetten
                continue;
            }
            beenThere.add(neighbour);
            if (beenThere.size() < 25) {
                burenEnZijnBuren(field, neighbour, beenThere);
            }
        }
    }

}