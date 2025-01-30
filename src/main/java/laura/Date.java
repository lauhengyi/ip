package laura;

import laura.exception.LauraException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    private final LocalDate date;
    private static final DateTimeFormatter stringFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Date(String dateString) throws LauraException {
        try {
            this.date = LocalDate.parse(dateString, stringFormat);
        } catch (DateTimeException e) {
            throw new LauraException("There is a problem parsing your date, make sure your date is in the format dd/mm/yyyy");
        }
    }

    public String encode() {
        return this.date.format(stringFormat);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return this.date.format(formatter);
    }
}
