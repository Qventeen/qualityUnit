package programmertasken.handlers.query;

import programmertasken.entity.Box;
import programmertasken.entity.QueryEntity;
import programmertasken.handlers.BaseHandler;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Question handler for query
 */
public class QueryQuestionHandler extends BaseHandler<QueryEntity, Box> {
    private final Pattern pattern;
    private final int SUBCATEGORY = 2;
    private final int CATEGORY = 1;
    private final int QUESTION = 0;
    private final String WILDCARD;


    public QueryQuestionHandler(Pattern pattern, String wildcard) {
        Objects.requireNonNull(pattern);
        this.pattern = pattern;
        Objects.requireNonNull(wildcard);
        this.WILDCARD = wildcard;
    }

    @Override
    public boolean handle(QueryEntity entity, Box box) {
        try {
            String[] serviceStrings = pattern.split(box.next());

            //Wildcard exists - no filter
            if (!WILDCARD.equals(serviceStrings[0])) {
                switch (serviceStrings.length - 1) {
                    case SUBCATEGORY:
                        entity.subcategory(Integer.parseInt(serviceStrings[SUBCATEGORY]));
                    case CATEGORY:
                        entity.category(Integer.parseInt(serviceStrings[CATEGORY]));
                    default:
                        entity.questionType(Integer.parseInt(serviceStrings[QUESTION]));
                }
            }
        } catch (Exception e) {
            return false;
        }

        return handleNext(entity, box);

    }
}
