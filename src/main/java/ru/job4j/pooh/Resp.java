package ru.job4j.pooh;

/**
 * Class Resp
 *
 * @author Kseniya Dergunova
 * @since 25.11.2021
 */
public class Resp {
    private final String text;
    private final String status;

    public Resp(String text, String status) {
        this.text = text;
        this.status = status;
    }

    public String text() {
        return text;
    }

    public String status() {
        return status;
    }
}
