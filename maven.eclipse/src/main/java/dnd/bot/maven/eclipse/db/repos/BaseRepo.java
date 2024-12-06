package dnd.bot.maven.eclipse.db.repos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

import static com.mongodb.client.model.Filters.eq;

import org.bson.codecs.configuration.CodecConfigurationException;

public abstract class BaseRepo<T, KeyType> {
    protected MongoCollection<T> mongoCollection;

    public BaseRepo(MongoDatabase db) {
        mongoCollection = initMongoCollection(db);
    }

    protected abstract MongoCollection<T> initMongoCollection(MongoDatabase db);

    public T insertDocument(T item) {
        mongoCollection.insertOne(item);
        return item;
    }

    public T getDocumentByKey(KeyType id) {
        return mongoCollection.find(eq("_id", id)).first();
    }

    public void updateField(KeyType id, String fieldName, Object newFieldValue) throws CodecConfigurationException {
        var update = Updates.set(fieldName, newFieldValue);
        var filter = eq("_id", id);
        var options = new UpdateOptions().upsert(false);
        mongoCollection.updateOne(filter, update, options);
    }

    protected void deleteDocument(KeyType id) {
        mongoCollection.findOneAndDelete(eq("_id", id));
    }
}
