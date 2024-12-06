package dnd.bot.maven.eclipse.db.repos.Interfaces;

public interface FieldUpdatable<TKey> {
    public void updateField(TKey id, String fieldName, Object newFieldValue);
}