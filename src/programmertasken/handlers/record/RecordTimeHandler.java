package programmertasken.handlers.record;

import programmertasken.entity.Box;
import programmertasken.entity.RecordEntity;
import programmertasken.handlers.BaseHandler;

/**
 * Time handler for record
 */
public class RecordTimeHandler extends BaseHandler<RecordEntity, Box> {
    @Override
    public boolean handle(RecordEntity entity, Box box) {
        try {
            entity.setTime(Integer.parseInt(box.next()));
        } catch (Exception e) {
            return false;
        }
        return handleNext(entity, box);
    }
}
