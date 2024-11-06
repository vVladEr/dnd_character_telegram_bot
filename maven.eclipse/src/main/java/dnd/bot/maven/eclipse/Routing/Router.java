package dnd.bot.maven.eclipse.Routing;
import java.util.Stack;


public class Router {
    private final Stack<State> transitionsStack = new Stack<State>();

    public State getCurrentState() {
        return transitionsStack.peek();
    }

    public void addState(State newState) {
        transitionsStack.add(newState);
    }

    public String makeTransition(String callBackCommand) {
        if (callBackCommand.equals("back"))
        {
            transitionsStack.pop();
            return "ok";
        }

        var currentState = transitionsStack.peek();

        System.out.println(currentState.possibleTransitions);

        if (!currentState.possibleTransitions.containsKey(callBackCommand)) {
            return "no";
            
        }

        var nextState = currentState.possibleTransitions.get(callBackCommand);

        transitionsStack.add(nextState);

        return "ok";
    }

    public void clear() {
		transitionsStack.clear();
	}
}   