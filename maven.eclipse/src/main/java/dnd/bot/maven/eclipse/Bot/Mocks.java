// package dnd.bot.maven.eclipse.Bot;

// import dnd.bot.maven.eclipse.User.Character.Character;
// import dnd.bot.maven.eclipse.User.Character.Description.Description;
// import dnd.bot.maven.eclipse.User.Character.Description.Features.Features;
// import dnd.bot.maven.eclipse.User.Character.Description.Personality.Appearance.Appearance;
// import dnd.bot.maven.eclipse.User.Character.Description.Personality.HP.HP;
// import dnd.bot.maven.eclipse.User.Character.Description.Personality.Level.Level;
// import dnd.bot.maven.eclipse.User.Character.Description.Personality.Personality;
// import dnd.bot.maven.eclipse.User.Character.Description.Personality.Social.Social;
// import dnd.bot.maven.eclipse.User.Character.Description.Possessions.Possessions;
// import dnd.bot.maven.eclipse.User.Character.Grade.Grade;
// import dnd.bot.maven.eclipse.User.Character.Grade.Spells.Spells;
// import dnd.bot.maven.eclipse.User.Character.Inventory.Inventory;
// import dnd.bot.maven.eclipse.User.Character.Inventory.Items.Item.Item;
// import dnd.bot.maven.eclipse.User.Character.Inventory.Items.Items;
// import dnd.bot.maven.eclipse.User.Character.Inventory.Money.Money;
// import dnd.bot.maven.eclipse.User.Character.Notes.Notes;
// import dnd.bot.maven.eclipse.User.Character.Stats.Stats;
// import dnd.bot.maven.eclipse.User.Character.Stats.Stat.Stat;
// import dnd.bot.maven.eclipse.User.Character.Stats.Stat.Skills.Skill;
// import dnd.bot.maven.eclipse.User.Character.Stats.Stat.Skills.Knowledge.KnowledgeLevel;

// import java.util.ArrayList;

// public class Mocks {
//     public static Character[] characters = new Character[1];

//     static {
//         // Level
//         var level = new Level();
//         level.currentExp = 100;
//         level.currentLevel = 1;
//         level.necessaryExp = 500;

//         // Social
//         var social = new Social();
//         social.characterClass = "Воин";
//         social.race = "Человек";

//         // HP
//         var hp = new HP();
//         hp.bonusMaxHP = 5;
//         hp.hpDice = "К4";
//         hp.maxHP = 30;
//         hp.tempHP = 0;

//         // Appearance
//         var appearance = new Appearance();
//         appearance.backGround = "Артист";
//         appearance.alignment = "ХЗ";
//         appearance.height = 180;
//         appearance.age = 30;
//         appearance.weight = 90;
//         appearance.characterBackground = "Жил да был";
//         appearance.allies = "Арфисты";
//         appearance.ideals = "Хороший парень";
//         appearance.personality = "Агрится";
//         appearance.bonds = "Цветы";
//         appearance.flaws = "Пчелы";

//         // Personality
//         var personality = new Personality(hp, level, appearance, social);
//         personality.armor = 18;
//         personality.exhaustion = 0;
//         personality.inspiration = false;
//         personality.speed = 30;
//         personality.possessionBonus = 2;
        
//         // Features
//         var features = new Features();
//         features.features.add("Темное зрение");
//         features.features.add("Могучий взгляд");

//         // Possessions
//         var possessions = new Possessions();
//         var possession = new  ArrayList<String>();
//         possession.add("Легкая");
//         possession.add("Тяжелая");
//         possessions.possessions.put("Броня", possession);

//         // Discription 
//         var description = new Description(personality, features, possessions);

//         // Skill
//         var firstSkill = new Skill();
//         firstSkill.name = "Атлетика";
//         firstSkill.totalBonus = 2;
//         firstSkill.knowledgeLevel = KnowledgeLevel.INTERMEDIATE;

//         var secondSkill = new Skill();
//         secondSkill.name = "Ловкость рук";
//         secondSkill.totalBonus = -1;
//         secondSkill.knowledgeLevel = KnowledgeLevel.BASIC;

//         // Stat
//         var powerStat = new Stat();
//         powerStat.name = "Сила";
//         powerStat.checkBonus = 2;
//         powerStat.saveThrowBonus = 2;
//         powerStat.value = 14;
//         powerStat.skills.add(firstSkill);
//         powerStat.possibleTransitions.put(firstSkill.name, firstSkill);

//         var agilityStat = new Stat();
//         agilityStat.name = "Ловкость";
//         agilityStat.checkBonus = -1;
//         agilityStat.saveThrowBonus = -1;
//         agilityStat.value = 8;
//         agilityStat.skills.add(secondSkill);
//         agilityStat.possibleTransitions.put(secondSkill.name, secondSkill);

//         // Stats
//         var stats = new Stats();
//         stats.stats.add(powerStat);
//         stats.stats.add(agilityStat);
//         stats.possibleTransitions.put(powerStat.name, powerStat);
//         stats.possibleTransitions.put(agilityStat.name, agilityStat);

//         // Item
//         var item = new Item();
//         item.category = "Пища";
//         item.name = "Хлеб";
//         item.amount = 2;
//         item.description = "Вкусный и питательный";
//         item.isMagic = false;
//         item.isEquiped = false;
//         item.weight = 1;

//         // Items
//         var items = new Items();
//         items.items.add(item);
//         items.possibleTransitions.put(item.name, item);

//         // Money
//         var money = new Money();
//         money.money = 47569356;

//         // Inventory
//         var inventory = new Inventory(items, money);

//         // Spells
//         var spells = new Spells();
//         spells.spells.add("Огненный шар");
//         spells.spells.add("Жуткий смех Таши");

//         // Grade
//         var grade = new Grade(spells);
//         grade.count = 3;
//         grade.maxCount = 4;

//         // Notes
//         var notes = new Notes();
//         notes.notes.add("Мы побывали в Невервинтере");
//         notes.notes.add("Я видел, как плут украл у товарища 20 золотых");

//         // Character
//         var character = new Character(stats, description, inventory, grade, notes);
//         character.name = "Брустон";

//         characters[0] = character;
//     }
// }
