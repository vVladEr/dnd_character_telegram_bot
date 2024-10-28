package dnd.bot.maven.eclipse.Routing;
import java.util.Stack;


public class Router {
    private static final Router router = new Router();
    private static final Stack<State> transitionsStack = new Stack<State>();

    private Router() {
    }

    public static Router getInstance() {
        return router;
    }

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