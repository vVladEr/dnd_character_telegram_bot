package dnd.bot.maven.eclipse.db.repos;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;

import org.bson.codecs.configuration.CodecConfigurationException;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dnd.bot.maven.eclipse.db.Models.dbo.BasicDescriptionDbo;

public class MongoPossesionsRepository  extends BaseRepo<BasicDescriptionDbo, ObjectId>{

    public MongoPossesionsRepository(MongoDatabase db) {
        super(db);
    }

    @Override
    protected MongoCollection<BasicDescriptionDbo> InitMongoCollection(MongoDatabase db) {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(BasicDescriptionDbo.class).build()));
        return db.getCollection("possesions", BasicDescriptionDbo.class).withCodecRegistry(pojoCodecRegistry);
    }

    public ArrayList<BasicDescriptionDbo> GetCharacterPossesions(ObjectId characterId)
    {
        var possesionsDbo = mongoCollection.find(eq("characterId", characterId));
        var possesions = new ArrayList<BasicDescriptionDbo>();
        for(var possesionBdo: possesionsDbo)
        {
            possesions.add(possesionBdo);
        }
        return possesions;
    }

    @Override
    public void UpdateField(ObjectId id, String fieldName, Object newFieldValue) throws CodecConfigurationException
    {
        super.UpdateField(id, fieldName, newFieldValue);
    }
}
