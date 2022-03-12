package trivia.model.question;

public class PopQuestion extends Question {

    public PopQuestion(int index) {
        super("Pop question " + index);
    }

    @Override
    public String getQuestionCategory() {
        return "Pop";
    }
}
