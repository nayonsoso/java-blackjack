package domain.card;

import java.util.Objects;

public class Card {
    public static final int BUST_DEADLINE = 21;

    private final Shape shape;
    private final Number number;

    public Card(Shape shape, Number number) {
        this.shape = shape;
        this.number = number;
    }

    public boolean isAce() {
        return number.isAce();
    }

    public int getScore() {
        return number.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return shape == card.shape && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, number);
    }

    @Override
    public String toString() {
        return "Card{" +
                "shape=" + shape +
                ", number=" + number +
                '}';
    }

    public String getNumberDescription() {
        return number.getDescription();
    }

    public String getShapeDescription() {
        return shape.getDescription();
    }
}