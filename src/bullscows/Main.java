package bullscows;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static String[] answer = {"4", "1", "7", "5"};
    public static int cowCount = 0;
    public static int bullCount = 0;


    public static void checkDigits(String userGuess) {
        for (int i = 0; i < userGuess.length(); i++) {
            String num = String.valueOf(userGuess.charAt(i));

            if (Objects.equals(num, answer[i])) { // bull
                bullCount += 1;
            }

            else if (isCow(num)) { // cow
                cowCount += 1;
            }
        }
    }

    public static boolean isCow(String numString) {

        for (String value : answer) {
            if (Objects.equals(value, numString)) {
                return true;
            }
        }

        return false;
    }

    public static void gradeAnswer() {

        String answerString = String.join("", answer); // only works on arrays of strings
        System.out.println(answerString);

        if (cowCount == 0 && bullCount == 0) {
            System.out.println("Grade: None. The secret code is " + answerString + ".");
        }

        else if (bullCount > 0 && cowCount == 0) {
            System.out.println("Grade " + bullCount + " bull(s). The secret code is " + answerString + ".");
        }

        else if (cowCount > 0 && bullCount == 0){
            System.out.println("Grade " + cowCount + " cow(s). The secret code is " + answerString + ".");
        }

        else {
            System.out.println("Grade " + bullCount + " bull(s) and " + cowCount + " cow(s). The secret code is " + answerString + ".");
        }
    }

    public static void generateNumber(int requiredDigits) {
        long pseudoRandomNumber = System.nanoTime();

        if (requiredDigits > 10) {
            System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.");
        }

        StringBuilder numString = new StringBuilder();

        int max = 10;
        int min = 1;

        while (numString.length() != requiredDigits) {
            int randomNum = (int) (Math.random() * (max - min) + min);
            numString.append(randomNum);
            System.out.println(numString);
        }

        System.out.println(numString);

        // if the number starts with 0 bring up an error
        // all unique numbers.
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//      String userGuess = scanner.nextLine();
        int requiredDigits = scanner.nextInt();

        generateNumber(requiredDigits);
    }

}
