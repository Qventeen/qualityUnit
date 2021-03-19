package programmertasken.handlers.record;

import programmertasken.entity.Box;
import programmertasken.entity.RecordEntity;
import programmertasken.handlers.BaseHandler;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Question handler for record
 */
public class RecordQuestionHandler extends BaseHandler<RecordEntity, Box> {
    private final Pattern pattern;
    private final int SUBCATEGORY_INDEX = 2;
    private final int CATEGORY_INDEX = 1;
    private final int QUESTION_INDEX = 0;

    public RecordQuestionHandler(Pattern pattern) {
        Objects.requireNonNull(pattern);
        this.pattern = pattern;
    }

    @Override
    public boolean handle(RecordEntity entity, Box box) {
        try {
            String[] serviceStrings = pattern.split(box.next());

            switch (serviceStrings.length - 1) {
                case SUBCATEGORY_INDEX:
                    entity.setSubcategory(Integer.parseInt(serviceStrings[SUBCATEGORY_INDEX]));
                case CATEGORY_INDEX:
                    entity.setCategory(Integer.parseInt(serviceStrings[CATEGORY_INDEX]));
                default:
                    entity.setQuestionType(Integer.parseInt(serviceStrings[QUESTION_INDEX]));
            }
        } catch (Exception e) {
            return false;
        }

        return handleNext(entity, box);

    }
}
