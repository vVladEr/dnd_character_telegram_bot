package dnd.bot.maven.eclipse.User.Character.Description.Personality.Appearance;

import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.BasicDescription.BasicDescription;

public class Appearance extends State {
    public String alignment;
    public int height;
    public int age;
    public int weight;
    public String backGround;
    public BasicDescription characterBackground;
    public BasicDescription allies;
    public BasicDescription personality;
    public BasicDescription ideals;
    public BasicDescription bonds;
    public BasicDescription flaws;

    @Override
	public void render() {
		
	}
}
