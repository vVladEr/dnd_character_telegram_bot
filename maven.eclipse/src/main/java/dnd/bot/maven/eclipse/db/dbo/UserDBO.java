package dnd.bot.maven.eclipse.db.dbo;

import java.util.ArrayList;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;


public final class UserDBO {

    @BsonId
    public final String id;

    @BsonProperty("characters")
    public final ArrayList<ObjectId> characters;

    public UserDBO(String userId)
    {
        id = userId;
        characters = new ArrayList<ObjectId>();
    }

    @BsonCreator
    public UserDBO(@BsonId String id, @BsonProperty("characters") ArrayList<ObjectId> characters)
    {
        this.id = id;
        this.characters = characters;
    }

    public String GetId()
    {
        return id;
    }

    public ArrayList<ObjectId> GetCharacters()
    {
        return characters;
    }
}
