import java.util.ArrayList;

public class Player {
    private int score;
    private ArrayList<Integer> cards;

    public Player(int[] c, int... s) {
        setCards(c);
        setScore(s);
    }

    public void setScore(int... s) {
        for(int i : s) {
            if(i == 11 && this.score + i > 21) {
                this.score += 1;
            } else if(i == 11 && this.score + i < 21) {
                this.score += 11;
            } else {
                this.score += i;
            }
        }
    }

    public void setCards(int[] cards, int... s) {
        this.cards = new ArrayList<>();
        for(int i : cards)
            this.cards.add(i);
        setScore(s);
    }

    public void addCard(int index, int score) {
        this.cards.add(index);
        this.score += score;
    }

    public int getScore() {
        return this.score;
    }


    public void clear() {
        this.score = 0;
        this.cards.clear();
    }

    public int[] getCards() {
        int[] cards = new int[this.cards.size()];
        for(int i = 0; i < this.cards.size(); i++)
            cards[i] = this.cards.get(i);
        return cards;
    }

}


