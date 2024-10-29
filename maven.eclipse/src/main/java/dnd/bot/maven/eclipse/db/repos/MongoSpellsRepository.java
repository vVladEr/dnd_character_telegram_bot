package dnd.bot.maven.eclipse.db.repos;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

import dnd.bot.maven.eclipse.db.dbo.SpellDBo;
import java.util.ArrayList;

public class MongoSpellsRepository {
    private MongoCollection<SpellDBo> spellsCollection;

    public MongoSpellsRepository(MongoDatabase db)
    {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(SpellDBo.class).build()));
        spellsCollection =  db.getCollection("spells", SpellDBo.class).withCodecRegistry(pojoCodecRegistry);
    }



    public SpellDBo InsertSpell(SpellDBo item)
    {
        spellsCollection.insertOne(item);
        return item;
    }

    public ArrayList<SpellDBo> GetCharacterSpells(ObjectId id)
    {
        var dboSpells = spellsCollection.find(eq("_id", id));
        var spells = new ArrayList<SpellDBo>();
        for(var dboStat: dboSpells)
        {
            spells.add(dboStat);
        }
        return spells;
    }

    public ArrayList<SpellDBo> GetCharacterSpells(ObjectId id, int grade)
    {
        var dboSpells = spellsCollection.find(and(eq("_id", id), eq("grade", grade)));
        var spells = new ArrayList<SpellDBo>();
        for(var dboStat: dboSpells)
        {
            spells.add(dboStat);
        }
        return spells;
    }

    public void UpdateField(ObjectId id, int grade, String fieldName, String newFieldValue)
    {
        var update = Updates.set(fieldName, newFieldValue);
        var filter = and(eq("_id", id), eq("grade", grade));
        var options = new UpdateOptions().upsert(false);
        spellsCollection.updateOne(filter, update, options);
    }

    public void addSpell(ObjectId id, int grade, String spellName, String spellDesc)
    {
        var filter = and(eq("_id", id), eq("grade", grade));
        spellsCollection.updateOne(filter, Updates.push("spells", new BasicDBObject(spellName, spellDesc)));
    }
}
