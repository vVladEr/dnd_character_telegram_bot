package dnd.bot.maven.eclipse.db.dbo;

import java.util.HashMap;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import dnd.bot.maven.eclipse.User.Character.Description.Personality.Appearance.Appearance;


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
    public HashMap<String, String> characterBackground;

    @BsonProperty
    public HashMap<String, String> allies;

    @BsonProperty
    public HashMap<String, String> personality;

    @BsonProperty
    public HashMap<String, String> ideals;

    @BsonProperty
    public HashMap<String, String> bonds;

    @BsonProperty
    public HashMap<String, String> flaws;

    public AppearenceDbo()
    {
    }

    public AppearenceDbo(Appearance ap)
    {
        //TODO дописать при подключении
    }
}
