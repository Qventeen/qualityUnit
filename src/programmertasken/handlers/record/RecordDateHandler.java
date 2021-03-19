package programmertasken.handlers.record;

import programmertasken.entity.Box;
import programmertasken.entity.RecordEntity;
import programmertasken.handlers.BaseHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Date handler for record
 */
public class RecordDateHandler extends BaseHandler<RecordEntity, Box> {
    private final DateTimeFormatter FORMATTER;

    public RecordDateHandler(DateTimeFormatter formatter) {
        Objects.requireNonNull(formatter);
        this.FORMATTER = formatter;
    }

    @Override
    public boolean handle(RecordEntity entity, Box box) {
        try {
            LocalDate date = LocalDate.parse(box.next(), FORMATTER);
            entity.setDate(date);
        } catch (Exception e) {
            return false;
        }
        return handleNext(entity, box);
    }
}
