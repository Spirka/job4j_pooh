package ru.job4j.pooh;

/**
 * Class Req служит для парсинга входящего запроса.
 *
 * @author Kseniya Dergunova
 * @since 25.11.2021
 */
public class Req {
    private final String httpRequestType;
    private final String poohMode;
    private final String sourceName;
    private final String param;

    public Req(String httpRequestType, String poohMode, String sourceName, String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    /**
     * Метод для парсинга строки с контентом.
     * <p>
     * httpRequestType - GET или POST. Он указывает на тип запроса.
     * poohMode - указывает на режим работы: queue или topic.
     * sourceName - имя очереди или топика.
     * param - содержимое запроса.
     *
     * @param content строка с запросом.
     * @return {@link Req объект запроса}
     */
    public static Req of(String content) {
        String httpRequestType;
        String poohMode;
        String sourceName;
        String param;
        String[] requestLines = content.split(System.lineSeparator());
        String[] firstLine = requestLines[0].split(" ");
        String[] modeAndName = firstLine[1].split("/");
        httpRequestType = firstLine[0];
        poohMode = modeAndName[1];
        sourceName = modeAndName[2];
        param = requestLines[requestLines.length - 1];
        if ("GET".equals(httpRequestType)) {
            if (modeAndName.length < 4) {
                param = "";
            } else {
                param = modeAndName[3];
            }
        }
        return new Req(httpRequestType, poohMode, sourceName, param);
    }

    public String httpRequestType() {
        return httpRequestType;
    }

    public String getPoohMode() {
        return poohMode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getParam() {
        return param;
    }
}
