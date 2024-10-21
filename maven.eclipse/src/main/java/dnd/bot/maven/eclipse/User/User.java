package dnd.bot.maven.eclipse.User;

import java.util.HashMap;
import java.util.List;

import dnd.bot.maven.eclipse.Routing.State;

public class User extends State {
    public int id;
    public List<Character> characters;

    public User() {
        possibleTransitions = new HashMap<String, State>();
    }

    @Override
    public void render() {
        
    }
}
