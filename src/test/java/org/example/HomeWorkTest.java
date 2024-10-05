package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeWorkTest {

    HomeWork homeWork = new HomeWork();

    @Test
    void managerFabric() {
        TicketManager ticketManager = homeWork.managerFabric();
        Ticket ticket1 = new Ticket("pension");
        Ticket ticket2 = new Ticket("pension");
        Ticket ticket3 = new Ticket("tmp2");
        Ticket ticket4 = new Ticket("tmp5");
        ticketManager.add(ticket2);
        ticketManager.add(ticket1);
        ticketManager.add(ticket3);
        ticketManager.add(ticket4);
        System.out.println();
    }

    @Test
    void check() {
        List<Integer> expectedQueue = generateQueue(1, 4);
        List<String> pairs = generatePairs(expectedQueue);
        assertEquals(expectedQueue, homeWork.check(pairs));
    }

    private List<String> generatePairs(List<Integer> expectedQueue) {
        List<String> pairs = new ArrayList<>();
        Function<Integer, Integer> map = (x) -> (x < 0 || x >= expectedQueue.size()) ? 0 : expectedQueue.get(x);

        for (int i = 0;
             i < expectedQueue.size(); i++) {
            pairs.add(String.format("%d:%d", map.apply(i - 1), map.apply(i + 1)));
        }
        Collections.shuffle(pairs);
        return pairs;
    }

    private List<Integer> generateQueue(int seed, int length) {
        return new Random(seed)
                .ints(1, length * 100)
                .limit(length)
                .boxed()
                .collect(Collectors.toList());
    }


}