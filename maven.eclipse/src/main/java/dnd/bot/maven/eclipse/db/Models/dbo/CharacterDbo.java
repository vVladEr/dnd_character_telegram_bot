package dnd.bot.maven.eclipse.db.Models.dbo;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class CharacterDbo implements IDbo {

    @BsonId
    public ObjectId id;

    @BsonProperty
    public String name;

    @BsonProperty
    public String userId;

    public CharacterDbo() {
    }

    public CharacterDbo(ObjectId characterId, String characterName, String userId) {
        this.id = characterId;
        this.name = characterName;
        this.userId = userId;
    }
}
