package programmertasken.entity;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * This class take some string and split her by splitter
 * Works as simple iterator that iterated by splitting substring.
 * */
public class Box {
    private Pattern splitter;
    private String logEntry = "";
    private int position = 0;
    private String[] elements = {};

    public Box(String record, Pattern splitter) {
        Objects.requireNonNull(splitter);
        Objects.requireNonNull(record);
        this.logEntry = record.trim();
        elements = splitter.split(record);
    }

    public String next() {
        return getElement(position++);
    }

    //Technical methods
    private String getElement(int pos) {
        if (testPosition(pos)) {
            return elements[pos];
        }
        return "";
    }

    private boolean testPosition(int pos) {
        return pos >= 0 && pos < this.elements.length;
    }
}