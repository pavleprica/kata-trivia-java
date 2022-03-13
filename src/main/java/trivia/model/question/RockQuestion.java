package trivia.model.question;

public class RockQuestion extends Question {

    public RockQuestion(int index) {
        super("Rock Question " + index);
    }

    @Override
    public String getQuestionCategory() {
        return "Rock";
    }
}
