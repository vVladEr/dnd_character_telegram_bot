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

import dnd.bot.maven.eclipse.User.Character.Description.Personality.Level.Level;
import dnd.bot.maven.eclipse.db.dbo.LevelDbo;

public class MongoLevelRepository {
    private MongoCollection<LevelDbo> levelCollection;

    public MongoLevelRepository(MongoDatabase db) 
    {
        
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(LevelDbo.class).build()));
        levelCollection = db.getCollection("levels", LevelDbo.class).withCodecRegistry(pojoCodecRegistry);
    
   
    }

    public Level InsertCharacterLevel(Level lv)
    {
        levelCollection.insertOne(new LevelDbo(lv));
        return lv;
    }

    public Level GetCharacterLevel(ObjectId characterId)
    {
        var characterLevelDbo = levelCollection.find(eq("_id", characterId)).first();
        return new Level(characterLevelDbo);
    }

    public void UpdateCharacterLevelField(ObjectId characterId, String fieldName, String fieldNewValue)
    {
        var update = Updates.set(fieldName, fieldNewValue);
        var filter = eq("_id", characterId);
        var options = new UpdateOptions().upsert(false);
        levelCollection.updateOne(filter, update, options);
    }
}
