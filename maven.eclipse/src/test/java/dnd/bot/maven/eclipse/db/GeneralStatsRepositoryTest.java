package dnd.bot.maven.eclipse.db;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.User.Character.Stats.Stat.Skills.Knowledge.KnowledgeLevel;
import dnd.bot.maven.eclipse.db.Models.StatNameEnum;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.StatCompositeKey;
import dnd.bot.maven.eclipse.db.Models.dbo.GeneralStatDBO;
import dnd.bot.maven.eclipse.db.Models.dbo.SkillDbo;
import dnd.bot.maven.eclipse.db.Services.dbConnector;
import dnd.bot.maven.eclipse.db.repos.MongoGeneralStatRepository;

public class GeneralStatsRepositoryTest {
private static MongoGeneralStatRepository rep;
	private static dbConnector conn;
	
	@BeforeAll
	public static void SetUp() 
	{
        conn = new dbConnector("test-java-dnd-bot");
        rep = new MongoGeneralStatRepository(conn.getDb());
	}
	
	@AfterAll
	public static void TearDown() 
	{
		conn.getDb().drop();
	}

    @Test
    public void AddStatTest()
    {
        var characterId = new ObjectId();
        var statName = StatNameEnum.CHARISMA;
        var compositeKey = new StatCompositeKey(characterId, statName);
        var stat = new GeneralStatDBO(characterId, statName);
        rep.InsertDocument(stat);

        var dbStat = rep.GetDocumentByKey(compositeKey);
        assertTrue(dbStat != null);
        assertTrue(dbStat.statName.equals(statName));
    }

    @Test
    public void GetStatsByCharacterIdTest()
    {
        var characterId = new ObjectId();
        var stat1 = new GeneralStatDBO(characterId, StatNameEnum.CHARISMA);
        var stat2 = new GeneralStatDBO(characterId, StatNameEnum.CONSTITUTION);
        var anotherStat = new GeneralStatDBO(new ObjectId(), StatNameEnum.DEXTERITY);
        rep.InstertStats(stat1, stat2, anotherStat);

        var stats = rep.GetCharacterStats(characterId);
        assertTrue(stats != null);
        assertTrue(stats.size() == 2);
        assertTrue(stats.get(0).statName.equals(StatNameEnum.CHARISMA));
        assertTrue(stats.get(1).statName.equals(StatNameEnum.CONSTITUTION));
    }

    @Test
    public void UpdateStatFieldTest()
    {
        var characterId = new ObjectId();
        var statName = StatNameEnum.CHARISMA;
        var compositeKey = new StatCompositeKey(characterId, statName);
        var stat = new GeneralStatDBO(characterId, statName);
        rep.InstertStats(stat);

        var dbStat = rep.GetDocumentByKey(compositeKey);
        assertTrue(dbStat != null);
        assertTrue(dbStat.statName.equals(statName));
        var oldFieldValue = dbStat.value;

        var newValue = 5;
        rep.UpdateField(compositeKey, "value", newValue);
        dbStat = rep.GetDocumentByKey(compositeKey);
        assertTrue(dbStat != null);
        assertFalse(dbStat.value == oldFieldValue);
        assertTrue(dbStat.value == newValue);
    }

    @Test
    public void AddStatWithSkillTest()
    {
        var characterId = new ObjectId();
        var statName = StatNameEnum.CHARISMA;
        var compositeKey = new StatCompositeKey(characterId, statName);

        var skillName = "testSkill";
        var skill = new SkillDbo(skillName);
        var skills = new HashMap<String, SkillDbo>();

        skills.put(skillName, skill);
        var stat = new GeneralStatDBO(characterId, statName, skills);
        rep.InstertStats(stat);

        var dbStat = rep.GetDocumentByKey(compositeKey);
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
        var statName = StatNameEnum.CHARISMA;
        var compositeKey = new StatCompositeKey(characterId, statName);

        var skillName = "testSkill";
        var skill = new SkillDbo(skillName);
        var skills = new HashMap<String, SkillDbo>();
        skills.put(skillName, skill);
        var stat = new GeneralStatDBO(characterId, statName, skills);
        rep.InstertStats(stat);

        var newKnowledgeLevelValue = KnowledgeLevel.INTERMEDIATE;
        var newtotalBonusesValue = 10;
        rep.UpdateInnerField(compositeKey, skillName, "knowledgeLevel", newKnowledgeLevelValue); 
        rep.UpdateInnerField(compositeKey, skillName, "totalBonuses", newtotalBonusesValue); 

        var dbStat = rep.GetDocumentByKey(compositeKey);
        assertTrue(dbStat != null);
        assertTrue(dbStat.skills != null);
        assertTrue(dbStat.skills.containsKey(skillName));
        assertTrue(dbStat.skills.get(skillName).name.equals(skill.name));
        assertFalse(dbStat.skills.get(skillName).knowledgeLevel.equals(skill.knowledgeLevel));
        assertFalse(dbStat.skills.get(skillName).totalBonuses == skill.totalBonuses);
        assertTrue(dbStat.skills.get(skillName).knowledgeLevel.equals(newKnowledgeLevelValue));
        assertTrue(dbStat.skills.get(skillName).totalBonuses == newtotalBonusesValue);
    }
}
