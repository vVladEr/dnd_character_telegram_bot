package dnd.bot.maven.eclipse.db.repos;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dnd.bot.maven.eclipse.db.Models.dbo.HPDbo;
import dnd.bot.maven.eclipse.db.repos.Interfaces.IFieldUpdatable;

public class MongoHPRepository extends BaseRepo<HPDbo, ObjectId>
        implements IFieldUpdatable<ObjectId> {

    public MongoHPRepository(MongoDatabase db) {
        super(db);
    }

    @Override
    protected final MongoCollection<HPDbo> initMongoCollection(MongoDatabase db) {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(HPDbo.class).build()));
        return db.getCollection("hps", HPDbo.class).withCodecRegistry(pojoCodecRegistry);
    }
}
