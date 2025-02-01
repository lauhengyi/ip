package laura;

import laura.exception.LauraException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateTest {
    @Test
    public void encode_validDate_success() throws LauraException {
        // Using valid dates, their encoding should be the same dates
        assertEquals("13/04/2002", new Date("13/04/2002").encode());

        assertEquals("14/02/2018", new Date("14/02/2018").encode());

        assertEquals("02/12/2020", new Date("02/12/2020").encode());
    }

    @Test
    public void toString_validDate_success() throws LauraException {
        // Using valid dates, their encoding should be the beautified string
        assertEquals("13 April 2002", new Date("13/04/2002").toString());

        assertEquals("14 February 2018", new Date("14/02/2018").toString());

        assertEquals("02 December 2020", new Date("02/12/2020").toString());
    }

    @Test
    public void constructor_invalidDate_exceptionThrown() {
        // Using invalid dates, a Laura exception should be thrown
        assertThrows(LauraException.class,() -> new Date("2/04/2002"));

        assertThrows(LauraException.class,() -> new Date("13-04-2002"));

        assertThrows(LauraException.class,() -> new Date("343/04/2002"));

        assertThrows(LauraException.class,() -> new Date("2002/13/04"));

        assertThrows(LauraException.class,() -> new Date("asdfasdf"));
    }
}
