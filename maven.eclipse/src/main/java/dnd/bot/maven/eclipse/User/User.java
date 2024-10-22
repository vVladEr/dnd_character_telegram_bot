package dnd.bot.maven.eclipse.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bson.types.ObjectId;

import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.db.dbo.UserDBO;

public class User extends State{

    private ObjectId  id;

    public ArrayList<UUID> characters;
    
    public User(String id) 
    {
    	this.id = new ObjectId(id);
    	characters = new ArrayList<UUID>();
        possibleTransitions = new HashMap<String, State>();
    }
    
    public User(UserDBO userDbo) 
    {
        id = userDbo.GetId();
        characters = userDbo.GetCharacters();
        possibleTransitions = new HashMap<String, State>();
    }

    @Override
    public void render() {
        
    }

    public ObjectId GetId()
    {
        return id;
    }
}
