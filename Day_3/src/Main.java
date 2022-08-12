import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] oneCount = {0,0,0,0,0,0,0,0,0,0,0,0};
        ArrayList<Integer> mostCommonBit = new ArrayList<>();
        ArrayList<Integer> leastCommonBit = new ArrayList<>();
        int lineLength = 12;
        int fileLength = 0;
        try (Scanner myReader = new Scanner(new File("Input.txt"))) {
            while (myReader.hasNextLine()) {
                String[] line = myReader.nextLine().split("(?!^)");
                for (int i = 0;i < lineLength;i++) {
                    if (Integer.parseInt(line[i]) == 1) {
                        oneCount[i] += 1;
                    }

                }
                fileLength++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        for (int i = 0;i < lineLength;i++) {
            mostCommonBit.add(0);
            leastCommonBit.add(1);
            if (oneCount[i] > fileLength/2) {
                mostCommonBit.set(i, 1);
                leastCommonBit.set(i, 0);
            }
        }
        System.out.println(mostCommonBit);
        System.out.println(leastCommonBit);
    }
}