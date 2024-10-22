package dnd.bot.maven.eclipse.User.Character.Stats.GeneralStat;

import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Stats.GeneralStat.Skills.Skill;
import dnd.bot.maven.eclipse.db.dbo.GeneralStatDBO;

import java.util.HashMap;

import org.bson.types.ObjectId;

public class GeneralStat extends State {

	public ObjectId characterId;
	public String name;
	public int value;
	public HashMap<String, Skill> skills;
	public int checkValue;
	public int saveThrowValue;

	public GeneralStat(GeneralStatDBO dboGeneralStat)
	{
		characterId = dboGeneralStat.characterId;
		name = dboGeneralStat.statName;
		value = dboGeneralStat.value;
		skills = dboGeneralStat.skills;
		checkValue = dboGeneralStat.checkValue;
		saveThrowValue = dboGeneralStat.saveThrowValue;
	}

	@Override
	public void render() {

	}
}
