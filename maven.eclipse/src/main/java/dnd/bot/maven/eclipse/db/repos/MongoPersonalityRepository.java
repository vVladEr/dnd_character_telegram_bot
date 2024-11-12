package dnd.bot.maven.eclipse.db.repos;

import org.bson.codecs.configuration.CodecConfigurationException;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dnd.bot.maven.eclipse.db.Models.dbo.PersonalityDbo;

public class MongoPersonalityRepository extends BaseRepo<PersonalityDbo, ObjectId>{

    public MongoPersonalityRepository(MongoDatabase db) {
        super(db);
    }

    @Override
    protected MongoCollection<PersonalityDbo> InitMongoCollection(MongoDatabase db) {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(PersonalityDbo.class).build()));
        return db.getCollection("personalities", PersonalityDbo.class).withCodecRegistry(pojoCodecRegistry);
    }

    @Override
    public void UpdateField(ObjectId id, String fieldName, Object newFieldValue) throws CodecConfigurationException
    {
        super.UpdateField(id, fieldName, newFieldValue);
    }
}
