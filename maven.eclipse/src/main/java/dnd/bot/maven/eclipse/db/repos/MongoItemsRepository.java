package dnd.bot.maven.eclipse.db.repos;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dnd.bot.maven.eclipse.db.Models.dbo.ItemDBO;
import dnd.bot.maven.eclipse.db.repos.Interfaces.DocumentDeletable;

import java.util.ArrayList;

public class MongoItemsRepository extends BaseRepo<ItemDBO, ObjectId>
    implements DocumentDeletable<ObjectId>{

    public MongoItemsRepository(MongoDatabase db) {
        super(db);
    }

    @Override
    protected final MongoCollection<ItemDBO> initMongoCollection(MongoDatabase db) {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(ItemDBO.class).build()));
        return db.getCollection("items", ItemDBO.class).withCodecRegistry(pojoCodecRegistry);
    }

    public ArrayList<ItemDBO> getCharactersItems(ObjectId characterId) {
        var itemsDbo = mongoCollection.find(eq("characterId", characterId));
        var items = new ArrayList<ItemDBO>();
        for (var itemDbo : itemsDbo) {
            items.add(itemDbo);
        }
        return items;
    }

    public void deleteDocument(ObjectId id)
    {
        super.deleteDocument(id);
    }
}
