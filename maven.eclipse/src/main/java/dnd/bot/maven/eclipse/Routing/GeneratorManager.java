package dnd.bot.maven.eclipse.Routing;

import java.util.HashMap;

import dnd.bot.maven.eclipse.Routing.Generators.AddGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.AppearanceGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.BaseGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.CharacterGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.DescriptionGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.FeaturesGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.GradeGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.GradesGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.HPGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.InventoryGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.ItemsGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.LevelGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.NotesGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.PersonalityGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.PossessionsGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.SocialGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.SpellsGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.StatsGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.UserGenerator;
import dnd.bot.maven.eclipse.db.Services.ReposStorage;

public class GeneratorManager {
    private HashMap<String, BaseGenerator> generators; 
    private ReposStorage reposStorage;

    public GeneratorManager() {
        Init();
    }

    private void Init() {
        reposStorage = new ReposStorage();
        generators = new HashMap<>();

        generators.put("gotouser", new UserGenerator(this));

        generators.put("add", new AddGenerator());

        generators.put("gotocharacter", new CharacterGenerator());

        generators.put("gotodescription", new DescriptionGenerator());
        generators.put("gotoinventory", new InventoryGenerator());
        generators.put("gotogrades", new GradesGenerator(this));
        generators.put("gotonotes", new NotesGenerator(this));

        generators.put("gotopersonality", new PersonalityGenerator(this));
        generators.put("gotohp", new HPGenerator(this));
        generators.put("gotolevel", new LevelGenerator(this));
        generators.put("gotosocial", new SocialGenerator(this));
        generators.put("gotoitems", new ItemsGenerator(this));

        generators.put("gotofeatures", new FeaturesGenerator(this));
        generators.put("gotopossessions", new PossessionsGenerator(this));

        generators.put("gotostats", new StatsGenerator(this));
        generators.put("gotoappearance", new AppearanceGenerator(this));

        generators.put("gotograde", new GradeGenerator(this));
        generators.put("gotospells", new SpellsGenerator(this));
    }

    public BaseGenerator getGeneratorByStateName(String stateName) {
        return generators.get(stateName);
    }

    public ReposStorage getReposStorage() {
        return reposStorage;
    }
}
