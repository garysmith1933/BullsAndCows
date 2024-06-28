package bullscows;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static String[] answer;
    public static int requiredDigits;
    public static int symbolsAllowed;

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


    public static String generateAnswer(String[] options) {
        StringBuilder numString = new StringBuilder();
        HashSet<Integer> numSeen = new HashSet<>();

        int max = options.length;
        int min = 1;

        while (numString.length() != requiredDigits) {
            int idx = (int) (Math.random() * (max - min) + min);

            if (numSeen.contains(idx)) {
                continue;
            }

            numString.append(options[idx - 1]);
            numSeen.add(idx); //adds the idx to the set to keep track of it was used
        }

        return numString.toString();
    }

    public static void playGame() {
        int turn = 1;

        while (true) {
            System.out.println("Turn: " + turn);
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
        try {
            requiredDigits = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error:  " + requiredDigits + "isn't a valid number");
        }

        if (requiredDigits == 0) {
            System.out.println("Error: Secret code length cannot be zero");
            return;
        }

        if (requiredDigits > 36) {
            System.out.println("Error: maximum number of possible digits in the code is 36 (0-9, a-z).");
            return;
        }

        System.out.println("Please Input the number of possible symbols in the code: ");

        try {
            symbolsAllowed = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error:  " + requiredDigits + "isn't a valid number");
        }

        if (requiredDigits > symbolsAllowed) {
            System.out.println("Error: Number of symbols must be greater than or equal to secret code's length");
            return;
        }

        if (symbolsAllowed > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }

        String[] nums = {"0","1","2","3","4","5","6","7","8","9"};
        String[] letters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o",
                "p","q","r","s","t","u","v","w","x","y","z"};

        String[] lettersAllowed;
        StringBuilder secretCodeSign = new StringBuilder();
        secretCodeSign.append("*".repeat(Math.max(0, requiredDigits)));

        // if the symbols > 10, combine the nums and letters
        if (symbolsAllowed > 10) {;
            lettersAllowed = Arrays.copyOfRange(letters, 0, (symbolsAllowed - 10));
            String[] alphaNums = new String[nums.length + lettersAllowed.length];

            System.arraycopy(nums, 0, alphaNums, 0, nums.length);
            System.arraycopy(letters, 0, alphaNums, nums.length, lettersAllowed.length);
            System.out.println(Arrays.toString(alphaNums));

            answer = generateAnswer(alphaNums).split("");
            System.out.println("The secret code is prepared: " + secretCodeSign +  " (0-9), (" + letters[0] + "-" + letters[(symbolsAllowed - 10) - 1] +").");
        }

        else {
            answer = generateAnswer(nums).split("");
            System.out.println("The secret code is prepared: " + secretCodeSign +  " (0-9)");
        }

        System.out.println("Okay, let's start a game!");
        playGame();
    }
}
