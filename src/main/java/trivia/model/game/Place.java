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

    public Question drawQuestionFromStack() {
        return questionStack.removeFirst();
    }

    public String getQuestionCategoryFromStack() {
        return questionStack.getFirst().getQuestionCategory();
    }

    public int getPlaceLocation() {
        return placeLocation;
    }
}
