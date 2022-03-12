package trivia.model.question;

public class RockQuestion extends Question {

    public RockQuestion(int index) {
        super("Rock question " + index);
    }

    @Override
    public String getQuestionCategory() {
        return "Rock";
    }
}
