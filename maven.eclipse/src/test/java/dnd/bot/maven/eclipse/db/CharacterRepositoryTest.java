package dnd.bot.maven.eclipse.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.db.Models.dbo.CharacterDbo;
import dnd.bot.maven.eclipse.db.Services.dbConnector;
import dnd.bot.maven.eclipse.db.repos.MongoCharactersRepository;

public class CharacterRepositoryTest {
	private static MongoCharactersRepository rep;
	private static dbConnector conn;

	@BeforeAll
	public static void setUp() {
		conn = new dbConnector("test-java-dnd-bot");
		rep = new MongoCharactersRepository(conn.getDb());
	}

	@AfterAll
	public static void tearDown() {
		conn.getDb().drop();
	}

	@Test
	public void addCharactersToUserTest() {
		String userId = new ObjectId().toString();

		var characterId1 = new ObjectId();
		var characterId2 = new ObjectId();
		rep.insertDocument( new CharacterDbo(characterId1, "name1", userId));
		rep.insertDocument(new CharacterDbo(characterId2, "name2", userId));

		var characters = rep.getUserCharacters(userId);
		assertNotNull(characters);
		assertEquals(2, characters.size());
		assertEquals(characterId1, characters.get(0).id);
		assertEquals(characterId2, characters.get(1).id);
	}

	@Test
	public void deleteCharactersFromUserTest() {
		String userId = new ObjectId().toString();

		var characterId1 = new ObjectId();
		var characterId2 = new ObjectId();
		rep.insertDocument( new CharacterDbo(characterId1, "name1", userId));
		rep.insertDocument(new CharacterDbo(characterId2, "name2", userId));

		var characters = rep.getUserCharacters(userId);
		assertNotNull(characters);
		assertEquals(2, characters.size());

		assertEquals(characterId1, characters.get(0).id);
		assertEquals(characterId2, characters.get(1).id);

		rep.deleteDocument(characterId1);
		characters = rep.getUserCharacters(userId);
		assertNotNull(characters);
		assertEquals(1, characters.size());
		assertEquals(characterId2, characters.get(0).id);
	}

	@Test
	public void updateCharacterTest() {
		String userId = new ObjectId().toString();

		var characterId = new ObjectId();
		var characterName = "name1";
		rep.insertDocument(new CharacterDbo(characterId, characterName, userId));
		var newName = "newName";
		rep.updateField( characterId, "name", newName);

		var character = rep.getDocumentByKey(characterId);
		assertNotNull(character);
		assertEquals(characterId, character.id);
		assertEquals(newName, character.name);
	}
}
