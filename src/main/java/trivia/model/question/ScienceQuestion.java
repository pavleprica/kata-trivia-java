package trivia.model.question;

public class ScienceQuestion extends Question {

    public ScienceQuestion(int index) {
        super("Science question " + index);
    }

    @Override
    public String getQuestionCategory() {
        return "Science";
    }
}
