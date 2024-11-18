package dnd.bot.maven.eclipse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public static void SetUp() 
	{
        reposStorage = new ReposStorage("test-db");
        characterCreater = new CharacterCreater(reposStorage);
        var userRepo = reposStorage.getUserRepository();
        userId = new ObjectId().toString();
        userRepo.InsertDocument(new UserDBO(userId));
        characterId = characterCreater.CreateCharacter(userId);
	}

    @Test
    public void AddCharcaterIdToUser()
    {
        var userDbo = reposStorage.getUserRepository().GetDocumentByKey(userId);
        assertTrue(userDbo.characters.contains(characterId));
    }

    @Test
    public void addCharacterInfoIntoRepos()
    {
        var appearenceDbo = reposStorage.getAppearenceRepository().GetDocumentByKey(characterId);
        var hpDbo = reposStorage.getHpRepository().GetDocumentByKey(characterId);
        var levelDbo = reposStorage.getLevelRepository().GetDocumentByKey(characterId);
        var personalityDbo = reposStorage.getPersonalityRepository().GetDocumentByKey(characterId);
        var socialDbo = reposStorage.getSocialRepository().GetDocumentByKey(characterId);
        var characterStats = reposStorage.getStatRepository().GetCharacterStats(characterId);



        assertNotNull(appearenceDbo);
        assertNotNull(hpDbo);
        assertNotNull(levelDbo);
        assertNotNull(personalityDbo);
        assertNotNull(socialDbo);
        assertNotEquals(0, characterStats.size());
    }

    @Test
    public void addDefaultInfoForLevelRepo()
    {
        var levelDbo = reposStorage.getLevelRepository().GetDocumentByKey(characterId);


        assertNotNull(levelDbo);
        assertEquals(0, levelDbo.currentExp);
        assertEquals(1, levelDbo.currentLevel);
        assertEquals(300, levelDbo.necessaryExp);
    }

    @Test
    public void addDefaultInfoForSocialRepo()
    {
        var socialDbo = reposStorage.getSocialRepository().GetDocumentByKey(characterId);


        assertNotNull(socialDbo);
        assertEquals("Unknown", socialDbo.characterName);
    }

    @Test
    public void addDefaultInfoForStatRepo()
    {
        var stats = reposStorage.getStatRepository().GetCharacterStats(characterId);


        assertNotNull(stats);
        assertEquals(6, stats.size());
    }
}
