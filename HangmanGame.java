import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HangmanGame {
    private static final int MAX_MISSES = 7;
    private static final String CODEWORD = "cryptic";
    private static final String HINT = "Confedential";
    
    private int misses;
    private String codeword;
    private String answer;
    private List<Character> incorrect;
    private boolean guess;

    public HangmanGame() {
        this.codeword = CODEWORD;
        this.answer = "_".repeat(codeword.length());
        this.misses = 0;
        this.incorrect = new ArrayList<>();
        this.guess = false;
    }

    private void greet() {
        System.out.println("\n\n****************************************************************************");
        System.out.println("|                               Hangman Game                               |");
        System.out.println("****************************************************************************");
        System.out.println("Instruction: Save man from being hanged by guessing all the letters correctly.");
        System.out.println("\nHint: " + HINT + "\n");
    }

    private void displayMisses() {
        switch (misses) {
            case 0:
                System.out.println("    +----+");
                System.out.println("    |    |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println("============");
                break;
            case 1:
                System.out.println("    +----+");
                System.out.println("    |    |");
                System.out.println("    O    |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println("============");
                break;
            case 2:
                System.out.println("    +----+");
                System.out.println("    |    |");
                System.out.println("    O    |");
                System.out.println("    |    |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println("============");
                break;
            case 3:
                System.out.println("    +----+");
                System.out.println("    |    |");
                System.out.println("    O    |");
                System.out.println("   /|    |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println("============");
                break;
            case 4:
                System.out.println("    +----+");
                System.out.println("    |    |");
                System.out.println("    O    |");
                System.out.println("   /|\\   |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println("============");
                break;
            case 5:
                System.out.println("    +----+");
                System.out.println("    |    |");
                System.out.println("    O    |");
                System.out.println("   /|\\   |");
                System.out.println("   /     |");
                System.out.println("         |");
                System.out.println("============");
                break;
            case 6:
                System.out.println("    +----+");
                System.out.println("    |    |");
                System.out.println("    O    |");
                System.out.println("   /|\\   |");
                System.out.println("   / \\   |");
                System.out.println("         |");
                System.out.println("============");
                break;
        }
    }

    private void displayStatus() {
        System.out.println("Incorrect Guesses:");
        for (char c : incorrect) {
            System.out.print(c + " ");
        }
        System.out.println();
        
        System.out.println("Codeword:");
        for (char c : answer.toCharArray()) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    private void endGame() {
        if (answer.equals(codeword)) {
            System.out.println("\nHooray! You saved the man from being hanged. Congratulations!\n\n");
        } else {
            System.out.println("Sorry! The man is hanged. The correct word was '" + codeword + "'.\n\n");
        }
    }

    public void play() {
        greet();
        Scanner scanner = new Scanner(System.in);
        
        while (!answer.equals(codeword) && misses < MAX_MISSES) {
            displayMisses();
            displayStatus();
            System.out.println("\n------------------------------------------------------------------------");
            System.out.print("\nEnter your guess: ");
            
            char letter = scanner.next().charAt(0);
            
            // Reset guess to false for the current round
            guess = false;
            
            // Check if the guessed letter is in the codeword
            for (int i = 0; i < codeword.length(); i++) {
                if (letter == codeword.charAt(i)) {
                    // Update the answer with the correct guess
                    answer = answer.substring(0, i) + letter + answer.substring(i + 1);
                    guess = true;
                }
            }
            
            // Check if the guess was correct
            if (guess) {
                System.out.println("Correct guess!\n");
            } else {
                System.out.println("\nIncorrect guess. Another portion of the man is drawn.");
                incorrect.add(letter);
                misses++;
            }
        }
        
        endGame();
        scanner.close();
    }

    public static void main(String[] args) {
        HangmanGame game = new HangmanGame();
        game.play();
    }
}

