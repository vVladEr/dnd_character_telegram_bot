package dnd.bot.maven.eclipse;

import dnd.bot.maven.eclipse.Routing.Router;
import dnd.bot.maven.eclipse.Routing.States.GeneralState;
import dnd.bot.maven.eclipse.Routing.States.UserState;
import dnd.bot.maven.eclipse.db.Services.ReposStorage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class RouterTest {
    private Router router;
    private ReposStorage storage;

    @BeforeEach
    void setUp() {
        this.storage = new ReposStorage();
        this.router = new Router("testUserId");        
    }

    @Test
    void testInitialState() {
        assertNotNull(this.router.getCurrentState());
        assertTrue(this.router.getCurrentState() instanceof UserState);
    }

    @Test
    void testMakeTransitionForSimpleCallback() {
        router.makeTransition("add");

        assertTrue(this.router.getCurrentState() instanceof UserState);
    }

    @Test
    void testMakeTransitionForCompositeCallback() {
        this.router.makeTransition("add");

        assertTrue(this.router.getCurrentState() instanceof UserState);

        var characters = this.storage.getCharacterRepository().getUserCharacters("testUserId");

        this.router.makeTransition(
            String.format("gotocharacter:%s", 
            characters.get(0).id)
        );

        assertTrue(this.router.getCurrentState() instanceof GeneralState);
    }

    @Test
    void testMakeTransitionWithUnknownCallback() {
        this.router.makeTransition("unknowncallback");

        assertTrue(this.router.getCurrentState() instanceof UserState);
    }
}
