package programmertasken.handlers;

/**
 * Base interface for hendlers
 * @param <E> entity - shared model that former when go trough chain of handlers
 * @param <B> box - shared context that need for all handler of chain
 */
public interface Handler<E, B> {
    boolean handle(E entity, B box);

    Handler<E, B> linkNext(Handler<E, B> handler);
}
