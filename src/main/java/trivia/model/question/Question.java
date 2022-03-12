package trivia.model.question;

public abstract class Question {

    private final String question;

    protected Question(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }
}
