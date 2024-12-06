package dnd.bot.maven.eclipse.db.repos;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dnd.bot.maven.eclipse.db.Models.dbo.SocialDbo;
import dnd.bot.maven.eclipse.db.repos.Interfaces.IFieldUpdatable;

public class MongoSocialRepository extends BaseRepo<SocialDbo, ObjectId>
        implements IFieldUpdatable<ObjectId> {

    public MongoSocialRepository(MongoDatabase db) {
        super(db);
    }

    @Override
    protected final MongoCollection<SocialDbo> initMongoCollection(MongoDatabase db) {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(SocialDbo.class).build()));
        return db.getCollection("socials", SocialDbo.class).withCodecRegistry(pojoCodecRegistry);

    }
}
