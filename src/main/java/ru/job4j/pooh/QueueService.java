package ru.job4j.pooh;

import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Class QueueService
 *
 * @author Kseniya Dergunova
 * @since 25.11.2021
 */
public class QueueService implements Service {

    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        if ("POST".equals(req.httpRequestType())) {
            return post(req);
        } else if ("GET".equals(req.httpRequestType())) {
            return get(req);
        } else {
            return new Resp("Not Implemented", "501");
        }
    }

    private Resp post(Req req) {
        queue.putIfAbsent(req.getSourceName(), new ConcurrentLinkedQueue<>(Collections.singleton(req.getParam())));
        return new Resp(req.getParam(), "200");
    }

    private Resp get(Req req) {
        var resp = Optional.ofNullable(queue.get(req.getSourceName()).poll()).orElse("");
        return new Resp(resp, "200");
    }
}
