package trivia.model.game;

import java.util.LinkedList;
import trivia.model.question.Question;

public class Place {

    private final int placeLocation;

    private LinkedList<Question> questionStack;

    public Place(int placeLocation, LinkedList<Question> questionStack) {
        this.placeLocation = placeLocation;
        this.questionStack = questionStack;
    }

    private Question drawQuestionFromStack() {
        return questionStack.removeFirst();
    }

    public int getPlaceLocation() {
        return placeLocation;
    }
}
