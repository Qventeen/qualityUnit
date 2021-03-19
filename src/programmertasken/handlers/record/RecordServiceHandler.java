package programmertasken.handlers.record;

import programmertasken.entity.Box;
import programmertasken.entity.RecordEntity;
import programmertasken.handlers.BaseHandler;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Service handler for record
 */
public class RecordServiceHandler extends BaseHandler<RecordEntity, Box> {
    private final Pattern pattern;
    private final int VARIATION_INDEX = 1;
    private final int SERVICE_INDEX = 0;

    public RecordServiceHandler(Pattern pattern) {
        Objects.requireNonNull(pattern);
        this.pattern = pattern;
    }

    @Override
    public boolean handle(RecordEntity entity, Box box) {
        try {
            String[] serviceStrings = pattern.split(box.next());

            switch (serviceStrings.length - 1) {
                case VARIATION_INDEX:
                    entity.setVariation(Integer.parseInt(serviceStrings[VARIATION_INDEX]));
                default:
                    entity.setService(Integer.parseInt(serviceStrings[SERVICE_INDEX]));
            }
        } catch (Exception e) {
            return false;
        }

        return handleNext(entity, box);
    }
}
