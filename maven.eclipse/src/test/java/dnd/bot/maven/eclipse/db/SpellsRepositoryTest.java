package dnd.bot.maven.eclipse.db;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.db.Models.CompositeKeys.GradeCompositeKey;
import dnd.bot.maven.eclipse.db.Models.dbo.GradeDBo;
import dnd.bot.maven.eclipse.db.repos.MongoGradesRepository;

public class SpellsRepositoryTest {
    private static MongoGradesRepository rep;
	private static dbConnector conn;
	
	@BeforeAll
	public static void SetUp() 
	{
        conn = new dbConnector("test-java-dnd-bot");
        rep = new MongoGradesRepository(conn.DB);
	}
	
	@AfterAll
	public static void TearDown() 
	{
		conn.DB.drop();
	}

    @Test
    public void AddGradeTest()
    {
        var characterId = new ObjectId();
        var grade = 2;
        var compositeKey = new GradeCompositeKey(characterId, grade);
        var gradeDbo = new GradeDBo(characterId, grade);
        rep.InsertDocument(gradeDbo);

        var dbGradeDbo = rep.GetDocumentByKey(compositeKey);
        assertTrue(dbGradeDbo != null);
        assertTrue(characterId.equals(dbGradeDbo.characterId));
        assertTrue(grade == dbGradeDbo.grade);
    }

    @Test
    public void UpdateFuildTest()
    {
        var characterId = new ObjectId();
        var grade = 2;
        var compositeKey = new GradeCompositeKey(characterId, grade);

        var gradeDbo = new GradeDBo(characterId, grade);
        rep.InsertDocument(gradeDbo);

        rep.UpdateField(characterId, grade, "maxCount", 2);

        var dbGradeDbo = rep.GetDocumentByKey(compositeKey);
        assertTrue(dbGradeDbo != null);
        assertTrue(characterId.equals(dbGradeDbo.characterId));
        assertTrue(grade == dbGradeDbo.grade);
        assertTrue(gradeDbo.maxCount != dbGradeDbo.maxCount);
        assertTrue(dbGradeDbo.maxCount == 2);
    }

    @Test
    public void AddSpellTest()
    {
        var characterId = new ObjectId();
        var grade = 2;
        var compositeKey = new GradeCompositeKey(characterId, grade);
        var gradeDbo = new GradeDBo(characterId, grade);
        var spellName = "TestSpell";
        var spellDesc = "testDesk";
        rep.InsertDocument(gradeDbo);

        rep.AddSpell(characterId, grade, spellName, spellDesc);

        var dbGradeDbo = rep.GetDocumentByKey(compositeKey);
        assertTrue(dbGradeDbo != null);
        assertTrue(characterId.equals(dbGradeDbo.characterId));
        assertTrue(dbGradeDbo.spells.size() == 1);
        assertTrue(dbGradeDbo.spells.containsKey(spellName));
        assertTrue(dbGradeDbo.spells.get(spellName).equals(spellDesc));
    }

    @Test
    public void UpdateSpellDescTest()
    {
        var characterId = new ObjectId();
        var grade = 2;
        var compositeKey = new GradeCompositeKey(characterId, grade);
        var gradeDbo = new GradeDBo(characterId, grade);
        var spellName = "TestSpell";
        var oldSpellDesc = "oldTestDesc";
        var newSpellDesc = "newTestDesc";
        rep.InsertDocument(gradeDbo);

        rep.AddSpell(characterId, grade, spellName, oldSpellDesc);
        rep.UpdateSpellDesc(characterId, grade, spellName, newSpellDesc);

        var dbGradeDbo = rep.GetDocumentByKey(compositeKey);
        assertTrue(dbGradeDbo != null);
        assertTrue(characterId.equals(dbGradeDbo.characterId));
        assertTrue(dbGradeDbo.spells.size() == 1);
        assertTrue(dbGradeDbo.spells.containsKey(spellName));
        assertFalse(dbGradeDbo.spells.get(spellName).equals(oldSpellDesc));
        assertTrue(dbGradeDbo.spells.get(spellName).equals(newSpellDesc));
    }
}
