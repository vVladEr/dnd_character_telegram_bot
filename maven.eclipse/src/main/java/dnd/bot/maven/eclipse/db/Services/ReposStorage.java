package dnd.bot.maven.eclipse.db.Services;

import com.mongodb.client.MongoDatabase;

import dnd.bot.maven.eclipse.db.repos.MongoAppearenceRepository;
import dnd.bot.maven.eclipse.db.repos.MongoFeaturesRepository;
import dnd.bot.maven.eclipse.db.repos.MongoGeneralStatRepository;
import dnd.bot.maven.eclipse.db.repos.MongoGradesRepository;
import dnd.bot.maven.eclipse.db.repos.MongoHPRepository;
import dnd.bot.maven.eclipse.db.repos.MongoItemsRepository;
import dnd.bot.maven.eclipse.db.repos.MongoLevelRepository;
import dnd.bot.maven.eclipse.db.repos.MongoPersonalityRepository;
import dnd.bot.maven.eclipse.db.repos.MongoPossesionsRepository;
import dnd.bot.maven.eclipse.db.repos.MongoSocialRepository;
import dnd.bot.maven.eclipse.db.repos.MongoUserRepository;

public class ReposStorage {

    private MongoAppearenceRepository appearenceRepository;

    private MongoFeaturesRepository featuresRepository;

    private MongoGeneralStatRepository statRepository;

    private MongoGradesRepository gradesRepository;

    private MongoHPRepository hpRepository;

    private MongoItemsRepository itemsRepository;

    private MongoLevelRepository levelRepository;

    private MongoPersonalityRepository personalityRepository;

    private MongoPossesionsRepository possesionsRepository;

    private MongoSocialRepository socialRepository;

    private MongoUserRepository userRepository;

    public ReposStorage()
    {
        var dbConnector = new dbConnector();
        Init(dbConnector.DB);
    }

    public ReposStorage(String dbName)
    {
        var dbConnector = new dbConnector(dbName);
        Init(dbConnector.DB);
    }

    private void Init(MongoDatabase db)
    {
        appearenceRepository = new MongoAppearenceRepository(db);
        featuresRepository = new MongoFeaturesRepository(db);
        statRepository = new MongoGeneralStatRepository(db);
        gradesRepository = new MongoGradesRepository(db);
        hpRepository = new MongoHPRepository(db);
        itemsRepository = new MongoItemsRepository(db);
        levelRepository = new MongoLevelRepository(db);
        personalityRepository = new MongoPersonalityRepository(db);
        possesionsRepository = new MongoPossesionsRepository(db);
        socialRepository = new MongoSocialRepository(db);
        userRepository = new MongoUserRepository(db);
    }

    
    public MongoAppearenceRepository getAppearenceRepository() {
        return appearenceRepository;
    }

    public MongoFeaturesRepository getFeaturesRepository() {
        return featuresRepository;
    }

    public MongoGeneralStatRepository getStatRepository() {
        return statRepository;
    }

    public MongoGradesRepository getGradesRepository() {
        return gradesRepository;
    }

    public MongoHPRepository getHpRepository() {
        return hpRepository;
    }

    public MongoItemsRepository getItemsRepository() {
        return itemsRepository;
    }

    public MongoLevelRepository getLevelRepository() {
        return levelRepository;
    }

    public MongoPersonalityRepository getPersonalityRepository() {
        return personalityRepository;
    }

    public MongoPossesionsRepository getPossesionsRepository() {
        return possesionsRepository;
    }

    public MongoSocialRepository getSocialRepository() {
        return socialRepository;
    }

    public MongoUserRepository getUserRepository() {
        return userRepository;
    }
}
