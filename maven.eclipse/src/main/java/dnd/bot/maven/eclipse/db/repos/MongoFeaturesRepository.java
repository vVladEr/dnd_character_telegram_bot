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
import dnd.bot.maven.eclipse.db.repos.Interfaces.IFieldUpdatable;

public class MongoFeaturesRepository  extends BaseRepo<BasicDescriptionDbo, ObjectId>
    implements IFieldUpdatable<ObjectId>
{

    public MongoFeaturesRepository(MongoDatabase db) {
        super(db);
    }

    @Override
    protected final MongoCollection<BasicDescriptionDbo> InitMongoCollection(MongoDatabase db) {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(BasicDescriptionDbo.class).build()));
        return db.getCollection("features", BasicDescriptionDbo.class).withCodecRegistry(pojoCodecRegistry);
    }

    public ArrayList<BasicDescriptionDbo> GetCharacterFeatures(ObjectId characterId)
    {
        var feacturesDbo = mongoCollection.find(eq("characterId", characterId));
        var features = new ArrayList<BasicDescriptionDbo>();
        for(var dboStat: feacturesDbo)
        {
            features.add(dboStat);
        }
        return features;
    }
    
    @Override
    public void UpdateField(ObjectId id, String fieldName, Object newFieldValue) throws CodecConfigurationException
    {
        super.UpdateField(id, fieldName, newFieldValue);
    }
}
