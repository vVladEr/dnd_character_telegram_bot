package dnd.bot.maven.eclipse.db.repos;

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


public class MongoUserRepository extends BaseRepo<UserDBO, String>{
	
	public MongoUserRepository(MongoDatabase db) 
	{
		super(db);
	}

	@Override
	public MongoCollection<UserDBO> InitMongoCollection(MongoDatabase db)
	{
		CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(UserDBO.class).build()));
        return db.getCollection("users", UserDBO.class).withCodecRegistry(pojoCodecRegistry);
	}
	
	public void AddCharacterToUser(String userId, ObjectId characterId) 
	{
		mongoCollection.updateOne(eq("_id", userId), Updates.addToSet("characters", characterId));
		
	}
}
