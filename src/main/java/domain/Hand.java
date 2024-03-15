package domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Hand {

    private final List<Card> cards = new LinkedList<>();

    public void add(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public int countAceCard() {
        return (int) this.cards.stream()
                .filter(Card::isAceCard)
                .count();
    }

    private boolean isBustWithAce(int sum, int aceCount) {
        return sum > 21 && aceCount > 0;
    }

    public int calculateScore() {
        int aceCount = countAceCard();
        int sum = calculateSum();

        while (isBustWithAce(sum, aceCount)) {
            aceCount--;
            sum -= 10;
        }

        return sum;
    }

    private int calculateSum() {
        return cards.stream()
                .mapToInt(Card::getRankValue)
                .sum();
    }

    public int getSize() {
        return this.cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
