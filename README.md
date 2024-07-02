# BullsAndCows

## How It's Made:

**Tech used:Java

This is a console application project from www.Hyperskill.org.

The application is a game where the user will try to guess the secret code. The user has the ability to select the length of the secret code and the ability to determine rather or not to use only numbers for the code, or both numbers and letters. 

*will update further on how the application runs*

## Lessons Learned:
• When asking for an integer input using scanner.nextInt() does not consume the line, meaning that any additional logging or input will be put in the same line. If you want the integer to only be on that line and then proceed to the next, the best way do it is to convert the string input into an integer using Integer.parseInt(scanner.nextLine()).

• I also learned how to implement error handling. When converting the user input into a number you need to be able to check if it is a number and handle the case when it is not. Best way to do it is to wrap the input in a try-catch block and handle the error as a NumberFormatException. 


