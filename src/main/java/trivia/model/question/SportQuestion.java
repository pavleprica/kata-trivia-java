package trivia.model.question;

public class SportQuestion extends Question {

    public SportQuestion(int index) {
        super("Sport question " + index);
    }

    @Override
    public String getQuestionCategory() {
        return "Sport";
    }
}
