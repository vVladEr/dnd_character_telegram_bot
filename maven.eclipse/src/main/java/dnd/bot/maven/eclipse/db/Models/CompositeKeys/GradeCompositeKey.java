package dnd.bot.maven.eclipse.db.Models.CompositeKeys;

import org.bson.types.ObjectId;

public class GradeCompositeKey {

    public final ObjectId characterId;
    
    public final int grade;

    public GradeCompositeKey(ObjectId characterId, int grade)
    {
        this.characterId = characterId;
        this.grade = grade;
    }
}
