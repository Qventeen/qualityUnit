package programmertasken.entity;

import programmertasken.TypeOfAnswer;

import java.time.LocalDate;

/**
 * Model of Query for input data.
 */
public class RecordEntity {
    private int service;
    private int variation;

    private int questionType;
    private int category;
    private int subcategory;

    private TypeOfAnswer firstNext;
    private LocalDate date;
    private int time;

    //Getters & setters
    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public int getVariation() {
        return variation;
    }

    public void setVariation(int variation) {
        this.variation = variation;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(int subcategory) {
        this.subcategory = subcategory;
    }

    public TypeOfAnswer getPN() {
        return firstNext;
    }

    public void setPN(TypeOfAnswer firstNext) {
        this.firstNext = firstNext;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

}
