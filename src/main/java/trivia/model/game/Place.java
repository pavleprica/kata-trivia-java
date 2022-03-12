package trivia.model.game;

import java.util.Deque;
import trivia.model.question.Question;

public class Place {

    private final int placeLocation;

    private Deque<Question> questionStack;

    public Place(int placeLocation, Deque<Question> questionStack) {
        this.placeLocation = placeLocation;
        this.questionStack = questionStack;
    }

    public Question drawQuestionFromStack() {
        return questionStack.pop();
    }

    public String getQuestionCategoryFromStack() {
        assert questionStack.peek() != null;
        return questionStack.peek().getQuestionCategory();
    }

    public int getPlaceLocation() {
        return placeLocation;
    }
}
