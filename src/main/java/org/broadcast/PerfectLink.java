package org.broadcast;

public interface PerfectLink {
    public void send(Message message);
    public void subscribeForMessages(MessageReceiver receiver);
}
