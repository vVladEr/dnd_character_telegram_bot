package dnd.bot.maven.eclipse.db.repos;


import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dnd.bot.maven.eclipse.db.Models.dbo.LevelDbo;
import dnd.bot.maven.eclipse.db.repos.Interfaces.IFieldUpdatable;

public class MongoLevelRepository extends BaseRepo<LevelDbo, ObjectId> 
    implements IFieldUpdatable<ObjectId>
{

    public MongoLevelRepository(MongoDatabase db) 
    {
        super(db);
    }
    
    @Override
    protected final MongoCollection<LevelDbo> InitMongoCollection(MongoDatabase db)
    {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(LevelDbo.class).build()));
        return db.getCollection("levels", LevelDbo.class).withCodecRegistry(pojoCodecRegistry);
    }

    @Override
    public void UpdateField(ObjectId characterId, String fieldName, Object newFieldValue){
        super.UpdateField(characterId, fieldName, newFieldValue);
    }

    @Override
    public void DeleteDocument(ObjectId characterId){
        super.DeleteDocument(characterId);
    }
}
