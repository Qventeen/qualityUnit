package programmertasken.handlers.query;

import programmertasken.entity.Box;
import programmertasken.entity.QueryEntity;
import programmertasken.handlers.BaseHandler;

import java.util.Objects;
import java.util.regex.Pattern;


/**
 * Service handler for query
 */
public class QueryServiceHandler extends BaseHandler<QueryEntity, Box> {
    private final Pattern pattern;
    private final int VARIATION_INDEX = 1;
    private final int SERVICE_INDEX = 0;
    private final String WILDCARD;

    public QueryServiceHandler(Pattern pattern, String wildcard) {
        Objects.requireNonNull(pattern);
        this.pattern = pattern;
        Objects.requireNonNull(pattern);
        this.WILDCARD = wildcard;

    }

    @Override
    public boolean handle(QueryEntity entity, Box box) {
        try {
            String[] serviceStrings = pattern.split(box.next());

            //Wildcard exists - no filter
            if (!WILDCARD.equals(serviceStrings[0])) {
                switch (serviceStrings.length - 1) {
                    case VARIATION_INDEX:
                        entity.variation(Integer.parseInt(serviceStrings[VARIATION_INDEX]));
                    default:
                        entity.service(Integer.parseInt(serviceStrings[SERVICE_INDEX]));
                }
            }
        } catch (Exception e) {
            return false;
        }

        return handleNext(entity, box);
    }
}
