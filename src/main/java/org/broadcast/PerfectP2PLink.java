package org.broadcast;

import java.util.ArrayList;

public class PerfectP2PLink implements PerfectLink {
    private MessageReceiver owner;
    private ArrayList<PerfectLink>  peers;

    public PerfectP2PLink(MessageReceiver owner, ArrayList<PerfectLink> peers) {
        this.owner = owner;
        this.peers = peers;
    }


    @Override
    public void send(Message message) {

    }

    @Override
    public void subscribeForMessages(MessageReceiver receiver) {

    }
}
