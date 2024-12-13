package dnd.bot.maven.eclipse.db.repos;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

import dnd.bot.maven.eclipse.db.Models.CompositeKeys.StatCompositeKey;
import dnd.bot.maven.eclipse.db.Models.dbo.GeneralStatDBO;
import dnd.bot.maven.eclipse.db.Models.dbo.SkillDbo;
import dnd.bot.maven.eclipse.db.repos.Interfaces.InnerFieldUpdatable;

import java.util.ArrayList;

public class MongoGeneralStatRepository extends BaseRepo<GeneralStatDBO, StatCompositeKey>
        implements InnerFieldUpdatable<StatCompositeKey> {

    public MongoGeneralStatRepository(MongoDatabase db) {
        super(db);
    }

    @Override
    protected final MongoCollection<GeneralStatDBO> initMongoCollection(MongoDatabase db) {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(GeneralStatDBO.class, SkillDbo.class, KnowledgeLevel.class).build()));
        return db.getCollection("stats", GeneralStatDBO.class).withCodecRegistry(pojoCodecRegistry);

    }

    @Override
    public GeneralStatDBO getDocumentByKey(StatCompositeKey compositeKey) {
        var dboStat = mongoCollection.find(and(
                eq("characterId", compositeKey.characterId),
                eq("statName", compositeKey.statName))).first();
        return dboStat;
    }

    public void instertStats(GeneralStatDBO... stats) {
        for (var stat : stats) {
            mongoCollection.insertOne(stat);
        }
    }

    public ArrayList<GeneralStatDBO> getCharacterStats(ObjectId characterId) {
        var dboStats = mongoCollection.find(eq("characterId", characterId));
        var stats = new ArrayList<GeneralStatDBO>();
        for (var dboStat : dboStats) {
            stats.add(dboStat);
        }
        return stats;
    }

    public void updateField(StatCompositeKey compositeKey, String fieldName, Object fieldNewValue) {
        var update = Updates.set(fieldName, fieldNewValue);
        var filter = and(eq("characterId", compositeKey.characterId),
                eq("statName", compositeKey.statName));
        var options = new UpdateOptions().upsert(false);
        mongoCollection.updateOne(filter, update, options);
    }

    public void updateInnerField(StatCompositeKey compositeKey, String skillName, String fieldName,
            Object fieldNewValue) {
        var update = Updates.set("skills." + skillName + "." + fieldName, fieldNewValue);
        var filter = and(eq("characterId", compositeKey.characterId), eq("statName", compositeKey.statName));
        var options = new UpdateOptions().upsert(false);
        mongoCollection.updateOne(filter, update, options);
    }
}
