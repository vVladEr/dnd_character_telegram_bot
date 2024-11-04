package dnd.bot.maven.eclipse.db;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.db.dbo.GeneralStatDBO;
import dnd.bot.maven.eclipse.db.repos.MongoGeneralStatRepository;

public class GeneralStatsRepositoryTest {
private static MongoGeneralStatRepository rep;
	private static dbConnector conn;
	
	@BeforeAll
	public static void SetUp() 
	{
        conn = new dbConnector("test-java-dnd-bot");
        rep = new MongoGeneralStatRepository(conn.DB);
	}
	
	@AfterAll
	public static void TearDown() 
	{
		conn.DB.drop();
	}

    @Test
    public void AddStatTest()
    {
        var characterId = new ObjectId();
        var statName = "testStat";
        var stat = new GeneralStatDBO(characterId, statName);
        rep.InstertStats(stat);

        var dbStat = rep.GetCharacterStat(characterId, statName);
        assertTrue(dbStat != null);
        assertTrue(dbStat.statName.equals(statName));
    }

    @Test
    public void GetStatsByCharacterIdTest()
    {
        var characterId = new ObjectId();
        var stat1 = new GeneralStatDBO(characterId, "testStat1");
        var stat2 = new GeneralStatDBO(characterId, "testStat2");
        var anotherStat = new GeneralStatDBO(new ObjectId(), "stat from another character");
        rep.InstertStats(stat1, stat2, anotherStat);

        var stats = rep.GetCharacterStats(characterId);
        assertTrue(stats != null);
        assertTrue(stats.size() == 2);
        assertTrue(stats.get(0).statName.equals("testStat1"));
        assertTrue(stats.get(1).statName.equals("testStat2"));
    }

    @Test
    public void UpdateStatFieldTest()
    {
        var characterId = new ObjectId();
        var statName = "testStat";
        var stat = new GeneralStatDBO(characterId, statName);
        rep.InstertStats(stat);

        var dbStat = rep.GetCharacterStat(characterId, statName);
        assertTrue(dbStat != null);
        assertTrue(dbStat.statName.equals(statName));
        var oldFieldValue = dbStat.value;

        rep.UpdateStatField(characterId, statName, "value", 10);
        dbStat = rep.GetCharacterStat(characterId, statName);
        assertTrue(dbStat != null);
        assertFalse(dbStat.value == oldFieldValue);
        assertTrue(dbStat.value == 10);
    }

    //TODO Влад дописать тесты на изменение параметров скиллов
}
