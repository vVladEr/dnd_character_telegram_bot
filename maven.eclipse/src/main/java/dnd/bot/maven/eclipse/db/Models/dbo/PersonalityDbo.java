package dnd.bot.maven.eclipse.db.Models.dbo;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;


public class PersonalityDbo implements IDbo {
    @BsonId
    public ObjectId characterId;

    @BsonProperty
    public int armor;

    @BsonProperty
    public int speed;

    @BsonProperty
    public int possessionBonus;

    @BsonProperty
    public boolean inspiration;

    @BsonProperty
    public int exhaustion;

    public PersonalityDbo(){}

	public PersonalityDbo(ObjectId characterId)
	{
		this.characterId = characterId;
	}
}
