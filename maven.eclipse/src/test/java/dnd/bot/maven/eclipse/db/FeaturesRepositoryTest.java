package dnd.bot.maven.eclipse.db;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.db.Models.dbo.BasicDescriptionDbo;
import dnd.bot.maven.eclipse.db.Services.dbConnector;
import dnd.bot.maven.eclipse.db.repos.MongoFeaturesRepository;

public class FeaturesRepositoryTest {
    private static MongoFeaturesRepository rep;
	private static dbConnector conn;
	
	@BeforeAll
	public static void SetUp() 
	{
        conn = new dbConnector("test-java-dnd-bot");
        rep = new MongoFeaturesRepository(conn.getDb());
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
		var feature1 = new BasicDescriptionDbo(characterId, "test1", "testDesc1");
        var feature2 = new BasicDescriptionDbo(characterId, "test2", "testDesc2");
        var anotherCharacterFeature = new BasicDescriptionDbo(new ObjectId(), "anotherFeature", "feature for another character");
		rep.InsertDocument(feature1);
        rep.InsertDocument(feature2);
        rep.InsertDocument(anotherCharacterFeature);
        
        var features = rep.GetCharacterFeatures(characterId);
        assertTrue(features != null);
        assertTrue(features.size() == 2);
        assertTrue(features.get(0).name.equals("test1"));
        assertTrue(features.get(0).description.equals("testDesc1"));
        assertTrue(features.get(1).name.equals("test2"));
        assertTrue(features.get(1).description.equals("testDesc2"));
	}
}
