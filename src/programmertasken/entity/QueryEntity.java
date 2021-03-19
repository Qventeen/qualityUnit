package programmertasken.entity;

import programmertasken.TypeOfAnswer;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * This class working as java view of query from input data.
 */
public class QueryEntity {
    private final Predicate<RecordEntity> BASE_PREDICATE = b -> true;

    /**
     * Start of date range
     */
    private LocalDate from;

    /**
     * End of data range
     */
    private LocalDate to;

    /**
     * Filters of query
     */
    private Predicate<RecordEntity> filters = BASE_PREDICATE;

    //getters & setters
    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public Predicate<RecordEntity> getFilters() {
        return filters;
    }

    //Filter builders
    public void service(int service) {
        filters = filters.and(e -> e.getService() == service);
    }

    public void variation(int variation) {
        filters = filters.and(e -> e.getVariation() == variation);
    }

    public void questionType(int question) {
        filters = filters.and(e -> e.getQuestionType() == question);
    }

    public void category(int category) {
        filters = filters.and(e -> e.getCategory() == category);
    }

    public void subcategory(int subcategory) {
        filters = filters.and(e -> e.getSubcategory() == subcategory);
    }

    public void pn(TypeOfAnswer pn) {
        if (pn != null) {
            filters = filters.and(e -> e.getPN() == pn);
        }
    }
}
