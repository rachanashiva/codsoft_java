package numbergame;
import java.util.Random;  
import java.util.Scanner; 
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        Random random = new Random();            
        int lowerBound = 1;                  
        int upperBound = 100;                    
        int randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound; 
        int userGuess = 0;                   
        int attempts = 0;                      
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Try to guess the number between " + lowerBound + " and " + upperBound);
while (userGuess != randomNumber)
{
            System.out.print("Enter your guess: ");
            userGuess = scanner.nextInt(); 
            attempts++;

            if (userGuess < randomNumber) {
                System.out.println("Too low! Try again.");
            } else if (userGuess > randomNumber) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Congratulations! You've guessed the correct number.");
                System.out.println("It took you " + attempts + " attempts.");
            }
        }
        
        scanner.close();
    }
}
