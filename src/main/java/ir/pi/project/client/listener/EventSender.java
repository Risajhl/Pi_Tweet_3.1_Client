package ir.pi.project.client.listener;


import ir.pi.project.shared.event.Event;
import ir.pi.project.shared.response.Response;

public interface EventSender {
    Response send(Event event);
    void close();
}
