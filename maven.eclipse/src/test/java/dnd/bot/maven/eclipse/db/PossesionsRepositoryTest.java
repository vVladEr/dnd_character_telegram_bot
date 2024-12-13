package dnd.bot.maven.eclipse.db;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.db.Models.dbo.BasicDescriptionDbo;
import dnd.bot.maven.eclipse.db.Services.dbConnector;
import dnd.bot.maven.eclipse.db.repos.MongoPossesionsRepository;

public class PossesionsRepositoryTest {
	private static MongoPossesionsRepository rep;
	private static dbConnector conn;

	@BeforeAll
	public static void setUp() {
		conn = new dbConnector("test-java-dnd-bot");
		rep = new MongoPossesionsRepository(conn.getDb());
	}

	@AfterAll
	public static void tearDown() {
		conn.getDb().drop();
	}

	@Test
	public void getCharactersFeatures() {
		var characterId = new ObjectId();
		var feature1 = new BasicDescriptionDbo(characterId, "testPossesion1", "testDesc1");
		var feature2 = new BasicDescriptionDbo(characterId, "testPossesion2", "testDesc2");
		var anotherCharacterFeature = new BasicDescriptionDbo(new ObjectId(), "anotherFeature",
				"feature for another character");
		rep.insertDocument(feature1);
		rep.insertDocument(feature2);
		rep.insertDocument(anotherCharacterFeature);

		var features = rep.getCharacterPossesions(characterId);
		assertTrue(features != null);
		assertTrue(features.size() == 2);
		assertTrue(features.get(0).name.equals("testPossesion1"));
		assertTrue(features.get(0).description.equals("testDesc1"));
		assertTrue(features.get(1).name.equals("testPossesion2"));
		assertTrue(features.get(1).description.equals("testDesc2"));
	}
}