package trivia.model.question;

public class PopQuestion extends Question {

    public PopQuestion(int index) {
        super("Pop Question " + index);
    }

    @Override
    public String getQuestionCategory() {
        return "Pop";
    }
}
