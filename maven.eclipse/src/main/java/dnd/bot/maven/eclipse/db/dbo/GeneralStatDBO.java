package dnd.bot.maven.eclipse.db.dbo;

import dnd.bot.maven.eclipse.User.Character.Stats.GeneralStat.GeneralStat;
import dnd.bot.maven.eclipse.User.Character.Stats.GeneralStat.Skills.Skill;
import java.util.HashMap;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class GeneralStatDBO {
    
    @BsonId
    public ObjectId characterId;

    @BsonProperty
	public String statName;

    @BsonProperty
	public int value;

    @BsonProperty
	public HashMap<String, Skill> skills;

    @BsonProperty
	public int checkValue;

    @BsonProperty
	public int saveThrowValue;

    public GeneralStatDBO(GeneralStat stat)
    {
        characterId = stat.characterId;
        statName = stat.name;
        value = stat.value;
        skills = stat.skills;
        checkValue = stat.checkValue;
        saveThrowValue = stat.saveThrowValue;
    }

    public GeneralStatDBO()
    {
    }
}
