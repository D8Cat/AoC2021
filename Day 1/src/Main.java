import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int counter = -3;
        int currentNumber = 0;
        int secondNumber = 0;
        int thirdNumber = 0;
        int sumCurrentThree = 0;
        int sumLastThree = 0;
        try (Scanner myReader = new Scanner(new File("Input.txt"))) {
            while (myReader.hasNextLine()) {
                currentNumber = myReader.nextInt();
                sumCurrentThree = currentNumber + secondNumber + thirdNumber;
                if (sumCurrentThree > sumLastThree) {
                    counter++;
                    System.out.println(sumCurrentThree);
                }
                thirdNumber = secondNumber;
                secondNumber = currentNumber;
                sumLastThree = sumCurrentThree;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        System.out.println(counter);
    }
}