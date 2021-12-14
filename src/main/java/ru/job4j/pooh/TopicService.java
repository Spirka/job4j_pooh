package ru.job4j.pooh;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Class TopicService
 *
 * @author Kseniya Dergunova
 * @since 25.11.2021
 */
public class TopicService implements Service {

    private final ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentLinkedQueue<String>>> topics = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        if ("GET".equals(req.httpRequestType())) {
            return get(req);
        } else if ("POST".equals(req.httpRequestType())) {
            return put(req);
        } else {
            return new Resp("", "204");
        }
    }

    private Resp put(Req req) {
        ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> topic = topics.get(req.getSourceName());
        topic.forEach((key, value) -> value.add(req.getParam()));
        return new Resp(req.getParam(), "200");
    }

    private Resp get(Req req) {
        topics.putIfAbsent(req.getSourceName(), new ConcurrentHashMap<>());
        topics.get(req.getSourceName()).putIfAbsent(req.getParam(), new ConcurrentLinkedQueue<>());
        String result = topics.get(req.getSourceName()).get(req.getParam()).poll();
        return new Resp(Objects.requireNonNullElse(result, ""), "200");
    }
}
