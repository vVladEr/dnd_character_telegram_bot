package dnd.bot.maven.eclipse.db.dbo;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import dnd.bot.maven.eclipse.User.Character.Inventory.Item.Item;

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

    public ItemDBO(Item item)
    {
        itemId = item.itemId;
        characterId = item.characterId;
        category = item.category;
        weight = item.weight;
        amount = item.amount;
        isMagic = item.isMagic;
        isEquiped = item.isEquiped;
        description = item.description;
    }
}
