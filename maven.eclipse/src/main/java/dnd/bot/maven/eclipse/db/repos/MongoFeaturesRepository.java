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

import dnd.bot.maven.eclipse.db.dbo.BasicDescriptionDbo;

public class MongoFeaturesRepository  extends BaseRepo<BasicDescriptionDbo, ObjectId>{

    public MongoFeaturesRepository(MongoDatabase db) {
        super(db);
    }

    @Override
    protected MongoCollection<BasicDescriptionDbo> InitMongoCollection(MongoDatabase db) {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(BasicDescriptionDbo.class).build()));
        return db.getCollection("features", BasicDescriptionDbo.class).withCodecRegistry(pojoCodecRegistry);
    }

    public ArrayList<BasicDescriptionDbo> GetCharacterFeatures(ObjectId characterId)
    {
        var feacturesDbo = mongoCollection.find(eq("_id", characterId));
        var features = new ArrayList<BasicDescriptionDbo>();
        for(var dboStat: feacturesDbo)
        {
            features.add(dboStat);
        }
        return features;
    }

}
