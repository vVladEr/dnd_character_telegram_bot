package dnd.bot.maven.eclipse.db.dbo;

import java.util.ArrayList;
import java.util.UUID;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import dnd.bot.maven.eclipse.User.User;

public final class UserDBO {

    @BsonId
    public final ObjectId  id;

    @BsonProperty("characters")
    public final ArrayList<UUID> characters;

    public UserDBO(User user)
    {
        id = user.GetId();
        characters = user.characters;
    }

    @BsonCreator
    public UserDBO(@BsonId ObjectId id, @BsonProperty("characters") ArrayList<UUID> characters)
    {
        this.id = id;
        this.characters = characters;
    }

    public ObjectId GetId()
    {
        return id;
    }

    public ArrayList<UUID> GetCharacters()
    {
        return characters;
    }
}
