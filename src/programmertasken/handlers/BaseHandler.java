package programmertasken.handlers;


/**
 * Base class for handler.
 * This class is a part of pattern Chain of Responsibility
 * @param <E>
 * @param <B>
 */
public abstract class BaseHandler<E, B> implements Handler<E, B> {
    Handler<E, B> next;

    @Override
    public abstract boolean handle(E entity, B box);

    @Override
    public Handler<E, B> linkNext(Handler<E, B> handler) {
        if (next == null) {
            this.next = handler;
            return this.next;
        }
        return this;
    }

    protected boolean handleNext(E entity, B box) {
        if (next != null) {
            next.handle(entity, box);
        }
        return true;
    }
}
