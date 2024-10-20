package dnd.bot.maven.eclipse;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.db.dbConnector;
import dnd.bot.maven.eclipse.db.repos.MongoUserRepository;

public class dbUserRepositoryTest {
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
		var retUser = rep.AddNewUser(userId);
        var dbUser = rep.GetUserById(userId);
        assertTrue(dbUser != null);
        assertTrue(dbUser.id.equals(retUser.id));
	}

	@Test
	public void AddCharactersToUserTest() 
	{
		String userId = new ObjectId().toString();
		var retUser = rep.AddNewUser(userId);
		var uuid1 = UUID.randomUUID();
		var uuid2 = UUID.randomUUID();
		rep.AddCharacterToUser(userId, uuid1);
		rep.AddCharacterToUser(userId, uuid2);
        var dbUser = rep.GetUserById(userId);
        assertTrue(dbUser != null);
        assertTrue(dbUser.characters.size() == 2);
        assertTrue(dbUser.id.equals(retUser.id));
        assertTrue(dbUser.characters.get(0).equals(uuid1));
        assertTrue(dbUser.characters.get(1).equals(uuid2));
	}

}
