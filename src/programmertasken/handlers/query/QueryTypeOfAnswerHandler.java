package programmertasken.handlers.query;

import programmertasken.TypeOfAnswer;
import programmertasken.entity.Box;
import programmertasken.entity.QueryEntity;
import programmertasken.handlers.BaseHandler;


/**
 * Type of answer handler for query
 */
public class QueryTypeOfAnswerHandler extends BaseHandler<QueryEntity, Box> {
    @Override
    public boolean handle(QueryEntity entity, Box box) {
        try {
            entity.pn(TypeOfAnswer.valueOf(box.next()));
        } catch (Exception e) {
            return false;
        }
        return handleNext(entity, box);
    }
}
