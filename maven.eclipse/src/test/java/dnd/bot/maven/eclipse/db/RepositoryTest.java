package dnd.bot.maven.eclipse.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.UUID;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.db.Models.dbo.BasicDescriptionDbo;
import dnd.bot.maven.eclipse.db.Services.dbConnector;
import dnd.bot.maven.eclipse.db.repos.MongoNotesRepository;

public class RepositoryTest {
	private static MongoNotesRepository rep;
	private static dbConnector conn;

	@BeforeAll
	public static void setUp() {
		conn = new dbConnector("test-java-dnd-bot");
		rep = new MongoNotesRepository(conn.getDb());
	}

	@AfterAll
	public static void tearDown() {
		conn.getDb().drop();
	}

	@Test
	public void addDocTest() {
		var characterId = new ObjectId();
		var note = new BasicDescriptionDbo(characterId, "newNote", "newNote");
		rep.insertDocument(note);
		var descId = rep.getCharacterNotes(characterId).get(0).id;
		var noteDbo = rep.getDocumentByKey(descId);
		assertNotNull(noteDbo);
		assertEquals(note.characterId, noteDbo.characterId);
	}

	@Test
	public void updateFieldTest() {
		var characterId = new ObjectId();
		var desc = "desc";
		var note = new BasicDescriptionDbo(characterId, "newNote", desc);
		rep.insertDocument(note);
		var descId = rep.getCharacterNotes(characterId).get(0).id;
		var newDesc = "newDesc";
		rep.updateField(descId, "description", newDesc);
		var noteDbo = rep.getDocumentByKey(descId);
		assertNotNull(noteDbo);
		assertEquals(note.characterId, noteDbo.characterId);
		assertEquals(newDesc, noteDbo.description);
	}

	@Test
	public void updateUnexpectedField_ShouldNotUpdategAnythingTest() {
		var characterId = new ObjectId();
		var note = new BasicDescriptionDbo(characterId, "newNote", "desc");
		rep.insertDocument(note);
		var descId = rep.getCharacterNotes(characterId).get(0).id;
		rep.updateField(descId, UUID.randomUUID().toString(), 5);
		var noteDbo = rep.getDocumentByKey(descId);
		assertNotNull(noteDbo);
		assertEquals(note.characterId, noteDbo.characterId);
		assertEquals(note.name, noteDbo.name);
		assertEquals(note.description, noteDbo.description);
	}

	@Test
	public void deleteDocTest() {
		var characterId = new ObjectId();
		var note = new BasicDescriptionDbo(characterId,"newNote", "newNote");
		rep.insertDocument(note);
		var descId = rep.getCharacterNotes(characterId).get(0).id;
		var noteDbo = rep.getDocumentByKey(descId);
		assertNotNull(noteDbo);
		assertEquals(note.characterId, noteDbo.characterId);
		rep.deleteDocument(descId);
		noteDbo = rep.getDocumentByKey(descId);
		assertNull(noteDbo);
	}
}
