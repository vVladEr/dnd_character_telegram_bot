package dnd.bot.maven.eclipse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.db.Models.StatNameEnum;
import dnd.bot.maven.eclipse.db.Models.UpdateFieldRequest;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.GradeCompositeKey;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.StatCompositeKey;
import dnd.bot.maven.eclipse.db.Models.dbo.GradeDBo;
import dnd.bot.maven.eclipse.db.Models.dbo.ItemDBO;
import dnd.bot.maven.eclipse.db.Services.CharacterCreater;
import dnd.bot.maven.eclipse.db.Services.ReposStorage;
import dnd.bot.maven.eclipse.db.Services.UpdateManager;

public class UpdateManagerTest {

    private static UpdateManager updateManager;
    private static ReposStorage reposStorage;
    private static ObjectId characterId;

    @BeforeAll
    public static void setUp() {
        reposStorage = new ReposStorage("test-db");
        updateManager = new UpdateManager(reposStorage);
        var characterCreater = new CharacterCreater(reposStorage);
        var userId = new ObjectId().toString();
        characterId = characterCreater.createCharacter(userId);
    }

    @Test
    public void updateAppearenceField() {
        var oldValue = reposStorage.getAppearenceRepository().getDocumentByKey(characterId).age;
        var newValue = oldValue + 2;
        var updateRequest = new UpdateFieldRequest(
                new Combinekey(characterId),
                "age", newValue,
                "Appearance");
        updateManager.updateField(updateRequest);

        var updatedDbo = reposStorage.getAppearenceRepository().getDocumentByKey(characterId);

        assertNotNull(updatedDbo);
        assertNotEquals(oldValue, updatedDbo.age);
        assertEquals(newValue, updatedDbo.age);
    }

    @Test
    public void updateItemField() {
        var oldItem = new ItemDBO(characterId, "testItem", "just test item");
        var item = reposStorage.getItemsRepository().insertDocument(oldItem);
        var oldValue = oldItem.amount;
        var newValue = oldValue + 2;
        var updateRequest = new UpdateFieldRequest(
                new Combinekey(item.itemId),
                "amount", newValue,
                "Items");
        updateManager.updateField(updateRequest);

        var updatedDbo = reposStorage.getItemsRepository().getDocumentByKey(item.itemId);

        assertNotNull(updatedDbo);
        assertNotEquals(oldValue, updatedDbo.amount);
        assertEquals(newValue, updatedDbo.amount);
    }

    @Test
    public void updateSocialField() {
        var social = reposStorage.getSocialRepository().getDocumentByKey(characterId);
        var oldValue = social.race;
        var newValue = "new race";
        var updateRequest = new UpdateFieldRequest(
                new Combinekey(characterId),
                "race", newValue,
                "Social");
        updateManager.updateField(updateRequest);

        var updatedDbo = reposStorage.getSocialRepository().getDocumentByKey(characterId);

        assertNotNull(updatedDbo);
        assertNotEquals(oldValue, updatedDbo.race);
        assertEquals(newValue, updatedDbo.race);
    }

    @Test
    public void updateStatInnerField() {
        var statName = StatNameEnum.CHARISMA;
        var compositeKey = new StatCompositeKey(characterId, statName);
        var social = reposStorage.getStatRepository().getDocumentByKey(compositeKey);

        var skillName = social.skills.keySet().iterator().next();
        var oldValue = social.skills.get(skillName).totalBonuses;
        var newValue = oldValue + 2;

        var updateRequest = new UpdateFieldRequest(
                new Combinekey(characterId, statName),
                skillName,
                "totalBonuses",
                newValue,
                "Stat");
        updateManager.updateField(updateRequest);

        var updatedDbo = reposStorage.getStatRepository().getDocumentByKey(compositeKey);

        assertNotNull(updatedDbo);
        assertNotEquals(oldValue, updatedDbo.skills.get(skillName).totalBonuses);
        assertEquals(newValue, updatedDbo.skills.get(skillName).totalBonuses);
    }

    @Test
    public void updateGradeInnerField() {
        var grade = 1;
        var compositeKey = new GradeCompositeKey(characterId, 1);
        reposStorage.getGradesRepository().insertDocument(new GradeDBo(characterId, 1));
        var spellName = "testSpell";
        var oldValue = "just test spell";

        reposStorage.getGradesRepository().addSpell(compositeKey, spellName, oldValue);

        var newValue = "new desc";

        var updateRequest = new UpdateFieldRequest(
                new Combinekey(characterId, grade),
                spellName,
                "description",
                newValue,
                "Grade");
        updateManager.updateField(updateRequest);

        var updatedDbo = reposStorage.getGradesRepository().getDocumentByKey(compositeKey);

        assertNotNull(updatedDbo);
        assertNotEquals(oldValue, updatedDbo.spells.get(spellName).description);
        assertEquals(newValue, updatedDbo.spells.get(spellName).description);
    }

    @Test
    public void renameSpell() {
        var grade = 1;
        var compositeKey = new GradeCompositeKey(characterId, 1);
        reposStorage.getGradesRepository().insertDocument(new GradeDBo(characterId, 1));
        var oldSpellName = "testSpell";
        var desc = "just test spell";

        reposStorage.getGradesRepository().addSpell(compositeKey, oldSpellName, desc);

        var newName = "new name";

        var updateRequest = new UpdateFieldRequest(
                new Combinekey(characterId, grade),
                oldSpellName,
                "name",
                newName,
                "Grade");
        updateManager.updateField(updateRequest);

        var updatedDbo = reposStorage.getGradesRepository().getDocumentByKey(compositeKey);

        assertNotNull(updatedDbo);
        assertFalse(updatedDbo.spells.containsKey(oldSpellName));
        assertTrue(updatedDbo.spells.containsKey(newName));
        assertEquals(desc, updatedDbo.spells.get(newName).description);
    }
}
