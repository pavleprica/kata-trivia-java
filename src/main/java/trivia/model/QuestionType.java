package trivia.model;

public enum QuestionType {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock");

    private final String value;

    QuestionType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
