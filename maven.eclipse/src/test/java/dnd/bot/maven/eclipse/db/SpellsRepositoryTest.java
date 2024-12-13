package dnd.bot.maven.eclipse.db;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.db.Models.CompositeKeys.GradeCompositeKey;
import dnd.bot.maven.eclipse.db.Models.dbo.GradeDBo;
import dnd.bot.maven.eclipse.db.Services.dbConnector;
import dnd.bot.maven.eclipse.db.repos.MongoGradesRepository;

public class SpellsRepositoryTest {
    private static MongoGradesRepository rep;
    private static dbConnector conn;

    @BeforeAll
    public static void setUp() {
        conn = new dbConnector("test-java-dnd-bot");
        rep = new MongoGradesRepository(conn.getDb());
    }

    @AfterAll
    public static void tearDown() {
        conn.getDb().drop();
    }

    @Test
    public void addGradeTest() {
        var characterId = new ObjectId();
        var grade = 2;
        var compositeKey = new GradeCompositeKey(characterId, grade);
        var gradeDbo = new GradeDBo(characterId, grade);
        rep.insertDocument(gradeDbo);

        var dbGradeDbo = rep.getDocumentByKey(compositeKey);
        assertTrue(dbGradeDbo != null);
        assertTrue(characterId.equals(dbGradeDbo.characterId));
        assertTrue(grade == dbGradeDbo.grade);
    }

    @Test
    public void updateFuildTest() {
        var characterId = new ObjectId();
        var grade = 2;
        var compositeKey = new GradeCompositeKey(characterId, grade);

        var gradeDbo = new GradeDBo(characterId, grade);
        rep.insertDocument(gradeDbo);

        rep.updateField(compositeKey, "maxCount", 2);

        var dbGradeDbo = rep.getDocumentByKey(compositeKey);
        assertTrue(dbGradeDbo != null);
        assertTrue(characterId.equals(dbGradeDbo.characterId));
        assertTrue(grade == dbGradeDbo.grade);
        assertTrue(gradeDbo.maxCount != dbGradeDbo.maxCount);
        assertTrue(dbGradeDbo.maxCount == 2);
    }

    @Test
    public void addSpellTest() {
        var characterId = new ObjectId();
        var grade = 2;
        var compositeKey = new GradeCompositeKey(characterId, grade);
        var gradeDbo = new GradeDBo(characterId, grade);
        var spellName = "TestSpell";
        var spellDesc = "testDesk";
        rep.insertDocument(gradeDbo);

        rep.addSpell(compositeKey, spellName, spellDesc);

        var dbGradeDbo = rep.getDocumentByKey(compositeKey);
        assertTrue(dbGradeDbo != null);
        assertTrue(characterId.equals(dbGradeDbo.characterId));
        assertTrue(dbGradeDbo.spells.size() == 1);
        assertTrue(dbGradeDbo.spells.containsKey(spellName));
        assertTrue(dbGradeDbo.spells.get(spellName).description.equals(spellDesc));
    }

    @Test
    public void updateSpellDescTest() {
        var characterId = new ObjectId();
        var grade = 2;
        var compositeKey = new GradeCompositeKey(characterId, grade);
        var gradeDbo = new GradeDBo(characterId, grade);
        var spellName = "TestSpell";
        var oldSpellDesc = "oldTestDesc";
        var newSpellDesc = "newTestDesc";
        rep.insertDocument(gradeDbo);

        rep.addSpell(compositeKey, spellName, oldSpellDesc);
        rep.updateInnerField(compositeKey, spellName, "description", newSpellDesc);

        var dbGradeDbo = rep.getDocumentByKey(compositeKey);
        assertTrue(dbGradeDbo != null);
        assertTrue(characterId.equals(dbGradeDbo.characterId));
        assertTrue(dbGradeDbo.spells.size() == 1);
        assertTrue(dbGradeDbo.spells.containsKey(spellName));
        assertFalse(dbGradeDbo.spells.get(spellName).description.equals(oldSpellDesc));
        assertTrue(dbGradeDbo.spells.get(spellName).description.equals(newSpellDesc));
    }
}
