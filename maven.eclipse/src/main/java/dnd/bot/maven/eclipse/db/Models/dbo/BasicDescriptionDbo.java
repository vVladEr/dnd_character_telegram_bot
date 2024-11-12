package dnd.bot.maven.eclipse.db.Models.dbo;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class BasicDescriptionDbo {

    @BsonId
    public ObjectId descId;

    @BsonProperty
    public ObjectId characterId;

    @BsonProperty
    public String name;

    @BsonProperty
    public String description;

    public BasicDescriptionDbo(){}

    public BasicDescriptionDbo(ObjectId characterId, String name, String description)
    {
        this.characterId = characterId;
        this.name = name;
        this.description = description;
    }
}
