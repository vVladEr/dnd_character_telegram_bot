package dnd.bot.maven.eclipse.db.repos;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dnd.bot.maven.eclipse.db.Models.dbo.BasicDescriptionDbo;
import dnd.bot.maven.eclipse.db.repos.Interfaces.DocumentDeletable;

public class MongoPossesionsRepository extends BaseRepo<BasicDescriptionDbo, ObjectId>
implements DocumentDeletable<ObjectId>{

    public MongoPossesionsRepository(MongoDatabase db) {
        super(db);
    }

    @Override
    protected final MongoCollection<BasicDescriptionDbo> initMongoCollection(MongoDatabase db) {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(BasicDescriptionDbo.class).build()));
        return db.getCollection("possesions", BasicDescriptionDbo.class).withCodecRegistry(pojoCodecRegistry);
    }

    public ArrayList<BasicDescriptionDbo> getCharacterPossesions(ObjectId characterId) {
        var possesionsDbo = mongoCollection.find(eq("characterId", characterId));
        var possesions = new ArrayList<BasicDescriptionDbo>();
        for (var possesionDbo : possesionsDbo) {
            possesions.add(possesionDbo);
        }
        return possesions;
    }

    public void deleteDocument(ObjectId id)
    {
        super.deleteDocument(id);
    }
}
