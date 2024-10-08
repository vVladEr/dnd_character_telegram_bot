package dnd.bot.maven.eclipse.User.Character.Stats;

import dnd.bot.maven.eclipse.User.Character.Stats.Skills.Skill;

import java.util.Dictionary;

public abstract class GeneralStat {
	public String name;
	public int value;
	public Dictionary<String, Skill> skills;
	public int checkValue;
	public int saveThrowValue;
}
