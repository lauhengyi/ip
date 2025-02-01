package laura.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTaskTest {
    @Test
    public void mark_success() {
        // When unmarked, mark will mark it
        ToDoTask test = new ToDoTask("This is a test");
        assertEquals("[T][ ] This is a test", test.toString());
        test.mark();
        assertEquals("[T][X] This is a test", test.toString());
    }

    @Test
    void unmark_success() {
        // When marked, unmark will unmark it
        ToDoTask test = new ToDoTask(true, "This is a test");
        assertEquals("[T][X] This is a test", test.toString());
        test.unmark();
        assertEquals("[T][ ] This is a test", test.toString());
    }

    @Test
    void encode_success() {
        // When encode is called, it should provide the correct encoding
        assertEquals("T|0|Test1", new ToDoTask("Test1").encode());

        // Encoding should include correct marking
        ToDoTask test2 = new ToDoTask("Test2");
        test2.mark();
        assertEquals("T|1|Test2", test2.encode());
        test2.unmark();
        assertEquals("T|0|Test2", test2.encode());

        // Should provide correct marking when first constructed
        assertEquals("T|1|Test3", new ToDoTask(true, "Test3").encode());
    }

}
