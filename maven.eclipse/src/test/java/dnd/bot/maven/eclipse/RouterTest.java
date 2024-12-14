package dnd.bot.maven.eclipse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import dnd.bot.maven.eclipse.Routing.Router;
import dnd.bot.maven.eclipse.Routing.States.AddState;
import dnd.bot.maven.eclipse.Routing.States.GeneralState;
import dnd.bot.maven.eclipse.Routing.States.NotesState;
import dnd.bot.maven.eclipse.Routing.States.UserState;
import dnd.bot.maven.eclipse.db.Services.ReposStorage;

public class RouterTest {
    private Router router;
    private ReposStorage storage;

    @BeforeEach
    void setUp() {
        storage = new ReposStorage();
        router = new Router("testUserId");        
    }

    @Test
    void testInitialState() {
        assertNotNull(router.getCurrentState());
        assertTrue(router.getCurrentState() instanceof UserState);
    }

    @Test
    void testAddCharacter() {
        router.makeTransition("add");

        assertTrue(router.getCurrentState() instanceof AddState);

        router.makeTransition("Vasya");

        assertTrue(router.getCurrentState() instanceof UserState);
        assertTrue(storage.getCharacterRepository().getUserCharacters("testUserId").get(0).name.equals("Vasya"));
    }

    @Test
    void testMakeTransitionForCompositeCallback() {
        var id = storage.getCharacterRepository().getUserCharacters("testUserId").get(0).id;
        router.makeTransition(String.format("gotocharacter:%s", id));

        assertTrue(router.getCurrentState() instanceof GeneralState);
    }

    @Test
    void testMakeTransitionForSimpleCallback() {
        var id = storage.getCharacterRepository().getUserCharacters("testUserId").get(0).id;
        router.makeTransition(String.format("gotocharacter:%s", id));

        assertTrue(router.getCurrentState() instanceof GeneralState);

        router.makeTransition("gotonotes");

        assertTrue(router.getCurrentState() instanceof NotesState);
    }

    @Test
    void testAddNote() {
        var id = storage.getCharacterRepository().getUserCharacters("testUserId").get(0).id;
        router.makeTransition(String.format("gotocharacter:%s", id));

        assertTrue(router.getCurrentState() instanceof GeneralState);
        
        router.makeTransition("gotonotes");

        assertTrue(router.getCurrentState() instanceof NotesState);

        router.makeTransition("add");

        assertTrue(router.getCurrentState() instanceof AddState);

        router.makeTransition("first trip");
        router.makeTransition("problems");

        var newNote = storage.getNotesRepository().getCharacterNotes(id).get(0);

        assertTrue(newNote.name.equals("first trip"));
        assertTrue(newNote.description.equals("problems"));

        assertTrue(router.getCurrentState() instanceof NotesState);

    }

    @Test
    void testMakeBackTransition() {
        var id = storage.getCharacterRepository().getUserCharacters("testUserId").get(0).id;
        router.makeTransition(String.format("gotocharacter:%s", id));

        assertTrue(router.getCurrentState() instanceof GeneralState);
        
        router.makeTransition("gotonotes");

        assertTrue(router.getCurrentState() instanceof NotesState);

        router.makeTransition("gotocharacter");

        assertTrue(router.getCurrentState() instanceof GeneralState);

        router.makeTransition("gotouser");

        assertTrue(router.getCurrentState() instanceof UserState);
    }

    @Test
    void testMakeTransitionWithUnknownCallback() {
        router.makeTransition("unknowncallback");

        assertTrue(router.getCurrentState() instanceof UserState);
    }
}
