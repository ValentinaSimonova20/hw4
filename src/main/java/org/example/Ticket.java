package org.example;

import java.time.OffsetDateTime;

/**
 * Можно изменять по своему усмотрению, не нарушая правила приоритезации очереди
 */
public class Ticket implements Comparable<Ticket> {

    private static int idSeq;

    /**
     * Автогенерация id
     */
    int id = ++idSeq;

    /**
     * Приоритеты типов:
     * 1) pension
     * 2) любые другие
     */
    String type;

    /**
     * Приоритет для ранней регистрации
     */
    OffsetDateTime registerTime;

    public Ticket(String type) {
        this.type = type;
        this.registerTime = OffsetDateTime.now();
    }

    @Override
    public int compareTo(Ticket o) {
        if(o == null) {
            return 1;
        }
        if(this.type.equals("pension")) {
            if(!o.type.equals("pension")) {
                return -1;
            }
        } else {
            if(o.type.equals("pension")) {
                return 1;
            }
        }
        return this.registerTime.compareTo(o.registerTime);
    }
}
