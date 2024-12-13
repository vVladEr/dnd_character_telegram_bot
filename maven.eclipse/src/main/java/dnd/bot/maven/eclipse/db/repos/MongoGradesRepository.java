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

import dnd.bot.maven.eclipse.db.Models.CompositeKeys.GradeCompositeKey;
import dnd.bot.maven.eclipse.db.Models.dbo.BasicDescriptionDbo;
import dnd.bot.maven.eclipse.db.Models.dbo.GradeDBo;
import dnd.bot.maven.eclipse.db.repos.Interfaces.InnerFieldUpdatable;

import java.util.ArrayList;

public class MongoGradesRepository extends BaseRepo<GradeDBo, GradeCompositeKey>
        implements InnerFieldUpdatable<GradeCompositeKey> {

    public MongoGradesRepository(MongoDatabase db) {
        super(db);
    }

    @Override
    protected final MongoCollection<GradeDBo> initMongoCollection(MongoDatabase db) {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(GradeDBo.class, BasicDescriptionDbo.class).build()));
        return db.getCollection("spells", GradeDBo.class).withCodecRegistry(pojoCodecRegistry);

    }

    @Override
    public GradeDBo getDocumentByKey(GradeCompositeKey compositeKey) {
        var gradeDbo = mongoCollection.find(and(
                eq("characterId", compositeKey.characterId),
                eq("grade", compositeKey.grade))).first();
        return gradeDbo;

    }

    public ArrayList<GradeDBo> getCharacterGrades(ObjectId characterId) {
        var dboGrades = mongoCollection.find(eq("characterId", characterId));
        var grades = new ArrayList<GradeDBo>();
        for (var dboGrade : dboGrades) {
            grades.add(dboGrade);
        }
        return grades;
    }

    @Override
    public void updateField(GradeCompositeKey compositeKey, String fieldName, Object newFieldValue) {
        var update = Updates.set(fieldName, newFieldValue);
        var filter = and(eq("characterId", compositeKey.characterId), eq("grade", compositeKey.grade));
        var options = new UpdateOptions().upsert(false);
        mongoCollection.updateOne(filter, update, options);
    }

    public void updateInnerField(GradeCompositeKey compositeKey, String spellName, String fieldName, Object newValue) {
        if (fieldName.equals("name")) {
            var oldSpell = getDocumentByKey(compositeKey).spells.get(spellName);
            removeSpell(compositeKey, spellName);
            addSpell(compositeKey, (String) newValue, oldSpell.description);
            return;
        }
        var update = Updates.set("spells." + spellName + "." + fieldName, newValue);
        var filter = and(eq("characterId", compositeKey.characterId), eq("grade", compositeKey.grade));
        var options = new UpdateOptions().upsert(false);
        mongoCollection.updateOne(filter, update, options);
    }

    public void addSpell(GradeCompositeKey compositeKey, String spellName, String spellDesc) {
        var filter = and(eq("characterId", compositeKey.characterId), eq("grade", compositeKey.grade));
        mongoCollection.updateOne(filter, Updates.set("spells." + spellName,
                new BasicDescriptionDbo(
                        compositeKey.characterId,
                        spellName,
                        spellDesc)));
    }

    private void removeSpell(GradeCompositeKey compositeKey, String spellName) {
        var filter = and(eq("characterId", compositeKey.characterId), eq("grade", compositeKey.grade));
        mongoCollection.updateOne(filter, Updates.unset("spells." + spellName));
    }
}
