package dnd.bot.maven.eclipse.db.repos;


import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dnd.bot.maven.eclipse.db.dbo.LevelDbo;

public class MongoLevelRepository extends BaseRepo<LevelDbo, ObjectId> {

    public MongoLevelRepository(MongoDatabase db) 
    {
        super(db);
    }

    protected MongoCollection<LevelDbo> InitMongoCollection(MongoDatabase db)
    {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(LevelDbo.class).build()));
        return db.getCollection("levels", LevelDbo.class).withCodecRegistry(pojoCodecRegistry);
    }
}
