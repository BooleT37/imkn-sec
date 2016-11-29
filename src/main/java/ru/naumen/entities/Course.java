package ru.naumen.entities;

/**
 * @author aarkaev
 * @since 19.11.2016
 */
public class Course {

    private int number;
    private String title;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Course{" + "number=" + number + ", title='" + title + '\'' + '}';
    }
}
