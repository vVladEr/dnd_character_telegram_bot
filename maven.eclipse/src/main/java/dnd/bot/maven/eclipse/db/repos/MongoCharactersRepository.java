package dnd.bot.maven.eclipse.db.repos;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dnd.bot.maven.eclipse.db.Models.dbo.CharacterDbo;
import dnd.bot.maven.eclipse.db.repos.Interfaces.IFieldUpdatable;

public class MongoCharactersRepository extends BaseRepo<CharacterDbo, ObjectId>
		implements IFieldUpdatable<ObjectId> {
	public MongoCharactersRepository(MongoDatabase db) {
		super(db);
	}

	@Override
	protected final MongoCollection<CharacterDbo> initMongoCollection(MongoDatabase db) {
		CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(CharacterDbo.class).build()));
		return db.getCollection("characters", CharacterDbo.class).withCodecRegistry(pojoCodecRegistry);
	}

	public ArrayList<CharacterDbo> getUserCharacters(String userId)
	{
		var charactersDbo = mongoCollection.find(eq("userId", userId));
        var characters = new ArrayList<CharacterDbo>();
        for (var characterDbo : charactersDbo) {
            characters.add(characterDbo);
        }
        return characters;
	}

	@Override
	public void deleteDocument(ObjectId id) {
        mongoCollection.findOneAndDelete(eq("_id", id));
    }
}
