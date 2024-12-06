package dnd.bot.maven.eclipse.db.repos.Interfaces;

public interface InnerFieldUpdatable<TKey> extends FieldUpdatable<TKey> {
    public void updateInnerField(TKey key, String fieldName, String innerFieldName, Object newValue);
}
