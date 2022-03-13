package trivia.model.question;

public class SportQuestion extends Question {

    public SportQuestion(int index) {
        super("Sports Question " + index);
    }

    @Override
    public String getQuestionCategory() {
        return "Sports";
    }
}
