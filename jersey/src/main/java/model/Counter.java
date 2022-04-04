package model;

public class Counter {
    private final String date;
    private final int value;

    public Counter(String dateInfo, int counter) {
        this.value = counter;
        this.date = dateInfo;
    }

    public String getDate() {
        return date;
    }

    public int getValue() {
        return value;
    }
}
