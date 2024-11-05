package dnd.bot.maven.eclipse.db.dbo;

import java.util.HashMap;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class GeneralStatDBO {
    
    @BsonProperty
    public ObjectId characterId;

    @BsonProperty
	public String statName;

    @BsonProperty
	public int value;

    @BsonProperty
	public HashMap<String, SkillDbo> skills;

    @BsonProperty
	public int checkValue;

    @BsonProperty
	public int saveThrowValue;

    @BsonProperty
    public boolean IsMastered;

    public GeneralStatDBO()
    {
    }
    
    public GeneralStatDBO(ObjectId characterId, String statName)
	{
		this.characterId = characterId;
		this.statName = statName;
        skills = new HashMap<String, SkillDbo>();
	}

    public GeneralStatDBO(ObjectId characterId, String statName, HashMap<String, SkillDbo> skills)
	{
		this.characterId = characterId;
		this.statName = statName;
        this.skills = skills;
	}
}
