package programmertasken.handlers.query;

import programmertasken.entity.Box;
import programmertasken.entity.QueryEntity;
import programmertasken.handlers.BaseHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Dates handler for query
 */
public class QueryDateHandler extends BaseHandler<QueryEntity, Box> {
    private final DateTimeFormatter FORMATTER;
    private final Pattern PATTERN;
    private final int FROM_DATE = 0;
    private final int TO_DATE = 1;


    public QueryDateHandler(DateTimeFormatter formatter, Pattern pattern) {
        Objects.requireNonNull(formatter);
        this.FORMATTER = formatter;
        Objects.requireNonNull(pattern);
        this.PATTERN = pattern;
    }

    @Override
    public boolean handle(QueryEntity entity, Box box) {
        try {
            String[] dates = PATTERN.split(box.next());
            switch (dates.length - 1) {
                case TO_DATE:
                    entity.setTo(LocalDate.parse(dates[TO_DATE], FORMATTER));
                case FROM_DATE:
                    entity.setFrom(LocalDate.parse(dates[FROM_DATE], FORMATTER));
            }
        } catch (Exception e) {
            return false;
        }

        return handleNext(entity, box);
    }
}
