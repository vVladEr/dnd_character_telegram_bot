package dnd.bot.maven.eclipse.db.dbo;

import java.util.ArrayList;
import java.util.UUID;


import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import dnd.bot.maven.eclipse.User.User;

public final class UserDBO {

    @BsonProperty("_id")
    private ObjectId  id;

    @BsonProperty("characters")
    private ArrayList<UUID> characters;

    public UserDBO(User user)
    {
        id = user.GetId();
        characters = user.characters;
    }

    public ObjectId GetId()
    {
        return id;
    }

    public ArrayList<UUID> GetCharactersUUIDs()
    {
        return characters;
    }
}
