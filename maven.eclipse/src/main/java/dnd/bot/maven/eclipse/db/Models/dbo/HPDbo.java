package dnd.bot.maven.eclipse.db.Models.dbo;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class HPDbo implements IDbo {
    @BsonId
    public ObjectId characterId;

    @BsonProperty
    public int maxHP;

    @BsonProperty
    public int bonusMaxHP;

    @BsonProperty
    public String hpDice;

    @BsonProperty
    public int tempHP;

    public HPDbo() {}
    
    public HPDbo(ObjectId characterId)
	{
		this.characterId = characterId;
	}
}
