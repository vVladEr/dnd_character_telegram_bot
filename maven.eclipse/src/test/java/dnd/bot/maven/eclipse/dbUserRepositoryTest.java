package dnd.bot.maven.eclipse;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.db.dbConnector;
import dnd.bot.maven.eclipse.db.dbo.UserDBO;
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
		var user = new UserDBO(userId);
		rep.InsertUser(user);
        var dbUser = rep.GetUserById(userId);
        assertTrue(dbUser != null);
        assertTrue(dbUser.GetId().equals(user.GetId()));
	}

	@Test
	public void AddCharactersToUserTest() 
	{
		String userId = new ObjectId().toString();
		var user = new UserDBO(userId);
		rep.InsertUser(user);
		var uuid1 = new ObjectId();
		var uuid2 = new ObjectId();
		rep.AddCharacterToUser(userId, uuid1);
		rep.AddCharacterToUser(userId, uuid2);
        var dbUser = rep.GetUserById(userId);
        assertTrue(dbUser != null);
        assertTrue(dbUser.characters.size() == 2);
        assertTrue(dbUser.GetId().equals(user.GetId()));
        assertTrue(dbUser.characters.get(0).equals(uuid1));
        assertTrue(dbUser.characters.get(1).equals(uuid2));
	}

}
