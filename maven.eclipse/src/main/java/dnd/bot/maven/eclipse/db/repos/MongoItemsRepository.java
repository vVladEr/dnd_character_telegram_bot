package dnd.bot.maven.eclipse.db.repos;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dnd.bot.maven.eclipse.User.Character.Inventory.Item.Item;
import dnd.bot.maven.eclipse.db.dbo.ItemDBO;

import java.util.ArrayList;

public class MongoItemsRepository  extends BaseRepo<ItemDBO, ObjectId>{

    public MongoItemsRepository(MongoDatabase db)
    {
        super(db);
    }

    protected MongoCollection<ItemDBO> InitMongoCollection(MongoDatabase db)
    {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(ItemDBO.class).build()));
        return db.getCollection("stats", ItemDBO.class).withCodecRegistry(pojoCodecRegistry);
    }

    public ArrayList<Item> getCharactersItems(ObjectId characterId)
    {
        var dboItems = mongoCollection.find(eq("characterId", characterId));
        var items = new ArrayList<Item>();
        for(var dboItem: dboItems)
        {
            items.add(new Item(dboItem));
        }
        return items;
    }
}