package dnd.bot.maven.eclipse.db.Models.dbo;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class LevelDbo {

    @BsonId
    public ObjectId characterId;
    
    @BsonProperty
    public int currentLevel;

    @BsonProperty
    public int currentExp;

    @BsonProperty
    public int necessaryExp;

    public LevelDbo(){}

    public LevelDbo(ObjectId characterId)
	{
		this.characterId = characterId;
        currentLevel = 1;
        necessaryExp = 300;
	}
}
