package laura;

import laura.exception.LauraException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that deals with formatting of dates
 */
public class Date {
    private final LocalDate date;
    /** The format that the date should be written in string */
    private static final DateTimeFormatter STRING_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Creates a Date instance
     *
     * @param dateString The Date string in the specified format
     * @throws LauraException If dateString is not in the specified format
     */
    public Date(String dateString) throws LauraException {
        try {
            this.date = LocalDate.parse(dateString, stringFormat);
        } catch (DateTimeException e) {
            throw new LauraException("There is a problem parsing your date, make sure your date is in the format dd/mm/yyyy");
        }
    }

    /**
     *
     * @return The format of the date for data encoding
     */
    public String encode() {
        return this.date.format(stringFormat);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return this.date.format(formatter);
    }
}
