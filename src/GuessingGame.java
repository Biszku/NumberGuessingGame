import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

    int number;
    int attempts;
    int userGuess;
    Level difficulty;

    public GuessingGame() {}

    public void run() {
        resetGame();
        displayWelcomeMessage();
        displayGameOptionsMessage();
        difficulty = Level.values()[readUserDifficultyInput() - 1];
        displaySelectedLevelMessage(difficulty);

        while (number != userGuess && attempts < difficulty.getMaxAttempts()) {

            userGuess = readUserGuess();
            attempts++;
            if (userGuess != number) {
                System.out.printf("Incorrect! The number is %s than %d.\n\n", number > userGuess ? "greater" : "less", userGuess);
            }
        }

        if (number == userGuess) {
            System.out.printf("Congratulations! You guessed the correct number in %d %s.\n", attempts, attempts == 1 ? "attempt" : "attempts");
        } else {
            System.out.printf("Sorry, you have run out of attempts. The correct number was %d.\n", number);
        }
    }

    private void resetGame() {
        Random random = new Random();
        number = random.nextInt(100) + 1;
        attempts = 0;
    }

    private void displayWelcomeMessage() {
        System.out.println("""
                Welcome to the Number Guessing Game!
                I'm thinking of a number between 1 and 100.
                You're job is to guess the correct number.
                """);
    }

    private void displayGameOptionsMessage() {
        StringBuilder message = new StringBuilder("Please select the difficulty level:\n");
        for (Level level : Level.values()) {
            message.append("%d. %s (%d %s)\n".formatted(
                    level.ordinal() + 1,
                    level,
                    level.getMaxAttempts(),
                    level.getMaxAttempts() == 1 ? "attempt" : "attempts")
            );
        }
        System.out.println(message);
    }

    private int readUserDifficultyInput() {
        int choice;

        displayUserPrompt();
        choice = readUserInput();
        System.out.println();
        return choice;
    }

    private void displayUserPrompt() {
        System.out.print("Enter your choice: ");
    }

    private int readUserInput() {
        try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            return readUserDifficultyInput();
        }
    }

    private void displaySelectedLevelMessage(Level difficulty) {
        System.out.printf("""
                Great! You have selected the %s difficulty level.
                Let's start the game!
                
                """, difficulty);
    }

    private int readUserGuess () {
        int guess;
        displayUserGuessPrompt();
        guess = readUserInput();
        System.out.println();
        return guess;
    }

    private void displayUserGuessPrompt() {
        System.out.print("Enter your guess: ");
    }
}
