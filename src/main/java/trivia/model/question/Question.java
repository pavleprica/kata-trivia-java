package trivia.model.question;

public abstract class Question {

    private final String questionSentence;

    protected Question(String questionSentence) {
        this.questionSentence = questionSentence;
    }

    public String getQuestionSentence() {
        return questionSentence;
    }

    public abstract String getQuestionCategory();
}
