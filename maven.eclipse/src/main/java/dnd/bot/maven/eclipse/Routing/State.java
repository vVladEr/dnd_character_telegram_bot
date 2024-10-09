package dnd.bot.maven.eclipse.Routing;
import java.util.HashMap;

public abstract class State {
    public HashMap<String, State> possibleTransitions;

    abstract void render();
}
