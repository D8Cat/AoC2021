import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int amountOfRows = 895;
        int amountOfColumns = 1310;
        int[][]field = new int[amountOfColumns][amountOfRows];

        try (Scanner myReader = new Scanner(new File("Input.txt"))) {
            while (myReader.hasNextLine()) {
                int[] numbers = Arrays.stream(myReader.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
                field[numbers[0]][numbers[1]] = 1;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        try (Scanner secondReader = new Scanner(new File("FoldInstructions.txt"))) {
            while (secondReader.hasNextLine()) {
                String[] firstSplit = secondReader.nextLine().split(" ");
                String[] secondSplit = firstSplit[2].split("=");

                String foldingDirection = secondSplit[0];
                int foldingLine = Integer.parseInt(secondSplit[1]);

                for (int x = 0; x < amountOfColumns;x++) {
                    for (int y = 0; y < amountOfRows;y++) {
                        if (foldingDirection.equalsIgnoreCase("x")) {
                            if (x == foldingLine) {
                                field[x][y] = 0;
                            } else if (field[x][y] > 0 && x > foldingLine) {
                                field[foldingLine - (x - foldingLine)][y] = 1;
                                field[x][y] = 0;
                            }
                        }
                        if (foldingDirection.equalsIgnoreCase("y")) {
                            if (y == foldingLine) {
                                field[x][y] = 0;
                            } else if (field[x][y] > 0 && y > foldingLine) {
                                field[x][(foldingLine - (y -foldingLine))] = 1;
                                field[x][y] = 0;
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }


        int amountOfPoints = 0;
        for (int x = 0; x < amountOfColumns;x++) {
            for (int y = 0; y < amountOfRows;y++) {
                if (field[x][y] > 0) {
                    System.out.println(x + "," + y);
                    amountOfPoints++;
                }
            }
        }
        System.out.println(amountOfPoints);

        for (int x = 0; x < 80;x++) {
            System.out.println();
            for (int y = 0; y < 80;y++) {
                if (field[x][y] > 0) {
                    System.out.print('x');
                } else {
                    System.out.print(".");
                }
            }
        }
    }
}