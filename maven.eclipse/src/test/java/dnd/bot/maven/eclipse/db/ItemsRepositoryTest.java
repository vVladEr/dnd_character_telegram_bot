package dnd.bot.maven.eclipse.db;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.db.Models.dbo.ItemDBO;
import dnd.bot.maven.eclipse.db.Services.dbConnector;
import dnd.bot.maven.eclipse.db.repos.MongoItemsRepository;

public class ItemsRepositoryTest {
    private static MongoItemsRepository rep;
	private static dbConnector conn;
	
	@BeforeAll
	public static void SetUp() 
	{
        conn = new dbConnector("test-java-dnd-bot");
        rep = new MongoItemsRepository(conn.getDb());
	}
	
	@AfterAll
	public static void TearDown() 
	{
		conn.getDb().drop();
	}
	
	@Test
	public void GetCharactersFeatures() 
	{
		var characterId = new ObjectId();
		var item1 = new ItemDBO(characterId,"testItem1", "testDesc1", 3);
        var item2 = new ItemDBO(characterId, "testItem2", "testDesc2");
        var anotherCharacterFeature = new ItemDBO(new ObjectId(), "anotherFeature", "feature for another character", 5);
		rep.InsertDocument(item1);
        rep.InsertDocument(item2);
        rep.InsertDocument(anotherCharacterFeature);
        
        var features = rep.GetCharactersItems(characterId);
        assertTrue(features != null);
        assertTrue(features.size() == 2);
        assertTrue(features.get(0).name.equals("testItem1"));
        assertTrue(features.get(0).description.equals("testDesc1"));
        assertTrue(features.get(0).amount == 3);

        assertTrue(features.get(1).name.equals("testItem2"));
        assertTrue(features.get(1).description.equals("testDesc2"));
        assertTrue(features.get(1).amount == 1);
	}
}
