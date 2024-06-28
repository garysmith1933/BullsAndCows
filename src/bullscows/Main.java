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

    //make symbols allowed a global variable, requiredDigits is not necesssary here, need to rework this method to work with options array
    public static String generateAnswer(int requiredDigits, int symbolsAllowed, String[] options) { // need to refactor
        StringBuilder numString = new StringBuilder();
        HashSet<Integer> numSeen = new HashSet<>();

        int max = options.length; // will need to choose from 1 to length of options cause we cant pick an index thats not there.
        int min = 1;

        while (numString.length() != requiredDigits) {
            int idx = (int) (Math.random() * (max - min) + min);

            if (numSeen.contains(idx)) { //
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

        while (true) {
            System.out.println("Please, enter the secret code's length: ");
            requiredDigits = scanner.nextInt();
            scanner.nextLine();

            if (requiredDigits < 36) {
                break;
            }

            else {
                System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.");
            }
        }

        int symbolsAllowed;

        while (true) {
            System.out.println("Please Input the number of possible symbols in the code: ");
            symbolsAllowed = scanner.nextInt();
            scanner.nextLine();

            if (requiredDigits <= symbolsAllowed) {
                break;
            }

            else {
                System.out.println("Error: Number of symbols must be greater than or equal to secret code's length");
            }
        }

        System.out.println("end");

        String[] nums = {"0","1","2","3","4","5","6","7","8","9"};
        String[] letters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o",
                "p","q","r","s","t","u","v","w","x","y","z"};

        //letters should only be allowed when symbols are greater than 10 - is this true?
        String[] lettersAllowed;

        // if the symbols > 10, combine the nums and letters
        if (symbolsAllowed > 10) {
            System.out.println("alpha");
            lettersAllowed = Arrays.copyOfRange(letters, 0, (symbolsAllowed - 10));
            String[] alphaNums = new String[nums.length + lettersAllowed.length];

            System.arraycopy(nums, 0, alphaNums, 0, nums.length);
            System.arraycopy(letters, 0, alphaNums, nums.length, lettersAllowed.length);
            System.out.println(Arrays.toString(alphaNums));

            answer = generateAnswer(requiredDigits, symbolsAllowed, alphaNums).split("");
            System.out.println(answer);
        }

        // proceed with nums
        else {
            answer = generateAnswer(requiredDigits, symbolsAllowed, nums).split("");
            System.out.println(answer);
        }

        System.out.println("Okay, let's start a game!");
        playGame();
    }
}