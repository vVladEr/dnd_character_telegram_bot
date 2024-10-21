package dnd.bot.maven.eclipse.User.Character.Description;

import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Description.Features.Features;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.Personality;
import dnd.bot.maven.eclipse.User.Character.Description.Possession.Possession;

import java.util.HashMap;
import java.util.List;

public class Description extends State {
    public Personality personality;
    public Features features;
    public HashMap<String, List<Possession>> possessions;

    @Override
	public void render() {
		possibleTransitions = new HashMap<String, State>() {{
            possibleTransitions.put("moveToPersonality", personality);
            possibleTransitions.put("moveToFeatures", features);
        }};
	}
}
