package dnd.bot.maven.eclipse.db.Models.dbo;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class CharacterDbo {

    @BsonId
    public ObjectId CharacterId;

    @BsonProperty
    public String CharacterName;

    public CharacterDbo(){}

    public CharacterDbo(ObjectId characterId, String characterName){
        CharacterId = characterId;
        CharacterName = characterName;
    }
}
