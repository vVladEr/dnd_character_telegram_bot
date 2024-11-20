package dnd.bot.maven.eclipse.db.repos;

import org.bson.codecs.configuration.CodecConfigurationException;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dnd.bot.maven.eclipse.db.Models.dbo.AppearenceDbo;
import dnd.bot.maven.eclipse.db.repos.Interfaces.IFieldUpdatable;

public class MongoAppearenceRepository extends BaseRepo<AppearenceDbo, ObjectId>
    implements IFieldUpdatable<ObjectId>
 {

    public MongoAppearenceRepository(MongoDatabase db)
    {
        super(db);
    }

    @Override
    protected final MongoCollection<AppearenceDbo> InitMongoCollection(MongoDatabase db)
    {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(AppearenceDbo.class).build()));
        return db.getCollection("appearances", AppearenceDbo.class).withCodecRegistry(pojoCodecRegistry);
    }

    @Override
    public void UpdateField(ObjectId id, String fieldName, Object newFieldValue) throws CodecConfigurationException
    {
        super.UpdateField(id, fieldName, newFieldValue);
    }
}
