import java.util.*;

public class BlackJack {
    private Player player;
    private Player dealer;
    private ArrayList<Integer> used = new ArrayList<>();
    final int[] CARDS = {
            2, 3, 4, 5, 6, 7, 8, 9, 10,  2, 3, 4, 5, 6, 7, 8, 9, 10,  2, 3, 4, 5, 6, 7, 8, 9, 10,
            2, 3, 4, 5, 6, 7, 8, 9, 10,  11, 10, 10, 10,  11, 10, 10, 10,  11, 10, 10, 10,  11, 10, 10, 10
    };

    HashMap<Integer, Character> cardLetter = new HashMap<>();

    public BlackJack() {
        setCardLetter();
    }

    public boolean play() {
        dealer = new Player(new int[] {getCard(), getCard()}, 0);
        dealer.setScore(map(dealer.getCards()[0]), map(dealer.getCards()[1]));
        player = new Player(new int[] {getCard(), getCard()}, 0);
        player.setScore(map(player.getCards()[0]), map(player.getCards()[1]));

        updateScreen(dealer.getCards(), player.getCards(), false);

        int dealerTotalScore = map(dealer.getCards()[0]) + map(dealer.getCards()[1]);

        return blackJackCheck(dealerTotalScore, player.getScore());
    }

    public void setCardLetter() {
        for(int index = 36; index+4 <= CARDS.length; index+=4) {
            cardLetter.put(index, 'A');
            cardLetter.put(index+1, 'J');
            cardLetter.put(index+2, 'Q');
            cardLetter.put(index+3, 'K');
        }
    }

    public int map(int index) { return CARDS[index]; }

    public void displayCard(int index) {
        if (index >= 36) {
            System.out.print(cardLetter.get(index) + " ");
        } else {
            System.out.print(CARDS[index] + " ");
        }
    }

    public void displayPlayerCard(int[] cards) {
        for (int card : cards) displayCard(card);
        System.out.println();
    }

    public void displayDealerCard(int[] cards, boolean showHiddenCard) {
        if(!showHiddenCard) {
            displayCard(cards[0]);
        } else {
            for (int card : cards) displayCard(card);
        }
        System.out.println();
    }

    public int getCard() {
        int index = new Random().nextInt(52);

        while(used.contains(index)) {
            index = new Random().nextInt(52);
        }
        used.add(index);
        return index;
    }

    public boolean blackJackCheck(int dealerScore, int playerScore) {
        if(dealerScore == 21) {
            System.out.println("BLACKJACK!!! The dealer wins");
            System.out.print("Dealer's cards: ");
            displayPlayerCard(dealer.getCards());
        } else if(playerScore == 21) {
            System.out.println("BLACKJACK!!! The player wins");
            System.out.print("Dealer's cards: ");
            displayPlayerCard(dealer.getCards());
        } else {
            return false;
        }
        return true;
    }

    public void updateScreen(int[] dealers, int[] users, boolean showHidden) {
        System.out.print("\nDealer's card: ");
        displayDealerCard(dealers, showHidden);
        System.out.print("Player's card: ");
        displayPlayerCard(users);
        System.out.println();
    }

    public boolean playerCanHit() { return player.getScore() < 22; }
    public boolean dealerCanHit() { return dealer.getScore() < 18; }

    public char hit() throws InputMismatchException{
        Scanner input = new Scanner(System.in);
        char ch;
        do {
            try {
                System.out.println("\nWould you like to hit or pass? Enter h or p: ");
                ch = input.next().charAt(0);
                ch = Character.toLowerCase(ch);
                if (ch != 'h' && ch != 'p') throw new InputMismatchException("ERROR: Please enter character h or p: ");
                return ch;
            } catch (InputMismatchException error) {
                System.out.println(error.getMessage());
            }
        } while(true);
    }


    public void addDealerCard() {
        int index = getCard();
        dealer.addCard(index, CARDS[index]);
    }

    public void addPlayerCard() {
        int index = getCard();
        player.addCard(index, CARDS[index]);
    }

    public int[] getDealerCards() { return dealer.getCards(); }
    public int[] getPlayerCards() { return player.getCards(); }

    public void checkWinner() {
         if (dealer.getScore() <= 21 && player.getScore() <= 21) {
            if(player.getScore() > dealer.getScore()) {
                System.out.println("\nThe PLAYER wins!!! :)");
            } else if(player.getScore() < dealer.getScore()) {
                System.out.println("\nThe dealer won!!! :(");
            } else {
                System.out.println("\nIt's a TIE!!!");
            }
        } else if (dealer.getScore() > 21 && player.getScore() <= 21) {
            System.out.println("\nThe PLAYER wins!!! :)");
        } else if (player.getScore() > 21 && dealer.getScore() <= 21) {
            System.out.println("\nThe dealer won!!! :(");
        } else {
            System.out.println("\nBoth dealer and player lost :(");
        }
        System.out.println("SCORES:\n_______________________________");
        System.out.println("Dealer: " + dealer.getScore());
        System.out.println("Player: " + player.getScore());
        System.out.println("_______________________________");
    }

    public void clear() {
        dealer.clear();
        player.clear();
        used.clear();
    }
}
