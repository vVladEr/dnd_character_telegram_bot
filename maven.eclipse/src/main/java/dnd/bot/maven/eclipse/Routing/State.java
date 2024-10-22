package dnd.bot.maven.eclipse.Routing;
import java.util.HashMap;

import org.bson.codecs.pojo.annotations.BsonIgnore;


public abstract class State {
    @BsonIgnore
    public HashMap<String, State> possibleTransitions;

    public abstract void render();
}
