import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        BlackJack game = new BlackJack();
        boolean playNewGame = false;

        do {
            boolean blackjack = game.play();

            if(!blackjack) {
                while(game.playerCanHit()) {
                    char ch = game.hit();

                    if(ch == 'h') {
                        game.addPlayerCard();
                        game.updateScreen(game.getDealerCards(), game.getPlayerCards(), true);
                    } else if(ch == 'p') {
                        break;
                    }

                    if(!game.playerCanHit()) {
                        System.out.println("\nPlayer cards exceed 21. Player cannot add another card");
                    }
                }

                while(game.dealerCanHit()) {
                    game.addDealerCard();
                    System.out.println("The dealer hit another card");
                    game.updateScreen(game.getDealerCards(), game.getPlayerCards(), true);
                }

                System.out.println("\nDealer cards exceed 17. Dealer cannot add another card");
                game.updateScreen(game.getDealerCards(), game.getPlayerCards(), true);

                game.checkWinner();
            }

            game.clear();
            playNewGame = newGame();

            /*if(playNewGame) {
                try {
                    clearScreen();
                } catch (final Exception e) {
                    System.out.println(e.getMessage());
                }
            }*/
        } while(playNewGame);
    }

    public static boolean newGame() throws InputMismatchException {
        Scanner input = new Scanner(System.in);
        char ch;
        do {
            try {
                System.out.println("\nWould you like to play again?(y, n): ");
                ch = input.next().charAt(0);
                ch = Character.toLowerCase(ch);
                if (ch != 'y' && ch != 'n') throw new InputMismatchException("ERROR: Please enter character 'y' or 'n': ");
                return ch == 'y';
            } catch (InputMismatchException error) {
                System.out.println(error.getMessage());
            }
        } while(true);
    }

    public static void clearScreen() throws IOException {
        final String os = System.getProperty("os.name");

        if(os.contains("Windows")) {
            Runtime.getRuntime().exec("cls");
        } else {
            Runtime.getRuntime().exec("clear");
        }
    }
}
