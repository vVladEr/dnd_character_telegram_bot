package dnd.bot.maven.eclipse.User.Character.Stats.GeneralStat;

import dnd.bot.maven.eclipse.User.Character.Stats.GeneralStat.Skills.Skill;

import java.util.ArrayList;
import java.util.List;

public class PowerStat extends GeneralStat {
    public String name = "Сила";
    public int value = 2;
    public List<Skill> skills = new ArrayList<Skill>();
    public int checkBonus = 5;
    public int saveThrowBonus = 3;
}
