package bullscows;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static String[] answer;
    public static int requiredDigits;

    public static int[] checkDigits(String userGuess) {
        int cowCount = 0;
        int bullCount = 0;
        for (int i = 0; i < userGuess.length(); i++) {
            String num = String.valueOf(userGuess.charAt(i));

            if (Objects.equals(num, answer[i])) { // bull
                bullCount += 1;
            }

            else if (isCow(num)) { // cow
                cowCount += 1;
            }
        }

        return new int[]{bullCount, cowCount};
    }

    public static boolean isCow(String numString) {

        for (String value : answer) {
            if (Objects.equals(value, numString)) {
                return true;
            }
        }

        return false;
    }

    public static boolean gradeAnswer(int[] scores) {
        int bullCount = scores[0];
        int cowCount = scores[1];

        if (bullCount == requiredDigits) {
            System.out.println("Grade " + bullCount + " bulls");
            return true;
        }

        else if (cowCount == 0 && bullCount == 0) {
            System.out.println("Grade: None.");
        }

        else if (bullCount > 0 && cowCount == 0) {
            System.out.println("Grade " + bullCount + " bull(s).");
        }

        else if (cowCount > 0 && bullCount == 0){
            System.out.println("Grade " + cowCount + " cow(s).");
        }

        else {
            System.out.println("Grade " + bullCount + " bull(s) and " + cowCount + " cow(s).");
        }

        return false;
    }

    public static String generateNumString(int requiredDigits) {

        StringBuilder numString = new StringBuilder();
        HashSet<Integer> numSeen = new HashSet<>();

        int max = 10;
        int min = 1;

        while (numString.length() != requiredDigits) {
            int randomNum = (int) (Math.random() * (max - min) + min);

            if (numString.isEmpty() && randomNum == 0 || numSeen.contains(randomNum)){ //
                continue;
            }

            numString.append(randomNum);
            numSeen.add(randomNum);
        }

        return numString.toString();
    }

    public static void playGame() {
        int turn = 1;

        while (true) {
            System.out.println("Turn " + turn);
            Scanner scanner = new Scanner(System.in);
            String userGuess = scanner.nextLine();

            int[] score = checkDigits(userGuess);

            if (gradeAnswer(score)) {
                System.out.println("Congratulations! You guessed the secret code!");
                break;
            }

            turn += 1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter the secret code's length: ");
        requiredDigits = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Please Input the number of possible symbols in the code: ");
        int symbolsAllowed = scanner.nextInt();
        scanner.nextLine();
        scanner.close();

        //create error when symbols allowed is greater than required digits

        //create error when symbols allowed is greater than required digits
        String[] nums = {"0","1","2","3","4","5","6","7","8","9"};
        String[] letters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o",
                "p","q","r","s","t","u","v","w","x","y","z"};

        String[] lettersAllowed = Arrays.copyOfRange(letters, 0, symbolsAllowed - 10); //error is here, cant
        // will be passed into generated

        String[] alphaNums = new String[nums.length + lettersAllowed.length];

        System.arraycopy(nums, 0, alphaNums, 0, nums.length);
        System.arraycopy(letters, 0, alphaNums, 0, lettersAllowed.length);
        System.out.println(alphaNums);
        if (requiredDigits > 36) {
            System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.");
            return;
        }

        System.out.println("Okay, let's start a game!");
        answer = generateNumString(requiredDigits).split("");

        // need to print the secret code with starts

        playGame();
    }
}