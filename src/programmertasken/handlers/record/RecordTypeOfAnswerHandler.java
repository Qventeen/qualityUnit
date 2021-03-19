package programmertasken.handlers.record;

import programmertasken.TypeOfAnswer;
import programmertasken.entity.Box;
import programmertasken.entity.RecordEntity;
import programmertasken.handlers.BaseHandler;

/**
 * Type of answer handler for record
 */
public class RecordTypeOfAnswerHandler extends BaseHandler<RecordEntity, Box> {
    @Override
    public boolean handle(RecordEntity entity, Box box) {
        try {
            entity.setPN(TypeOfAnswer.valueOf(box.next()));
        } catch (Exception e) {
            return false;
        }
        return handleNext(entity, box);
    }
}
