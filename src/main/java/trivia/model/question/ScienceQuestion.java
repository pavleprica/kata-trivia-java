package trivia.model.question;

public class ScienceQuestion extends Question {

    public ScienceQuestion(int index) {
        super("Science Question " + index);
    }

    @Override
    public String getQuestionCategory() {
        return "Science";
    }
}
