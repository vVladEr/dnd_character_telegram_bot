package dnd.bot.maven.eclipse.db.repos;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dnd.bot.maven.eclipse.db.dbo.AppearenceDbo;

public class MongoAppearenceRepository extends BaseRepo<AppearenceDbo, ObjectId>{

    public MongoAppearenceRepository(MongoDatabase db)
    {
        super(db);
    }

    protected MongoCollection<AppearenceDbo> InitMongoCollection(MongoDatabase db)
    {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(AppearenceDbo.class).build()));
        return db.getCollection("appearances", AppearenceDbo.class).withCodecRegistry(pojoCodecRegistry);
    }
}
