package dnd.bot.maven.eclipse.db.dbo;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;



public class AppearenceDbo {
    @BsonId
    public ObjectId characterId;

    @BsonProperty
    public String alignment;

    @BsonProperty
    public int height;

    @BsonProperty
    public int age;

    @BsonProperty
    public int weight;

    @BsonProperty
    public String backGround;

    @BsonProperty
    public String characterBackground;

    @BsonProperty
    public String allies;

    @BsonProperty
    public String personality;

    @BsonProperty
    public String ideals;

    @BsonProperty
    public String bonds;

    @BsonProperty
    public String flaws;

    public AppearenceDbo()
    {
    }
}
