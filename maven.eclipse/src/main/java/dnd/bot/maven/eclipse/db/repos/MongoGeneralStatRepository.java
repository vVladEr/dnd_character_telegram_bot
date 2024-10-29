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

import dnd.bot.maven.eclipse.User.Character.Stats.GeneralStat.Skills.Skill;
import dnd.bot.maven.eclipse.User.Character.Stats.GeneralStat.Skills.Knowledge.KnowledgeLevel;
import dnd.bot.maven.eclipse.db.dbo.GeneralStatDBO;
import java.util.ArrayList;

public class MongoGeneralStatRepository {

    private MongoCollection<GeneralStatDBO> statsColletion;

    public MongoGeneralStatRepository(MongoDatabase db)
    {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(GeneralStatDBO.class, Skill.class, KnowledgeLevel.class).build()));
        statsColletion = db.getCollection("stats", GeneralStatDBO.class).withCodecRegistry(pojoCodecRegistry);
    }
    

    public void InstertStats(GeneralStatDBO ...stats)
    {
        for(var stat: stats)
        {
            statsColletion.insertOne(stat);
        }
    }

    public ArrayList<GeneralStatDBO> GetCharacterStats(ObjectId characterId)
    {
        var dboStats = statsColletion.find(eq("_id", characterId));
        var stats = new ArrayList<GeneralStatDBO>();
        for(var dboStat: dboStats)
        {
            stats.add(dboStat);
        }
        return stats;
    }

    public GeneralStatDBO GetCharacterStat(ObjectId characterId, String statName)
    {
        var dboStat = statsColletion.find(and(eq("_id", characterId), eq("statName", statName))).first();
        return dboStat;
    }

    public void UpdateStatField(ObjectId characterId, String statName, String fieldName, String fieldNewValue)
    {
        var update = Updates.set(fieldName, fieldNewValue);
        var filter = and(eq("_id", characterId), eq("statName", statName));
        var options = new UpdateOptions().upsert(false);
        statsColletion.updateOne(filter, update, options);
    }

    public void UpdateSkillField(ObjectId characterId, String statName, String skillName, String fieldName, String fieldNewValue)
    {
        var update = Updates.set("skills." + skillName + "." + fieldName, fieldNewValue);
        var filter = and(eq("_id", characterId), eq("statName", statName));
        var options = new UpdateOptions().upsert(false);
        statsColletion.updateOne(filter, update, options);
    }
}
