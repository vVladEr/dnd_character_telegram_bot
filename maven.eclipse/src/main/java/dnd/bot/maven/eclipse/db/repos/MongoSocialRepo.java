package dnd.bot.maven.eclipse.db.repos;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dnd.bot.maven.eclipse.db.dbo.SocialDbo;

public class MongoSocialRepo extends BaseRepo<SocialDbo, ObjectId> {

    public MongoSocialRepo(MongoDatabase db)
    {
        super(db);
    }

    @Override
    protected MongoCollection<SocialDbo> InitMongoCollection(MongoDatabase db) {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(SocialDbo.class).build()));
        return db.getCollection("socials", SocialDbo.class).withCodecRegistry(pojoCodecRegistry);
    
    }

}
