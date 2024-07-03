# BullsAndCows

## How It's Made:

This is a **Java** console application project from www.Hyperskill.org.

The application is a game where the user will try to guess the secret code. The user has the ability to select the length of the secret code and the ability to determine rather or not to use only numbers for the code, or both numbers and letters. 

When the application runs, the user will be prompted to enter the length of the secret code, followed by how many symbols, in order to determine the difficulty. If the symbols are 10 or less the secret code will only include numbers from 0-9. If the symbols are more than 10, letters will start to be included. The more symbols allowed for the code, the more letters that are potentially allowed in the secret code up to 36 (10 numbers, 26 letters).

*will update more soon*

## Lessons Learned:
• When asking for an integer input using scanner.nextInt() does not consume the line, meaning that any additional logging or input will be put in the same line. If you want the integer to only be on that line and then proceed to the next, the best way do it is to convert the string input into an integer using Integer.parseInt(scanner.nextLine()).

• I also learned how to implement error handling. When converting the user input into a number you need to be able to check if it is a number and handle the case when it is not. Best way to do it is to wrap the input in a try-catch block and handle the error as a NumberFormatException. 
