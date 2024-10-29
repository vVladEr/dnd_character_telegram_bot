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

import dnd.bot.maven.eclipse.db.dbo.AppearenceDbo;

public class MongoAppearenceRepository {
    private MongoCollection<AppearenceDbo> appearanceCollection;

    
    public MongoAppearenceRepository(MongoDatabase db)
    {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(AppearenceDbo.class).build()));
        appearanceCollection = db.getCollection("appearances", AppearenceDbo.class).withCodecRegistry(pojoCodecRegistry);
    
    }

    public AppearenceDbo InsertAppearence(AppearenceDbo ap)
    {
        appearanceCollection.insertOne(ap);
        return ap;
    }

    public AppearenceDbo GetCharacterAppearence(ObjectId characterId)
    {
        var dboAppearence = appearanceCollection.find(eq("_id", characterId)).first();
        return dboAppearence;
    }

    public void UpdateBaseField(ObjectId characterId, String baseFieldName, String newValue)
    {
        var update = Updates.set(baseFieldName, newValue);
        var filter = eq("_id", characterId);
        var options = new UpdateOptions().upsert(false);
        appearanceCollection.updateOne(filter, update, options);
    }
}
