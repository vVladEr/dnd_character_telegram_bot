package dnd.bot.maven.eclipse.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.db.Models.dbo.UserDBO;
import dnd.bot.maven.eclipse.db.Services.dbConnector;
import dnd.bot.maven.eclipse.db.repos.MongoUserRepository;

public class UserRepositoryTest {
	private static MongoUserRepository rep;
	private static dbConnector conn;

	@BeforeAll
	public static void setUp() {
		conn = new dbConnector("test-java-dnd-bot");
		rep = new MongoUserRepository(conn.getDb());
	}

	@AfterAll
	public static void tearDown() {
		conn.getDb().drop();
	}

	@Test
	public void addUserTest() {
		String userId = new ObjectId().toString();
		var user = new UserDBO(userId);
		rep.insertDocument(user);

		var dbUser = rep.getDocumentByKey(userId);
		assertNotNull(dbUser);
		assertEquals(user.GetId(), dbUser.GetId());
	}

	@Test
	public void addCharactersToUserTest() {
		String userId = new ObjectId().toString();
		var user = new UserDBO(userId);
		rep.insertDocument(user);

		var characterId1 = new ObjectId();
		var characterId2 = new ObjectId();
		rep.AddCharacterToUser(userId, characterId1, "name1");
		rep.AddCharacterToUser(userId, characterId2, "name2");

		var dbUser = rep.getDocumentByKey(userId);
		assertNotNull(dbUser);
		assertEquals(2, dbUser.characters.size());
		assertEquals(user.GetId(), dbUser.GetId());
		assertEquals(characterId1, dbUser.characters.get(0).CharacterId);
		assertEquals(characterId2, dbUser.characters.get(1).CharacterId);
	}

	@Test
	public void deleteCharactersFromUserTest() {
		String userId = new ObjectId().toString();
		var user = new UserDBO(userId);
		rep.insertDocument(user);

		var characterId1 = new ObjectId();
		var characterName1 = "1";
		var characterId2 = new ObjectId();
		var characterName2 = "2";
		rep.AddCharacterToUser(userId, characterId1, characterName1);
		rep.AddCharacterToUser(userId, characterId2, characterName2);

		var dbUser = rep.getDocumentByKey(userId);
		assertNotNull(dbUser);
		assertEquals(2, dbUser.characters.size());
		assertEquals(user.GetId(), dbUser.GetId());

		assertEquals(characterId1, dbUser.characters.get(0).CharacterId);
		assertEquals(characterId2, dbUser.characters.get(1).CharacterId);

		rep.RemoveCharacterFromUser(userId, characterId1);
		dbUser = rep.getDocumentByKey(userId);
		assertNotNull(dbUser);
		assertEquals(1, dbUser.characters.size());
		assertEquals(characterId2, dbUser.characters.get(0).CharacterId);
	}
}
