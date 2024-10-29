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
	
	public UserDBO InsertUser(UserDBO user) 
	{
		userCollection.insertOne(user);
		return user;
	}
	
	public void AddCharacterToUser(String userId, ObjectId characterId) 
	{
		userCollection.updateOne(eq("_id", userId), Updates.addToSet("characters", characterId));
		
	}
	
	public UserDBO GetUserById(String userId) 
	{
		return userCollection.find(eq("_id", userId)).first();
	}

}
