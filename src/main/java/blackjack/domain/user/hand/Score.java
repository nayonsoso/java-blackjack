package blackjack.domain.user.hand;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;

public class Score {
    public static final Score ZERO = new Score(0);
    public static final Score BUST = new Score(-1);
    private static final Map<Integer, Score> CACHE = new HashMap<>();
    private static final int ADDITIONAL_ACE_SCORE = 10;
    private static final int BUST_SCORE = 22;

    private final int score;

    static {
        IntStream.range(Symbol.ACE.getScore(), BUST_SCORE)
            .forEach(score -> CACHE.put(score, new Score(score)));
    }

    private Score(int score) {
        this.score = score;
    }

    public static Score valueOf(int number) {
        Score score = CACHE.get(number);

        if (Objects.isNull(score)) {
            score = new Score(number);
        }
        return score;
    }

    public static Score valueOf(Card card) {
        return CACHE.get(card.getScore());
    }

    public Score add(Card card) {
        int currentScore = score + card.getScore();

        if (card.isAce() && currentScore + ADDITIONAL_ACE_SCORE < BUST_SCORE) {
            return Score.valueOf(currentScore + ADDITIONAL_ACE_SCORE);
        }
        return Score.valueOf(currentScore);
    }

    public boolean isLowerThan(int score) {
        return this.score <= score;
    }

    public boolean isMoreThan(int score) {
        return this.score > score;
    }

    public boolean isMoreThan(Score score) {
        return this.score > score.score;
    }

    public int getScore() {
        return score;
    }

    public boolean isBust() {
        return score >= BUST_SCORE;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Score that = (Score)object;
        return score == that.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}