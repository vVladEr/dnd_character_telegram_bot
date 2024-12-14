package dnd.bot.maven.eclipse.db.Models.dbo;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;


public class ItemDBO extends BasicDescriptionDbo {

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


    public ItemDBO(){}

    public ItemDBO(ObjectId characterId, String name, String description, int amount)
    {
        super(characterId, name, description);
        this.amount = amount;
    }

    public ItemDBO(ObjectId characterId, String name, String description)
    {
        this(characterId, name, description, 1);
    }
}
