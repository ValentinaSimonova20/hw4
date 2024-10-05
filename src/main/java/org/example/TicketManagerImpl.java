package org.example;

public class TicketManagerImpl implements TicketManager {

    private final Ticket[] queue;

    private int tail;

    public TicketManagerImpl() {
        this.queue = new Ticket[10];
        tail = -1;
    }
    @Override
    public void add(Ticket ticket) {
        if(isFull()) {
            throw new RuntimeException("Лимит на талоны превышен");
        }

        int pos = 0;

        while (pos <= tail && ticket.compareTo(queue[pos]) < 0) {
            ++pos;
        }

        for (int i = tail; i >= pos; --i) {
            queue[i+1] = queue[i];
        }

        queue[pos] = ticket;

        ++tail;
    }

    @Override
    public Ticket next() {
        if(isEmpty()) {
            throw new RuntimeException("Талонов больше нет");
        }
        return queue[tail--];
    }

    private boolean isFull() {
        return tail == queue.length - 1;
    }

    private boolean isEmpty() {
        return tail == -1;
    }
}
