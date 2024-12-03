package dnd.bot.maven.eclipse.db.Models.CompositeKeys;

import org.bson.types.ObjectId;

import dnd.bot.maven.eclipse.db.Models.StatNameEnum;

public class Combinekey {

    private final GradeCompositeKey gradeCompositeKey;

    private final StatCompositeKey statCompositeKey;

    private final ObjectId objectIdKey;

    private final String userIdKey;

    public Combinekey(ObjectId characterId, int gradeNumber)
    {
        gradeCompositeKey = new GradeCompositeKey(characterId, gradeNumber);
        statCompositeKey = null;
        objectIdKey = null;
        userIdKey = null;
    }

    public Combinekey(String userId, ObjectId characterId, int gradeNumber)
    {
        gradeCompositeKey = new GradeCompositeKey(characterId, gradeNumber);
        statCompositeKey = null;
        objectIdKey = characterId;
        userIdKey = userId;
    }
    
    public Combinekey(ObjectId characterId, StatNameEnum statName)
    {
        gradeCompositeKey = null;
        statCompositeKey = new StatCompositeKey(characterId, statName);
        objectIdKey = null;
        userIdKey = null;
    }

    public Combinekey(ObjectId objectId)
    {
        gradeCompositeKey = null;
        statCompositeKey = null;
        objectIdKey = objectId;
        userIdKey = null;
    }

    public Combinekey(String userId)
    {
        gradeCompositeKey = null;
        statCompositeKey = null;
        objectIdKey = null;
        userIdKey = userId;
    }

    public Combinekey(String userId, ObjectId objectId)
    {
        gradeCompositeKey = null;
        statCompositeKey = null;
        objectIdKey = objectId;
        userIdKey = userId;
    }

    public GradeCompositeKey getGradeCompositeKey() {
        assert gradeCompositeKey != null;

        return gradeCompositeKey;
    }

    public StatCompositeKey getStatCompositeKey() {
        assert statCompositeKey != null;

        return statCompositeKey;
    }

    public ObjectId getObjectIdKey() {
        assert objectIdKey != null;

        return objectIdKey;
    }

    public String getUserIdKey() {
        assert userIdKey != null;

        return userIdKey;
    }
}
