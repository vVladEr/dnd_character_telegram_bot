package dnd.bot.maven.eclipse.Routing;

import java.util.HashMap;

import dnd.bot.maven.eclipse.Routing.Generators.AppearanceGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.BaseGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.CharacterGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.DescriptionGenerator;
import dnd.bot.maven.eclipse.Routing.Generators.FeaturesGenerator;
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
    private CharacterGenerator characterGenerator;

    public GeneratorManager() {
        
        Init();
    }

    private void Init() {
        this.reposStorage = new ReposStorage();
        this.characterGenerator = new CharacterGenerator();
        var userGenerator = new UserGenerator(this);
        var featuresGenerator = new FeaturesGenerator(this);
        var possessionsGenerator = new PossessionsGenerator(this);
        var itemsGenerator = new ItemsGenerator(this);
        var gradesGenerator = new GradesGenerator(this);
        var spellsGenerator = new SpellsGenerator(this);

        this.generators = new HashMap<>();
        this.generators.put("gotouser", userGenerator);

        this.generators.put("addcharacter", userGenerator);
        this.generators.put("addfeature", featuresGenerator);
        this.generators.put("additem", itemsGenerator);
        this.generators.put("addpossession", possessionsGenerator);
        this.generators.put("addgrade", gradesGenerator);
        this.generators.put("addspell", spellsGenerator);

        this.generators.put("gotocharacter", this.characterGenerator);

        this.generators.put("gotodescription", new DescriptionGenerator());
        this.generators.put("gotoinventory", new InventoryGenerator());
        this.generators.put("gotospells", spellsGenerator);
        this.generators.put("gotonotes", new NotesGenerator());

        this.generators.put("gotopersonality", new PersonalityGenerator(this));
        this.generators.put("gotohp", new HPGenerator(this));
        this.generators.put("gotolevel", new LevelGenerator(this));
        this.generators.put("gotosocial", new SocialGenerator(this));
        this.generators.put("gotoitems", itemsGenerator);

        this.generators.put("gotofeatures", featuresGenerator);
        this.generators.put("gotopossessions", possessionsGenerator);

        this.generators.put("gotostats", new StatsGenerator(this));
        this.generators.put("gotoappearance", new AppearanceGenerator(this));
    }

    public BaseGenerator getGeneratorByStateName(String stateName) {
        return this.generators.get(stateName);
    }

    public ReposStorage getReposStorage() {
        return this.reposStorage;
    }
}
