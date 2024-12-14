package dnd.bot.maven.eclipse.db.Models.dbo;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class BasicDescriptionDbo implements IDbo {

    @BsonId
    public ObjectId id;

    @BsonProperty
    public ObjectId characterId;

    @BsonProperty
    public String name;

    @BsonProperty
    public String description;

    @BsonProperty
    public String icon;

    public BasicDescriptionDbo(){}

    public BasicDescriptionDbo(ObjectId characterId, String name, String description)
    {
        this(characterId, name, description, "");
    }

    public BasicDescriptionDbo(ObjectId characterId, String name, String description, String icon)
    {
        this.characterId = characterId;
        this.name = name;
        this.description = description;
        this.icon = icon;
    }
}
