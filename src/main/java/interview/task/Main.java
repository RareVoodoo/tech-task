package interview.task;

import java.util.HashMap;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        long startTime, endTime, duration;

        OpenAddressingHashMap openAddressingHashMap = new OpenAddressingHashMap();
        HashMap<Integer, Long> hashMap = new HashMap<>();

        openAddressingHashMap.put(2020, 21455L);
        hashMap.put(2020, 21455L);

        Random random = new Random();
        int elemNumber = 1000000;


        startTime = System.nanoTime();
        for (int i = 0; i < elemNumber; i++) {
            hashMap.put(random.nextInt(10000), random.nextLong());
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Origin HashMap put() method timing: " + duration / 1000);


        startTime = System.nanoTime();
        for (int i = 0; i < elemNumber; i++) {
            openAddressingHashMap.put(random.nextInt(10000), random.nextLong());
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("OpenAddressingHashMap put() method timing: " + duration / 1000);

        System.out.println("---------------");

        startTime = System.nanoTime();
        hashMap.get(2020);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("HashMap origin get() method timing: " + duration);


        startTime = System.nanoTime();
        openAddressingHashMap.get(2020);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("OpenAddressingHashMap get() method timing: " + duration);

    }
}
