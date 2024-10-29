package dnd.bot.maven.eclipse.Routing;
import java.util.Stack;


public class Router {
    private final Stack<State> transitionsStack = new Stack<State>();

    public State getCurrentState() {
        return transitionsStack.peek();
    }

    public void makeTransition(String callBackCommand) {
        if (callBackCommand == "back")
        {
            transitionsStack.pop();
            return;
        }

        var currentState = transitionsStack.peek();

        if (!currentState.possibleTransitions.containsKey(callBackCommand)) {
            throw new IllegalArgumentException("wrong callBack");
        }

        var nextState = currentState.possibleTransitions.get(callBackCommand);

        transitionsStack.add(nextState);
    }
}   