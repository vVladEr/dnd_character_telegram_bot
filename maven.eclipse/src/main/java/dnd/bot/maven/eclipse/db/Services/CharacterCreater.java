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

    public CharacterCreater(ReposStorage reposStorage)
    {
        this.reposStorage = reposStorage;
    }

    public ObjectId CreateCharacter(String userId)
    {
        var characterId = new ObjectId();

        var appearenceRepository = reposStorage.getAppearenceRepository();
        appearenceRepository.InsertDocument(new AppearenceDbo(characterId));

        InsertCharacterStats(characterId);

        var hpRepository = reposStorage.getHpRepository();
        hpRepository.InsertDocument(new HPDbo(characterId));

        var levelRepository = reposStorage.getLevelRepository();
        levelRepository.InsertDocument(new LevelDbo(characterId));

        var personalityRepository = reposStorage.getPersonalityRepository();
        personalityRepository.InsertDocument(new PersonalityDbo(characterId));

        var socialRepository = reposStorage.getSocialRepository();
        socialRepository.InsertDocument(new SocialDbo(characterId));

        var userRepository = reposStorage.getUserRepository();
        userRepository.AddCharacterToUser(userId, characterId);

        return characterId;
    }

    private void InsertCharacterStats(ObjectId characterId)
    {
        var statsRepository = reposStorage.getStatRepository();
        statsRepository.InsertDocument(new GeneralStatDBO(characterId, StatNameEnum.CHARISMA));
        statsRepository.InsertDocument(new GeneralStatDBO(characterId, StatNameEnum.CONSTITUTION));
        statsRepository.InsertDocument(new GeneralStatDBO(characterId, StatNameEnum.DEXTERITY));
        statsRepository.InsertDocument(new GeneralStatDBO(characterId, StatNameEnum.INTELLIGENCE));
        statsRepository.InsertDocument(new GeneralStatDBO(characterId, StatNameEnum.STRENGTH));
        statsRepository.InsertDocument(new GeneralStatDBO(characterId, StatNameEnum.WISDOM));
    }
}
