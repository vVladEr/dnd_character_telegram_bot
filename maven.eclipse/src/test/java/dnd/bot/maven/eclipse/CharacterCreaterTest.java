package dnd.bot.maven.eclipse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.db.Models.dbo.UserDBO;
import dnd.bot.maven.eclipse.db.Services.CharacterCreater;
import dnd.bot.maven.eclipse.db.Services.ReposStorage;

public class CharacterCreaterTest {

    private static ReposStorage reposStorage;
    private static CharacterCreater characterCreater;
    private static String userId;
    private static ObjectId characterId;

    @BeforeAll
    public static void setUp() {
        reposStorage = new ReposStorage("test-db");
        characterCreater = new CharacterCreater(reposStorage);
        var userRepo = reposStorage.getUserRepository();
        userId = new ObjectId().toString();
        userRepo.insertDocument(new UserDBO(userId));
        characterId = characterCreater.createCharacter(userId);
    }

    @Test
    public void addCharcaterIdToUser() {
        var userDbo = reposStorage.getUserRepository().getDocumentByKey(userId);
        assertEquals(1, userDbo.characters.size());
    }

    @Test
    public void addCharacterInfoIntoRepos() {
        var appearenceDbo = reposStorage.getAppearenceRepository().getDocumentByKey(characterId);
        var hpDbo = reposStorage.getHpRepository().getDocumentByKey(characterId);
        var levelDbo = reposStorage.getLevelRepository().getDocumentByKey(characterId);
        var personalityDbo = reposStorage.getPersonalityRepository().getDocumentByKey(characterId);
        var socialDbo = reposStorage.getSocialRepository().getDocumentByKey(characterId);
        var characterStats = reposStorage.getStatRepository().getCharacterStats(characterId);

        assertNotNull(appearenceDbo);
        assertNotNull(hpDbo);
        assertNotNull(levelDbo);
        assertNotNull(personalityDbo);
        assertNotNull(socialDbo);
        assertNotEquals(0, characterStats.size());
    }

    @Test
    public void addDefaultInfoForLevelRepo() {
        var levelDbo = reposStorage.getLevelRepository().getDocumentByKey(characterId);

        assertNotNull(levelDbo);
        assertEquals(0, levelDbo.currentExp);
        assertEquals(1, levelDbo.currentLevel);
        assertEquals(300, levelDbo.necessaryExp);
    }

    @Test
    public void addDefaultInfoForStatRepo() {
        var stats = reposStorage.getStatRepository().getCharacterStats(characterId);

        assertNotNull(stats);
        assertEquals(6, stats.size());
    }
}
