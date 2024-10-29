package dnd.bot.maven.eclipse.db.repos;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dnd.bot.maven.eclipse.db.dbo.HPDbo;

public class MongoHPRepository  extends BaseRepo<HPDbo, ObjectId> {

    public MongoHPRepository(MongoDatabase db)
    {
        super(db);
    }

    protected MongoCollection<HPDbo> InitMongoCollection(MongoDatabase db)
    {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(HPDbo.class).build()));
        return db.getCollection("hps", HPDbo.class).withCodecRegistry(pojoCodecRegistry);
    }
}
