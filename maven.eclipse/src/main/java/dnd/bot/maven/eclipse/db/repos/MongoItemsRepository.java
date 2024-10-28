package dnd.bot.maven.eclipse.db.repos;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

import dnd.bot.maven.eclipse.User.Character.Inventory.Item.Item;
import dnd.bot.maven.eclipse.db.dbo.ItemDBO;

import java.util.ArrayList;

public class MongoItemsRepository {
    private MongoCollection<ItemDBO> itemsCollection;

    public MongoItemsRepository(MongoDatabase db)
    {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(PojoCodecProvider.builder()
						.register(ItemDBO.class).build()));
        itemsCollection = db.getCollection("stats", ItemDBO.class).withCodecRegistry(pojoCodecRegistry);

    }

    public Item insertItem(Item item)
    {
        itemsCollection.insertOne(new ItemDBO(item));
        return item;
    }

    public ArrayList<Item> getCharactersItems(ObjectId characterId)
    {
        var dboItems = itemsCollection.find(eq("characterId", characterId));
        var items = new ArrayList<Item>();
        for(var dboItem: dboItems)
        {
            items.add(new Item(dboItem));
        }
        return items;
    }

    public void UpdateItemField(ObjectId itemId, String fieldName, String fieldNewValue)
    {
        var update = Updates.set(fieldName, fieldNewValue);
        var filter = eq("_id", itemId);
        var options = new UpdateOptions().upsert(false);
        itemsCollection.updateOne(filter, update, options);
    }
}
