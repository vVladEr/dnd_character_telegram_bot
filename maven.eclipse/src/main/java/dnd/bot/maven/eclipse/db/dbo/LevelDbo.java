package dnd.bot.maven.eclipse.db.dbo;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import dnd.bot.maven.eclipse.User.Character.Description.Personality.Level.Level;

public class LevelDbo {

    @BsonId
    public ObjectId characterId;
    
    @BsonProperty
    public int currentLevel;

    @BsonProperty
    public int currentExp;

    @BsonProperty
    public int necessaryExp;

    public LevelDbo(){}
}
