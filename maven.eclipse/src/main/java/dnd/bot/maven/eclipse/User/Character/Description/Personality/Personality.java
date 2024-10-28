package dnd.bot.maven.eclipse.User.Character.Description.Personality;

import java.util.HashMap;

import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.Appearance.Appearance;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.HP.HP;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.Level.Level;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.Social.Social;

public class Personality extends State {
    public int Armor;
    public int Speed;
    public int PossessionBonus;
    public boolean inspiration;
    public int exhaustion;
    public HP hp;
    public Level level;
    public Appearance appearance;
    public Social social;

    @Override
	public void render() {
		possibleTransitions = new HashMap<String, State>() {{
            possibleTransitions.put("moveToHP", hp);
            possibleTransitions.put("moveToLevel", level);
            possibleTransitions.put("moveToAppearance", appearance);
            possibleTransitions.put("moveToSocial", social);
        }};
	}
}
