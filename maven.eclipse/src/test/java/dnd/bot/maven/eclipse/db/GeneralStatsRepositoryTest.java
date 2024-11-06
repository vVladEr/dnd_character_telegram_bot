package dnd.bot.maven.eclipse.db;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.User.Character.Stats.Stat.Skills.Knowledge.KnowledgeLevel;
import dnd.bot.maven.eclipse.db.dbo.GeneralStatDBO;
import dnd.bot.maven.eclipse.db.dbo.SkillDbo;
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

    @Test
    public void AddStatWithSkillTest()
    {
        var characterId = new ObjectId();
        var statName = "testStat";
        var skillName = "testSkill";
        var skill = new SkillDbo(skillName);
        var skills = new HashMap<String, SkillDbo>();
        skills.put(skillName, skill);
        var stat = new GeneralStatDBO(characterId, statName, skills);
        rep.InstertStats(stat);

        var dbStat = rep.GetCharacterStat(characterId, statName);
        assertTrue(dbStat != null);
        assertTrue(dbStat.skills != null);
        assertTrue(dbStat.skills.containsKey(skillName));
        assertTrue(dbStat.skills.get(skillName).name.equals(skill.name));
        assertTrue(dbStat.skills.get(skillName).knowledgeLevel.equals(KnowledgeLevel.BASIC));
    }

    @Test
    public void UpdateSkillFieldTest()
    {
        var characterId = new ObjectId();
        var statName = "testStat";
        var skillName = "testSkill";
        var skill = new SkillDbo(skillName);
        var skills = new HashMap<String, SkillDbo>();
        skills.put(skillName, skill);
        var stat = new GeneralStatDBO(characterId, statName, skills);
        rep.InstertStats(stat);

        var newKnowledgeLevelValue = KnowledgeLevel.INTERMEDIATE;
        var newtotalBonusesValue = 10;
        rep.UpdateSkillField(characterId, statName, skillName, "knowledgeLevel", newKnowledgeLevelValue); 
        rep.UpdateSkillField(characterId, statName, skillName, "totalBonuses", newtotalBonusesValue); 

        var dbStat = rep.GetCharacterStat(characterId, statName);
        assertTrue(dbStat != null);
        assertTrue(dbStat.skills != null);
        assertTrue(dbStat.skills.containsKey(skillName));
        assertTrue(dbStat.skills.get(skillName).name.equals(skill.name));
        assertFalse(dbStat.skills.get(skillName).knowledgeLevel.equals(skill.knowledgeLevel));
        assertFalse(dbStat.skills.get(skillName).totalBonuses == skill.totalBonuses);
        assertTrue(dbStat.skills.get(skillName).knowledgeLevel.equals(newKnowledgeLevelValue));
        assertTrue(dbStat.skills.get(skillName).totalBonuses == newtotalBonusesValue);
    }

    //TODO Влад дописать тесты на изменение параметров скиллов
}
