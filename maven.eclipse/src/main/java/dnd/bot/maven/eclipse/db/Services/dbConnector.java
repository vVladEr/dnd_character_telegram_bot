package dnd.bot.maven.eclipse.db.Services;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import org.bson.UuidRepresentation;



public class dbConnector {
	
	public MongoDatabase DB;
	
	public dbConnector(String dataBaseName) 
	{
		DB = establishConnection(dataBaseName);
	}
	
	public dbConnector() 
	{
		DB = establishConnection("java-dnd-telegram-bot");
	}
	
    private static MongoDatabase establishConnection(String dataBaseName)
    {
 
        try {
        	ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
        	MongoClient mongoClient
                =  MongoClients.create(MongoClientSettings.builder()
                        .uuidRepresentation(UuidRepresentation.STANDARD)
                        .applyConnectionString(connectionString)
                        .build());
 
            System.out.println(
                "Successfully Connected"
                + " to the database");
            
            return mongoClient.getDatabase(dataBaseName);

        }
        catch (Exception e) {
            System.out.println(
                "Connection establishment failed");
            System.out.println(e);
        }
		return null;
    }
}