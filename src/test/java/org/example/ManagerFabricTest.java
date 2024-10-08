package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManagerFabricTest {

    HomeWork homeWork = new HomeWork();

    @Test
    void managerFabric() {
        TicketManager ticketManager = homeWork.managerFabric();
        Ticket ticket1 = new Ticket("pension");
        Ticket ticket2 = new Ticket("pension");
        Ticket ticket3 = new Ticket("tmp2");
        Ticket ticket4 = new Ticket("tmp5");
        // добавляем сначала ticket который создался позже чтоблы проверить сортировку по registerTime
        ticketManager.add(ticket2);
        ticketManager.add(ticket3);
        ticketManager.add(ticket1);
        ticketManager.add(ticket4);

        // несмотря на то что сначала добавили ticket2 в очередь - первым извлекается ticket1
        // потому что у него registerTime раньше
        assertEquals(ticket1, ticketManager.next());
        // вторым добавили ticket3 в очередь но ticket2 приоритетнее по типу и времени
        assertEquals(ticket2, ticketManager.next());

        assertEquals(ticket3, ticketManager.next());
        assertEquals(ticket4, ticketManager.next());
    }

    @Test
    void emptyManagerFabric() {
        TicketManager ticketManager = homeWork.managerFabric();

        RuntimeException exception = assertThrows(RuntimeException.class, ticketManager::next);

        assertEquals("Талонов больше нет", exception.getMessage());
    }

    @Test
    void fullQueue() {
        TicketManager ticketManager = homeWork.managerFabric();
        for(int i = 0; i < 10; i++) {
            ticketManager.add(new Ticket("pension"));
        }

        RuntimeException exception =
                assertThrows(RuntimeException.class, () -> ticketManager.add(new Ticket("pension")));

        assertEquals("Лимит на талоны превышен", exception.getMessage());
    }
}
