package org.example;

import java.util.*;


public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Требуется реализовать интерфейс TicketManager в соответствии с JavaDoc описанием.
     * Реализации очередей из стандартной библиотеки не используем.
     */
    public TicketManager managerFabric() {
        return new TicketManagerImpl();
    }


    /**
     * Задача со звездочкой (опционально)
     * <br/>
     * На вход строки:
     *  номер_впереди : номер_сзади
     * Если впереди или сзади никого нет - то 0, для первого и последнего в очереди.
     * На выход нужно восстановить порядок номеров в очереди.
     *
     * В тестах генератор тестовых данных (очереди и пары).
     * @see <a href="https://codeforces.com/problemset/problem/490/B?locale=ru">https://codeforces.com/problemset/problem/490/B?locale=ru</a>
     */
    public List<Integer> check(List<String> records){
        int recordsSize = records.size();
        Integer[] a = new Integer[recordsSize];
        Integer[] b = new Integer[recordsSize];
        Integer[] c = new Integer[recordsSize];
        Arrays.fill(c, 0);
        List<Integer> result = Arrays.asList(c);

        for(int i = 0; i < recordsSize; i++) {
            String currentRecord = records.get(i);
            String[] splitedRecord = currentRecord.split(":");
            a[i] = Integer.valueOf(splitedRecord[0]);
            b[i] = Integer.valueOf(splitedRecord[1]);
            if(a[i] == 0) {
                result.set(1, b[i]);
            }
            if(b[i] == 0) {
                result.set(recordsSize - 2, a[i]);
            }

        }

        for(int i = 0; i < records.size(); i++) {
            if(result.contains(a[i]) && b[i] != 0 && a[i] != 0) {
               result.set(result.indexOf(a[i]) + 2, b[i]);
            }
            if(result.contains(b[i]) && a[i] != 0 && b[i] != 0) {
                result.set(result.indexOf(b[i]) - 2 , a[i]);
            }

        }

        return result;
    }

}
