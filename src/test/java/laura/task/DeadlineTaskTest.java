package laura.task;

import laura.exception.LauraException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTaskTest {
    @Test
    public void constructor_validDate_success() throws LauraException {
        // When given a valid date, will return a unmarked task with the written dates
        assertEquals("[D][ ] This is a test (by: 13 April 2002)",
                new DeadlineTask("This is a test", "13/04/2002").toString());

        assertEquals("[D][ ] This is a test (by: 05 March 2018)",
                new DeadlineTask("This is a test", "05/03/2018").toString());

        assertEquals("[D][ ] This is a test (by: 18 December 2000)",
                new DeadlineTask("This is a test", "18/12/2000").toString());
    }

    @Test
    public void constructor_invalidDate_exceptionThrown() {
        // When given a valid date, will return an unmarked task with the written dates
        assertThrows(LauraException.class, () ->
                new DeadlineTask("This is a test", "13/13/2002"));

        assertThrows(LauraException.class, () ->
                new DeadlineTask("This is a test", "13/04/02"));

        assertThrows(LauraException.class, () ->
                new DeadlineTask("This is a test", "13-04-2002"));

        assertThrows(LauraException.class, () ->
                new DeadlineTask("This is a test", "asdfjkasdfjk"));
    }

    @Test
    public void mark_success() throws LauraException {
        // When unmarked, mark will mark it
        DeadlineTask test = new DeadlineTask("This is a test", "13/04/2002");
        assertEquals("[D][ ] This is a test (by: 13 April 2002)", test.toString());
        test.mark();
        assertEquals("[D][X] This is a test (by: 13 April 2002)", test.toString());
    }

    @Test
    void unmark_success() throws LauraException{
        // When marked, unmark will unmark it
        DeadlineTask test = new DeadlineTask(true, "This is a test", "13/04/2002");
        assertEquals("[D][X] This is a test (by: 13 April 2002)", test.toString());
        test.unmark();
        assertEquals("[D][ ] This is a test (by: 13 April 2002)", test.toString());
    }

    @Test
    void encode_success() throws LauraException{
        // When encode is called, it should provide the correct encoding
        assertEquals("D|0|Test1|13/04/2002", new DeadlineTask("Test1", "13/04/2002").encode());

        // Encoding should include correct marking
        DeadlineTask test2 = new DeadlineTask("Test2", "13/04/2002");
        test2.mark();
        assertEquals("D|1|Test2|13/04/2002", test2.encode());
        test2.unmark();
        assertEquals("D|0|Test2|13/04/2002", test2.encode());

        // Should provide correct marking when first constructed
        assertEquals("D|1|Test3|13/04/2002", new DeadlineTask(true, "Test3", "13/04/2002").encode());
    }
}
