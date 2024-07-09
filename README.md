# BullsAndCows

## How It's Made:

This is a **Java** console application project from www.Hyperskill.org.

The application is a game where the user will try to guess the secret code. The user has the ability to select the length of the secret code and the ability to determine rather or not to use only numbers for the code, or both numbers and letters. 

When the application runs, the user will be prompted to enter the length of the secret code, followed by how many symbols, in order to determine the difficulty. If the symbols are 10 or less, the secret code will only include numbers from 0-9. If the symbols are more than 10, letters will start to be included. The more symbols allowed for the code, the more letters that are potentially allowed in the secret code up to 26. Allowing the secret code to potentially have 36 options (10 numbers, 26 letters). An array is created with a combination of letters/numbers based on the inputs and passed to our generateAnswer method to randomly create the secret code. 

**Generating the secret code**: The general idea here was to create a method (generateAnswer) that will take a array of potential letters and numbers that will be part of the secret code, build the secret code randomly, and return the secret code for the game. In this method, I choose to use Math.random() to randomly get the index of a letter/number from the array to add to the secret code. I did not want to have duplicate values in the secret code, so to prevent that I included a set that kept track of the index of letter/numbers that are already in the code. If it was already in the set, the program would just choose another until we have one that is not in the set. Then, the letter/number would be added to the secret code. This went on until the length of the secret code is equal to the number of what the user has specified. 



*will update more soon*

## Lessons Learned:
• When asking for an integer input using scanner.nextInt() does not consume the line, meaning that any additional logging or input will be put in the same line. If you want the integer to only be on that line and then proceed to the next, the best way do it is to convert the string input into an integer using Integer.parseInt(scanner.nextLine()).

• I also learned how to implement error handling. When converting the user input into a number you need to be able to check if it is a number and handle the case when it is not. Best way to do it is to wrap the input in a try-catch block and handle the error as a NumberFormatException. 

• Unlike Python and Javascript, Java does not have a simple method to concat arrays. I learned that one way arrays can be combined is to create a new array and initalizing the length of the new array to be the combined length of the 2 arrays I want to join. Then I was able to use System.arraycopy() to append the values of each array to the new one.

