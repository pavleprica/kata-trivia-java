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

    private Question drawQuestionFromStack() {
        return questionStack.pop();
    }

    public int getPlaceLocation() {
        return placeLocation;
    }
}
