package dnd.bot.maven.eclipse;

import dnd.bot.maven.eclipse.User.Character.BasicDescription.BasicDescription;
import dnd.bot.maven.eclipse.User.Character.Character;
import dnd.bot.maven.eclipse.User.Character.Description.Description;
import dnd.bot.maven.eclipse.User.Character.Description.Features.Features;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.Appearance.Appearance;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.HP.HP;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.Level.Level;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.Personality;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.Social.Social;
import dnd.bot.maven.eclipse.User.Character.Grade.Grade;
import dnd.bot.maven.eclipse.User.Character.Grade.Spells.Spells;
import dnd.bot.maven.eclipse.User.Character.Inventory.Inventory;
import dnd.bot.maven.eclipse.User.Character.Inventory.Items.Item.Bread;
import dnd.bot.maven.eclipse.User.Character.Inventory.Items.Items;
import dnd.bot.maven.eclipse.User.Character.Inventory.Money.Money;
import dnd.bot.maven.eclipse.User.Character.Notes.Notes;
import dnd.bot.maven.eclipse.User.Character.Stats.GeneralStat.PowerStat;
import dnd.bot.maven.eclipse.User.Character.Stats.Stats;

import java.util.ArrayList;
import java.util.HashMap;

public class Mocks {
    public static Character[] characters = new Character[2];

    static {
        // пустой персонаж
        var c = new Character();
        c.stats = new Stats();
        c.stats.generalStats = new ArrayList<>();
        c.stats.generalStats.add(new PowerStat());
        c.description = new Description();
        c.description.personality = new Personality();
        c.description.personality.appearance = new Appearance();
        c.description.personality.appearance.personality = new BasicDescription();
        c.description.personality.appearance.allies = new BasicDescription();
        c.description.personality.appearance.bonds = new BasicDescription();
        c.description.personality.appearance.flaws = new BasicDescription();
        c.description.personality.appearance.characterBackground = new BasicDescription();
        c.description.personality.appearance.ideals = new BasicDescription();
        c.description.personality.hp = new HP();
        c.description.personality.level = new Level();
        c.description.personality.social = new Social();
        c.description.features = new Features();
        c.description.features.features = new ArrayList<>();
        c.description.possessions = new HashMap<>();
        c.inventory = new Inventory();
        c.inventory.items = new Items();
        c.inventory.items.items = new ArrayList<>();
        c.inventory.items.items.add(new Bread());
        c.inventory.money = new Money();
        c.grades = new Grade();
        c.grades.spells = new Spells();
        c.grades.spells.spells = new ArrayList<>();
        c.notes = new Notes();
        characters[0] = c;

        // заполненный персонаж
        c = new Character();
        c.name = "Миша";
        c.stats = new Stats();
        c.stats.generalStats = new ArrayList<>();
        c.stats.generalStats.add(new PowerStat());
        c.description = new Description();
        c.description.personality = new Personality();
        c.description.personality.appearance = new Appearance();
        c.description.personality.appearance.age = 24;
        c.description.personality.appearance.personality = new BasicDescription();
        c.description.personality.appearance.personality.name = "..";
        c.description.personality.appearance.personality.description = "..";
        c.description.personality.appearance.alignment = "...";
        c.description.personality.appearance.allies = new BasicDescription();
        c.description.personality.appearance.allies.name = "..";
        c.description.personality.appearance.allies.description = "..";
        c.description.personality.appearance.backGround = "...";
        c.description.personality.appearance.bonds = new BasicDescription();
        c.description.personality.appearance.bonds.name = "..";
        c.description.personality.appearance.bonds.description = "..";
        c.description.personality.appearance.flaws = new BasicDescription();
        c.description.personality.appearance.flaws.name = "..";
        c.description.personality.appearance.flaws.description = "..";
        c.description.personality.appearance.characterBackground = new BasicDescription();
        c.description.personality.appearance.characterBackground.name = "..";
        c.description.personality.appearance.characterBackground.description = "..";
        c.description.personality.appearance.ideals = new BasicDescription();
        c.description.personality.appearance.ideals.name = "..";
        c.description.personality.appearance.ideals.description = "..";
        c.description.personality.appearance.height = 180;
        c.description.personality.appearance.weight = 71;
        c.description.personality.armor = 10;
        c.description.personality.hp = new HP();
        c.description.personality.hp.tempHP = 53;
        c.description.personality.hp.bonusMaxHP = 20;
        c.description.personality.hp.maxHP = 100;
        c.description.personality.hp.hpDice = "k6";
        c.description.personality.exhaustion = 3;
        c.description.personality.level = new Level();
        c.description.personality.level.currentLevel = 1;
        c.description.personality.level.currentExp = 23;
        c.description.personality.level.necessaryExp = 40;
        c.description.personality.inspiration = true;
        c.description.personality.possessionBonus = 4;
        c.description.personality.social = new Social();
        c.description.personality.social.characterName = "Миша";
        c.description.personality.social.characterClass = "воин";
        c.description.personality.social.race = "race";
        c.description.personality.speed = 5;
        c.description.features = new Features();//
        c.description.features.features = new ArrayList<>();
        c.description.possessions = new HashMap<>();//
        c.inventory = new Inventory();
        c.inventory.items = new Items();
        c.inventory.items.items = new ArrayList<>();
        c.inventory.items.items.add(new Bread());
        c.inventory.money = new Money();
        c.inventory.money.money = 9875;
        c.grades = new Grade();
        c.grades.count = 5;
        c.grades.maxCount = 9;
        c.grades.spells = new Spells();
        c.grades.spells.spells = new ArrayList<>();
        var bd = new BasicDescription();
        bd.name = "имя";
        bd.description = "описание";
        c.grades.spells.spells.add(bd);
        c.notes = new Notes();
        var bd1 = new BasicDescription();
        bd1.name = "заметка";
        bd1.description = "описание";
        c.notes.notes.add(bd1);
        characters[1] = c;
    }
}
