package dnd.bot.maven.eclipse.db.Models.dbo;

import org.bson.codecs.pojo.annotations.BsonProperty;

import dnd.bot.maven.eclipse.User.Character.Stats.Stat.Skills.Knowledge.KnowledgeLevel;



public class SkillDbo {

    @BsonProperty
    public String name;

    @BsonProperty
    public int totalBonuses;
    
    @BsonProperty 
    public KnowledgeLevel knowledgeLevel;

    public SkillDbo(String skillName)
    {
        name = skillName;
        knowledgeLevel = KnowledgeLevel.BASIC;
    }

    public SkillDbo(){}
}
