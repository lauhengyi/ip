package laura.task;

import laura.exception.LauraException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTaskTest {
    @Test
    public void constructor_validDate_success() throws LauraException {
        // When given a valid date, will return a unmarked task with the written dates
        assertEquals("[E][ ] This is a test (from: 13 April 2002 to: 17 May 2002)",
                new EventTask("This is a test", "13/04/2002", "17/05/2002").toString());

        assertEquals("[E][ ] This is a test (from: 13 April 2002 to: 01 February 2025)",
                new EventTask("This is a test", "13/04/2002", "01/02/2025").toString());

        assertEquals("[E][ ] This is a test (from: 17 May 2002 to: 17 August 2025)",
                new EventTask("This is a test", "17/05/2002", "17/08/2025").toString());
    }

    @Test
    public void constructor_invalidDate_exceptionThrown() {
        // When given a valid date, will return an unmarked task with the written dates
        assertThrows(LauraException.class, () ->
                new EventTask("This is a test", "13/13/2002", "13/04/2004"));

        assertThrows(LauraException.class, () ->
                new EventTask("This is a test", "13/04/02", "13/02/2004"));

        assertThrows(LauraException.class, () ->
                new EventTask("This is a test", "13-04-2002", "13/02/2004"));

        assertThrows(LauraException.class, () ->
                new EventTask("This is a test", "asdfjkasdfjk", "13/04/2004"));

        assertThrows(LauraException.class, () ->
                new EventTask("This is a test", "13/04/2004", "13-04-2004"));
    }


    @Test
    public void mark_success() throws LauraException {
        // When unmarked, mark will mark it
        EventTask test = new EventTask("This is a test", "13/04/2002", "15/05/2004");
        assertEquals("[E][ ] This is a test (from: 13 April 2002 to: 15 May 2004)", test.toString());
        test.mark();
        assertEquals("[E][X] This is a test (from: 13 April 2002 to: 15 May 2004)", test.toString());
    }

    @Test
    void unmark_success() throws LauraException{
        // When marked, unmark will unmark it
        EventTask test = new EventTask(true, "This is a test", "13/04/2002", "15/05/2004");
        assertEquals("[E][X] This is a test (from: 13 April 2002 to: 15 May 2004)", test.toString());
        test.unmark();
        assertEquals("[E][ ] This is a test (from: 13 April 2002 to: 15 May 2004)", test.toString());
    }

    @Test
    void encode_success() throws LauraException{
        // When encode is called, it should provide the correct encoding
        assertEquals("E|0|Test1|13/04/2002|15/05/2004", new EventTask("Test1", "13/04/2002", "15/05/2004").encode());

        // Encoding should include correct marking
        EventTask test2 = new EventTask("Test2", "13/04/2002", "15/05/2004");
        test2.mark();
        assertEquals("E|1|Test2|13/04/2002|15/05/2004", test2.encode());
        test2.unmark();
        assertEquals("E|0|Test2|13/04/2002|15/05/2004", test2.encode());

        // Should provide correct marking when first constructed
        assertEquals("E|1|Test3|13/04/2002|15/05/2004", new EventTask(true, "Test3", "13/04/2002", "15/05/2004").encode());
    }

}
