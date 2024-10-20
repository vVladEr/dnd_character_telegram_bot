package dnd.bot.maven.eclipse.db;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dnd.bot.maven.eclipse.Commands.InfoCommand;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;



public class dbConnector {
    public static void establishConnections()
    {
 
        try {
        	MongoClient mongoClient
                =  MongoClients.create("mongodb://localhost:27017");
 
            System.out.println(
                "Successfully Connected"
                + " to the database");
            
            MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
            CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
            MongoCollection<TestCustomer> employeePojoCollection = mongoDatabase.getCollection("employees", TestCustomer.class).withCodecRegistry(pojoCodecRegistry);
            var testCustomer = new TestCustomer();
            testCustomer.firstName = "Emer";
            testCustomer.lastName = "Mullen";
            testCustomer.email = "testemail";
            testCustomer.phone = "phone number";
            employeePojoCollection.insertOne(testCustomer);
            
            var doc = employeePojoCollection.find(new Document("firstName", "Emer")).first();

 
            mongoClient.listDatabaseNames().forEach(s->System.out.println(s));
            mongoClient.close();
        }
        catch (Exception e) {
            System.out.println(
                "Connection establishment failed");
            System.out.println(e);
        }
    }
}