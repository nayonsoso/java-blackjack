package blackjack.model.participant;

import java.util.Objects;

public class Name {

    private final String name;

    public Name(String name) {
        validateIsEmpty(name);
        this.name = name.trim();
    }

    private void validateIsEmpty(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 빈 문자열일 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}