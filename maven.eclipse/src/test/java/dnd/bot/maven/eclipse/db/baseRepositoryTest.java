package dnd.bot.maven.eclipse.db;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.db.Models.dbo.LevelDbo;
import dnd.bot.maven.eclipse.db.Services.dbConnector;
import dnd.bot.maven.eclipse.db.repos.MongoLevelRepository;

public class BaseRepositoryTest {
	private static MongoLevelRepository rep;
	private static dbConnector conn;

	@BeforeAll
	public static void setUp() {
		conn = new dbConnector("test-java-dnd-bot");
		rep = new MongoLevelRepository(conn.getDb());
	}

	@AfterAll
	public static void tearDown() {
		conn.getDb().drop();
	}

	@Test
	public void addDocTest() {
		var characterId = new ObjectId();
		var lv = new LevelDbo(characterId);
		rep.insertDocument(lv);
		var dbLv = rep.getDocumentByKey(characterId);
		assertTrue(dbLv != null);
		assertTrue(dbLv.characterId.equals(lv.characterId));
	}

	@Test
	public void updateFieldTest() {
		var characterId = new ObjectId();
		var lv = new LevelDbo(characterId);
		rep.insertDocument(lv);
		rep.updateField(characterId, "currentExp", 5);
		var dbLv = rep.getDocumentByKey(characterId);
		assertTrue(dbLv != null);
		assertTrue(dbLv.currentExp == 5);
	}

	@Test
	public void updateUnexpectedField_ShouldNotUpdategAnythingTest() {
		var characterId = new ObjectId();
		var lv = new LevelDbo(characterId);
		rep.insertDocument(lv);
		rep.updateField(characterId, UUID.randomUUID().toString(), 5);
		var dbLv = rep.getDocumentByKey(characterId);
		assertTrue(dbLv != null);
		assertTrue(dbLv.currentLevel == lv.currentLevel);
		assertTrue(dbLv.currentExp == lv.currentExp);
		assertTrue(dbLv.necessaryExp == lv.necessaryExp);
	}

	@Test
	public void deleteDocTest() {
		var characterId = new ObjectId();
		var lv = new LevelDbo(characterId);
		rep.insertDocument(lv);
		var dbLv = rep.getDocumentByKey(characterId);
		assertTrue(dbLv != null);
		assertTrue(dbLv.characterId.equals(lv.characterId));
		rep.deleteDocument(characterId);
		dbLv = rep.getDocumentByKey(characterId);
		assertTrue(dbLv == null);
	}
}
