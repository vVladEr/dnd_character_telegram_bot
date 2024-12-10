package dnd.bot.maven.eclipse.db.repos.Interfaces;

public interface DocumentDeletable<KeyType> {
    public void deleteDocument(KeyType id);
}
