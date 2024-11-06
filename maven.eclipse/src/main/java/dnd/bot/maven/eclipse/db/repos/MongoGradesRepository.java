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

import dnd.bot.maven.eclipse.db.dbo.GradeDBo;
import java.util.ArrayList;

public class MongoGradesRepository {
    private MongoCollection<GradeDBo> spellsCollection;

    public MongoGradesRepository(MongoDatabase db)
    {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(GradeDBo.class).build()));
        spellsCollection =  db.getCollection("spells", GradeDBo.class).withCodecRegistry(pojoCodecRegistry);
    }



    public GradeDBo InsertGrade(GradeDBo grade)
    {
        spellsCollection.insertOne(grade);
        return grade;
    }

    public ArrayList<GradeDBo> GetCharacterGrades(ObjectId characterId)
    {
        var dboGrades = spellsCollection.find(eq("characterId", characterId));
        var grades = new ArrayList<GradeDBo>();
        for(var dboGrade: dboGrades)
        {
            grades.add(dboGrade);
        }
        return grades;
    }

    public GradeDBo GetCharacterGrade(ObjectId characterId, int grade)
    {
        var gradeDbo = spellsCollection.find(and(eq("characterId", characterId), eq("grade", grade))).first();
        return gradeDbo;
    }

    public void UpdateField(ObjectId characterId, int grade, String fieldName, Object newFieldValue)
    {
        var update = Updates.set(fieldName, newFieldValue);
        var filter = and(eq("characterId", characterId), eq("grade", grade));
        var options = new UpdateOptions().upsert(false);
        spellsCollection.updateOne(filter, update, options);
    }

    public void UpdateSpellDesc(ObjectId characterId, int grade, String spellName, Object newDesc)
    {
        var update = Updates.set("spells." + spellName, newDesc);
        var filter = and(eq("characterId", characterId), eq("grade", grade));
        var options = new UpdateOptions().upsert(false);
        spellsCollection.updateOne(filter, update, options);
    }

    public void AddSpell(ObjectId characterId, int grade, String spellName, String spellDesc)
    {
        var filter = and(eq("characterId", characterId), eq("grade", grade));
        spellsCollection.updateOne(filter, Updates.set("spells." + spellName, spellDesc));
    }
}
