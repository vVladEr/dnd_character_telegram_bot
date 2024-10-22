package dnd.bot.maven.eclipse.User;

import java.util.ArrayList;
import java.util.UUID;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class User {
	@BsonProperty("_id")
    public ObjectId  id;
    public ArrayList<UUID> characters;
    
    public User(String id) 
    {
    	this.id = new ObjectId(id);
    	characters = new ArrayList<UUID>();
    }
    
    public User() 
    {
    }

    public User() {
        possibleTransitions = new HashMap<String, State>();
    }

    @Override
    public void render() {
        
    }
}
