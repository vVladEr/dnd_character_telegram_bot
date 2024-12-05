package dnd.bot.maven.eclipse.db.Models.dbo;

import java.util.ArrayList;


import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;


public final class UserDBO {

    @BsonId
    public final String id;

    @BsonProperty("characters")
    public final ArrayList<CharacterDbo> characters;

    public UserDBO(String userId)
    {
        id = userId;
        characters = new ArrayList<CharacterDbo>();
    }

    @BsonCreator
    public UserDBO(@BsonId String id, @BsonProperty("characters") ArrayList<CharacterDbo> characters)
    {
        this.id = id;
        this.characters = characters;
    }

    public String GetId()
    {
        return id;
    }

    public ArrayList<CharacterDbo> GetCharacters()
    {
        return characters;
    }
}
