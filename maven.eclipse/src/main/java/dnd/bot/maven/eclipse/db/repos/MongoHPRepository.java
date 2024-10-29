package dnd.bot.maven.eclipse.db.repos;

import static com.mongodb.client.model.Filters.eq;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

import dnd.bot.maven.eclipse.db.dbo.HPDbo;

public class MongoHPRepository {
    private MongoCollection<HPDbo> hpCollection;

    public MongoHPRepository(MongoDatabase db)
    {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(HPDbo.class).build()));
        hpCollection = db.getCollection("hps", HPDbo.class).withCodecRegistry(pojoCodecRegistry);

    }

    public HPDbo insetCharacterHp(HPDbo characterHp)
    {
        hpCollection.insertOne(characterHp);
        return characterHp;
    }

    public HPDbo getCharacterHp(ObjectId characterId)
    {
        var dboHp = hpCollection.find(eq("_id", characterId)).first();
        return dboHp;
    }

    public void updateField(ObjectId characterId, String fieldName, String fieldValue)
    {
        var update = Updates.set(fieldName, fieldValue);
        var filter = eq("_id", characterId);
        var options = new UpdateOptions().upsert(false);
        hpCollection.updateOne(filter, update, options);
    }
}
