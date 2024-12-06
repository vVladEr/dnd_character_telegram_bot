package dnd.bot.maven.eclipse.db.repos;

import org.bson.Document;
import org.bson.codecs.configuration.CodecConfigurationException;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

import dnd.bot.maven.eclipse.db.Models.dbo.CharacterDbo;
import dnd.bot.maven.eclipse.db.Models.dbo.UserDBO;
import dnd.bot.maven.eclipse.db.repos.Interfaces.IFieldUpdatable;

public class MongoUserRepository extends BaseRepo<UserDBO, String> 
	implements IFieldUpdatable<String>
{
	public MongoUserRepository(MongoDatabase db) {
		super(db);
	}

	@Override
	protected final MongoCollection<UserDBO> initMongoCollection(MongoDatabase db) {
		CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(UserDBO.class, CharacterDbo.class).build()));
		return db.getCollection("users", UserDBO.class).withCodecRegistry(pojoCodecRegistry);
	}

	public void AddCharacterToUser(String userId, ObjectId characterId, String charaterName) {
		var characterDbo = new CharacterDbo(characterId, charaterName);
		mongoCollection.updateOne(eq("_id", userId),
				Updates.addToSet("characters", characterDbo));

	}

	public void RemoveCharacterFromUser(String userId, ObjectId characterId) {
		var filter = eq("_id", userId);
		Document update = new Document("$pull",
				new Document("characters",
						new Document("_id", characterId)));
		mongoCollection.updateOne(filter, update);

	}

	@Override
	public void updateField(String id, String fieldName, Object newFieldValue) throws CodecConfigurationException {
	var update = Updates.set(fieldName, newFieldValue);
	var filter = eq("_id", id);
	var options = new UpdateOptions().upsert(false);
	mongoCollection.updateOne(filter, update, options);
    }
}
