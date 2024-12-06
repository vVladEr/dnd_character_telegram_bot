package dnd.bot.maven.eclipse.db.Services;

import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.db.Models.StatNameEnum;
import dnd.bot.maven.eclipse.db.Models.dbo.SkillDbo;

public final class SkillFactory {

    public static LinkedHashMap<String, SkillDbo> getSkillsByStatName(StatNameEnum statName) {
        var skills = new LinkedHashMap<String, SkillDbo>();
        switch (statName) {
            case STRENGTH:
                skills.put("athletics", new SkillDbo("athletics"));
                break;

            case CONSTITUTION:
                break;

            case INTELLIGENCE:
                skills.put("arcana", new SkillDbo("arcana"));
                skills.put("history", new SkillDbo("history"));
                skills.put("investigation", new SkillDbo("investigation"));
                skills.put("nature", new SkillDbo("nature"));
                skills.put("religion", new SkillDbo("religion"));
                break;

            case WISDOM:
                skills.put("animal handling", new SkillDbo("animal handling"));
                skills.put("insight", new SkillDbo("insight"));
                skills.put("medicine", new SkillDbo("medicine"));
                skills.put("perception", new SkillDbo("perception"));
                skills.put("survival", new SkillDbo("survival"));
                break;

            case CHARISMA:
                skills.put("deception", new SkillDbo("deceptoion"));
                skills.put("intimidation", new SkillDbo("intimidation"));
                skills.put("performance", new SkillDbo("performance"));
                skills.put("persuasion", new SkillDbo("persuasion"));
                break;

            case DEXTERITY:
                skills.put("acrobatics", new SkillDbo("acrobatics"));
                skills.put("sleight of hand", new SkillDbo("sleight of hand"));
                skills.put("stealth", new SkillDbo("stealth"));
                break;

            default:
                throw new UnsupportedOperationException();
        }
        return skills;
    }
}
