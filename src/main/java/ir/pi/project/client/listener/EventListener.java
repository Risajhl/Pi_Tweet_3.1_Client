package ir.pi.project.client.listener;

import ir.pi.project.shared.event.Event;

public interface EventListener {
    void listen(Event event);
}
