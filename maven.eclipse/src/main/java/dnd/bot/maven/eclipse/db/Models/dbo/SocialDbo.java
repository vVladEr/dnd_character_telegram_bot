package dnd.bot.maven.eclipse.db.Models.dbo;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class SocialDbo {
    
    @BsonId
    public ObjectId characterId;

    @BsonProperty
    public String race;
    
    @BsonProperty
    public String characterName;

    @BsonProperty
    public String characterClass;

    public SocialDbo() {}

	public SocialDbo(ObjectId characterId)
	{
		this.characterId = characterId;
		characterName = "Unknown";
	}
}
