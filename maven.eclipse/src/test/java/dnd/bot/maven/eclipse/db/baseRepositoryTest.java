package dnd.bot.maven.eclipse.db;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.db.dbo.LevelDbo;
import dnd.bot.maven.eclipse.db.repos.MongoLevelRepository;

public class baseRepositoryTest {
private static MongoLevelRepository rep;
	private static dbConnector conn;
	
	@BeforeAll
	public static void SetUp() 
	{
        conn = new dbConnector("test-java-dnd-bot");
        rep = new MongoLevelRepository(conn.DB);
	}
	
	@AfterAll
	public static void TearDown() 
	{
		conn.DB.drop();
	}
	
	@Test
	public void AddDocTest() 
	{
		var characterId = new ObjectId();
		var lv = new LevelDbo(characterId);
		rep.InsertDocument(lv);
        var dbLv = rep.GetDocumentById(characterId);
        assertTrue(dbLv != null);
        assertTrue(dbLv.characterId.equals(lv.characterId));
	}

	@Test
	public void UpdateFieldTest() 
	{
		var characterId = new ObjectId();
		var lv = new LevelDbo(characterId);
		rep.InsertDocument(lv);
        rep.UpdateField(characterId, "currentExp", 5);
        var dbLv = rep.GetDocumentById(characterId);
        assertTrue(dbLv != null);
        assertTrue(dbLv.currentExp == 5);
	}

    @Test void DeleteDocTest()
    {
        var characterId = new ObjectId();
		var lv = new LevelDbo(characterId);
		rep.InsertDocument(lv);
        var dbLv = rep.GetDocumentById(characterId);
        assertTrue(dbLv != null);
        assertTrue(dbLv.characterId.equals(lv.characterId));
        rep.DeleteDocument(characterId);
        dbLv = rep.GetDocumentById(characterId); 
        assertTrue(dbLv == null);
    }
}
