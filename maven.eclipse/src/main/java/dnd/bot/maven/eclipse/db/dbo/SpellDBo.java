package dnd.bot.maven.eclipse.db.dbo;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import java.util.HashMap;

public class SpellDBo {
    @BsonId
    public ObjectId characterId;

    @BsonProperty
    public int grade;

    @BsonProperty
    public int maxCount;

    @BsonProperty 
    public int CurrentCount;

    @BsonProperty
    public HashMap<String, String> spells;
}
