package programmertasken;

import programmertasken.entity.RecordEntity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Storage for Entities
 */
public class EntityDB {
    private final TreeMap<LocalDate, List<RecordEntity>> records = new TreeMap<>();

    /**
     * Add Record Entity to storage by date of creating as key
     * @param record prepared data for storage
     * @throws NullPointerException if input parameter is null
     */
    public void addEntity(RecordEntity record) {
        Objects.requireNonNull(record);
        List<RecordEntity> list = records.computeIfAbsent(
                record.getDate(),
                v -> new LinkedList<>()
        );
        list.add(record);
    }

    /**
     * Get Record Entities from data storage
     * @param from - date inclusive if null take all data <= to
     * @param to - date inclusive if null take all data >= from
     * @return List of Records Entities between from and to dates inclusive
     */
    public List<RecordEntity> getEntitiesBetweenInclusive(LocalDate from, LocalDate to) {
        LocalDate fromDate = from == null ? LocalDate.MIN : from;
        LocalDate toDate = to == null ? LocalDate.MAX : to;

        return records.subMap(fromDate, true, toDate, true)
                .values().stream().flatMap(List::stream).collect(Collectors.toList());

    }

    /**
     * Clear storage
     */
    public void clear() {
        records.clear();
    }
}
