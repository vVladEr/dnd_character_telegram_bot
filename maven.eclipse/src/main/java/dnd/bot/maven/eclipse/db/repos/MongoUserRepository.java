package dnd.bot.maven.eclipse.db.repos;

import java.util.UUID;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;

import dnd.bot.maven.eclipse.User.User;
import dnd.bot.maven.eclipse.db.dbo.UserDBO;


public class MongoUserRepository {

	private MongoCollection<UserDBO> userCollection;
	
	public MongoUserRepository(MongoDatabase db) 
	{
		CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(UserDBO.class).build()));
        userCollection = db.getCollection("users", UserDBO.class).withCodecRegistry(pojoCodecRegistry);
	}
	
	public User InsertUser(User user) 
	{
		var userDbo = new UserDBO(user);
		userCollection.insertOne(userDbo);
		return user;
	}
	
	public void AddCharacterToUser(String userId, UUID characterId) 
	{
		userCollection.updateOne(eq("_id", new ObjectId(userId)), Updates.addToSet("characters", characterId));
		
	}
	
	public User GetUserById(String userId) 
	{
		return new User(userCollection.find(eq("_id", new ObjectId(userId))).first());
	}

}
