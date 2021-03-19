package programmertasken;


import programmertasken.entity.Box;
import programmertasken.entity.QueryEntity;
import programmertasken.entity.RecordEntity;
import programmertasken.handlers.Handler;
import programmertasken.handlers.query.QueryDateHandler;
import programmertasken.handlers.query.QueryQuestionHandler;
import programmertasken.handlers.query.QueryServiceHandler;
import programmertasken.handlers.query.QueryTypeOfAnswerHandler;
import programmertasken.handlers.record.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.averagingInt;

/**
 * Main module
 */
public class DataParser {
    /**
     * Input date formatter
     */
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Delimiter elements of input format
     */
    private final Pattern ELEMENT_DELIMITER = Pattern.compile("\\s");

    /**
     * Delimiter for subelements of input format
     */
    private final Pattern SUB_ELEMENT_DELIMITER = Pattern.compile("\\.");

    /**
     * Date broads delimiter
     */
    private final Pattern DATE_DELIMITER = Pattern.compile("-");

    /**
     * WILDCARD for service and question elements of query
     */
    private final String WILDCARD = "*";

    /**
     * Record marker
     */
    private final String RECORD_MARKER = "C";

    /**
     * Query marker
     */
    private final String QUERY_MARKER = "D";

    /**
     * Parsed records storage.
     */
    private EntityDB entityDB;

    /**
     * Handler for record line parsing
     */
    private Handler<RecordEntity, Box> recordHandler;

    /**
     * Handler for query line parsing
     */
    private Handler<QueryEntity, Box> queryHandler;

    /**
     * Constructor
     */
    public DataParser() {
        entityDB = new EntityDB();
        prepareQueryChain();
        prepareRecordChain();
    }

    /**
     * File parser
     * @param path - path to file
     */
    public void parseFile(Path path) {
        try (BufferedReader reader = new BufferedReader(Files.newBufferedReader(path))) {
            String currentLine = reader.readLine();

            int numberOfLines = Integer.parseInt(currentLine);

            for (int k = 0; reader.ready() && k < numberOfLines; k++) {
                currentLine = reader.readLine();

                if (currentLine.startsWith(RECORD_MARKER)) {
                    parseRecord(currentLine);
                }
                if (currentLine.startsWith(QUERY_MARKER)) {
                    parseQuery(currentLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        entityDB.clear();
    }

    /**
     * This method doing parsing of records
     * @param record input string that has record format
     */
    private void parseRecord(String record) {
        Box recordBox = new Box(record, ELEMENT_DELIMITER);
        recordBox.next();
        RecordEntity recordEntity = new RecordEntity();
        if (!recordHandler.handle(recordEntity, recordBox)) {
            System.err.printf("Error of parsing of record => %s", record);
        } else {
            entityDB.addEntity(recordEntity);
        }
    }

    /**
     * This method doing parsing and performing of queries
     * @param query input string that has query format
     */
    private void parseQuery(String query) {
        Box queryBox = new Box(query, ELEMENT_DELIMITER);
        queryBox.next();
        QueryEntity queryEntity = new QueryEntity();
        if (!queryHandler.handle(queryEntity, queryBox)) {
            System.err.printf("Error of parsing of record => %s", query);
            return;
        }
        //Get record between dates
        List<RecordEntity> setRecordsByDates = entityDB
                .getEntitiesBetweenInclusive(
                        queryEntity.getFrom(),
                        queryEntity.getTo());

        //Collect result
        double resultOfQuery = setRecordsByDates.stream()
                .filter(queryEntity.getFilters())
                .collect(averagingInt(RecordEntity::getTime));

        if (resultOfQuery != 0) {
            System.out.printf("%d %n",Math.round(resultOfQuery));
        } else {
            System.out.println("-");
        }
    }

    /**
     * Create record chain handlers
     */
    private void prepareRecordChain() {
        recordHandler = new RecordServiceHandler(SUB_ELEMENT_DELIMITER);
        recordHandler.linkNext(new RecordQuestionHandler(SUB_ELEMENT_DELIMITER))
                .linkNext(new RecordTypeOfAnswerHandler())
                .linkNext(new RecordDateHandler(DATE_FORMATTER))
                .linkNext(new RecordTimeHandler());
    }

    /**
     * Create query chain handlers
     */
    private void prepareQueryChain() {
        queryHandler = new QueryServiceHandler(SUB_ELEMENT_DELIMITER, WILDCARD);
        queryHandler.linkNext(new QueryQuestionHandler(SUB_ELEMENT_DELIMITER, WILDCARD))
                .linkNext(new QueryTypeOfAnswerHandler())
                .linkNext(new QueryDateHandler(DATE_FORMATTER, DATE_DELIMITER));
    }

}
