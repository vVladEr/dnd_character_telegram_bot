package dnd.bot.maven.eclipse.db.Services;

import org.bson.types.ObjectId;

import dnd.bot.maven.eclipse.db.Models.StatNameEnum;
import dnd.bot.maven.eclipse.db.Models.dbo.AppearenceDbo;
import dnd.bot.maven.eclipse.db.Models.dbo.GeneralStatDBO;
import dnd.bot.maven.eclipse.db.Models.dbo.HPDbo;
import dnd.bot.maven.eclipse.db.Models.dbo.LevelDbo;
import dnd.bot.maven.eclipse.db.Models.dbo.PersonalityDbo;
import dnd.bot.maven.eclipse.db.Models.dbo.SocialDbo;

public class CharacterCreater {
    private ReposStorage reposStorage;

    public CharacterCreater(ReposStorage reposStorage) {
        this.reposStorage = reposStorage;
    }

    public ObjectId CreateCharacter(String userId, String characterName) {
        var characterId = new ObjectId();

        var appearenceRepository = reposStorage.getAppearenceRepository();
        appearenceRepository.insertDocument(new AppearenceDbo(characterId));

        insertCharacterStats(characterId);

        var hpRepository = reposStorage.getHpRepository();
        hpRepository.insertDocument(new HPDbo(characterId));

        var levelRepository = reposStorage.getLevelRepository();
        levelRepository.insertDocument(new LevelDbo(characterId));

        var personalityRepository = reposStorage.getPersonalityRepository();
        personalityRepository.insertDocument(new PersonalityDbo(characterId));

        var socialRepository = reposStorage.getSocialRepository();
        socialRepository.insertDocument(new SocialDbo(characterId));

        var userRepository = reposStorage.getUserRepository();
        userRepository.AddCharacterToUser(userId, characterId, characterName);

        return characterId;
    }

    public ObjectId createCharacter(String userId) {
        return CreateCharacter(userId, "Noname");
    }

    private void insertCharacterStats(ObjectId characterId) {
        var statsRepository = reposStorage.getStatRepository();
        statsRepository.insertDocument(new GeneralStatDBO(characterId, StatNameEnum.CHARISMA));
        statsRepository.insertDocument(new GeneralStatDBO(characterId, StatNameEnum.CONSTITUTION));
        statsRepository.insertDocument(new GeneralStatDBO(characterId, StatNameEnum.DEXTERITY));
        statsRepository.insertDocument(new GeneralStatDBO(characterId, StatNameEnum.INTELLIGENCE));
        statsRepository.insertDocument(new GeneralStatDBO(characterId, StatNameEnum.STRENGTH));
        statsRepository.insertDocument(new GeneralStatDBO(characterId, StatNameEnum.WISDOM));
    }
}
