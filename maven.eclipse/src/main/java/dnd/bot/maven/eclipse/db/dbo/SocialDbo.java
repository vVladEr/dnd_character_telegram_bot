package dnd.bot.maven.eclipse.db.dbo;

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
}
