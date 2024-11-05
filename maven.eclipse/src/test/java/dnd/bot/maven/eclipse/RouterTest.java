package dnd.bot.maven.eclipse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.Router;
import dnd.bot.maven.eclipse.Routing.State;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

public class RouterTest {

    private Router router;
    private TestState state1;
    private TestState state2;

    @BeforeEach
    public void setUp() {
        router = new Router();
        state1 = new TestState();
        state2 = new TestState();

        state1.possibleTransitions.put("goToState2", state2);
        state2.possibleTransitions.put("goToState1", state1);

        router.addState(state1);
    }

    @Test
    public void testGetCurrentState() {
        assertEquals(state1, router.getCurrentState());
    }

    @Test
    public void testMakeTransitionValid() {
        router.makeTransition("goToState2");
        assertEquals(state2, router.getCurrentState());

        router.makeTransition("goToState1");
        assertEquals(state1, router.getCurrentState());
    }

    @Test
    public void testMakeTransitionBack() {
        router.makeTransition("goToState2");
        assertEquals(state2, router.getCurrentState());

        router.makeTransition("back");
        assertEquals(state1, router.getCurrentState());
    }

    private class TestState extends State {
        public TestState() {
            possibleTransitions = new HashMap<>();
        }

        @Override
        public ResponseObject getStateMessages() {
            return null;
        }
    }
}
