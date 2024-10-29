package dnd.bot.maven.eclipse.db.dbo;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;


public class ItemDBO {

    @BsonId
    public ObjectId itemId;

    @BsonProperty
    public ObjectId characterId;

    @BsonProperty
    public String category;

    @BsonProperty
    public double weight;

    @BsonProperty
    public int amount;

    @BsonProperty
    public boolean isMagic;

    @BsonProperty
    public boolean isEquiped;

    @BsonProperty
    public String description;

    public ItemDBO(){}

    public ItemDBO(ObjectId characterId, String description, int amount)
    {
        this.characterId = characterId;
        this.description = description;
        this.amount = amount;
    }
}
