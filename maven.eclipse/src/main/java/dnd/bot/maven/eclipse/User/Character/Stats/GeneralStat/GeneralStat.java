package dnd.bot.maven.eclipse.User.Character.Stats.GeneralStat;

import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Stats.GeneralStat.Skills.Skill;

import java.util.HashMap;

public abstract class GeneralStat extends State {
	public String name;
	public int value;
	public HashMap<String, Skill> skills;
	public int checkValue;
	public int saveThrowValue;

	@Override
	public void render() {

	}
}
