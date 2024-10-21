package dnd.bot.maven.eclipse.User.Character.Grade;

import java.util.HashMap;

import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Grade.Spells.Spells;

public class Grade extends State {
    public int maxCount;
    public int count;
    public Spells spells;

    @Override
	public void render() {
		possibleTransitions = new HashMap<String, State>() {{
            possibleTransitions.put("moveToSpells", spells);
        }};
	}
}
