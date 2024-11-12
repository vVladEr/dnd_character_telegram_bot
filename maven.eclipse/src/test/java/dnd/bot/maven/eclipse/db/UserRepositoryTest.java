package dnd.bot.maven.eclipse.db;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.db.Models.dbo.UserDBO;
import dnd.bot.maven.eclipse.db.Services.dbConnector;
import dnd.bot.maven.eclipse.db.repos.MongoUserRepository;

public class UserRepositoryTest{
	private static MongoUserRepository rep;
	private static dbConnector conn;
	
	@BeforeAll
	public static void SetUp() 
	{
        conn = new dbConnector("test-java-dnd-bot");
        rep = new MongoUserRepository(conn.DB);
	}
	
	@AfterAll
	public static void TearDown() 
	{
		conn.DB.drop();
	}
	
	@Test
	public void AddUserTest() 
	{
		String userId = new ObjectId().toString();
		var user = new UserDBO(userId);
		rep.InsertDocument(user);
		
        var dbUser = rep.GetDocumentByKey(userId);
        assertTrue(dbUser != null);
        assertTrue(dbUser.GetId().equals(user.GetId()));
	}

	@Test
	public void AddCharactersToUserTest() 
	{
		String userId = new ObjectId().toString();
		var user = new UserDBO(userId);
		rep.InsertDocument(user);

		var uuid1 = new ObjectId();
		var uuid2 = new ObjectId();
		rep.AddCharacterToUser(userId, uuid1);
		rep.AddCharacterToUser(userId, uuid2);

        var dbUser = rep.GetDocumentByKey(userId);
        assertTrue(dbUser != null);
        assertTrue(dbUser.characters.size() == 2);
        assertTrue(dbUser.GetId().equals(user.GetId()));
        assertTrue(dbUser.characters.get(0).equals(uuid1));
        assertTrue(dbUser.characters.get(1).equals(uuid2));
	}

	@Test
	public void DeleteCharactersFromUserTest() 
	{
		String userId = new ObjectId().toString();
		var user = new UserDBO(userId);
		rep.InsertDocument(user);

		var characterId1 = new ObjectId();
		var characterId2 = new ObjectId();
		rep.AddCharacterToUser(userId, characterId1);
		rep.AddCharacterToUser(userId, characterId2);

        var dbUser = rep.GetDocumentByKey(userId);
        assertTrue(dbUser != null);
        assertTrue(dbUser.characters.size() == 2);
        assertTrue(dbUser.GetId().equals(user.GetId()));
        assertTrue(dbUser.characters.get(0).equals(characterId1));
		assertTrue(dbUser.characters.get(1).equals(characterId2));

		rep.RemoveCharacterFromUser(userId, characterId1);
		dbUser = rep.GetDocumentByKey(userId);
        assertTrue(dbUser != null);
        assertTrue(dbUser.characters.size() == 1);
		assertTrue(dbUser.characters.get(0).equals(characterId2));
	}
}
