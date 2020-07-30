package interview.task;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class HashMapTest {

    @Test
    public void whenGetInsertedValuesShouldReturnActualValues() {
        OpenAddressingHashMap hashMap = new OpenAddressingHashMap();
        hashMap.put(20, 1256L);
        hashMap.put(5458, 56L);
        hashMap.put(578, 34587L);

        assertEquals(Optional.of(1256L).get(), hashMap.get(20));
        assertEquals(Optional.of(56L).get(), hashMap.get(5458));
        assertEquals(Optional.of(34587L).get(), hashMap.get(578));
    }


    @Test
    public void whenGetMissingValueShouldReturnNull() {
        OpenAddressingHashMap hashMap = new OpenAddressingHashMap();
        hashMap.put(20, 1256L);

        int expectedValue = 123;
        assertNull(hashMap.get(expectedValue));
    }


    @Test
    public void whenGetHashMapSizeShouldReturnActualEntriesNumber() {
        OpenAddressingHashMap hashMap = new OpenAddressingHashMap();
        hashMap.put(12, 1256L);
        hashMap.put(543, 56L);
        hashMap.put(354, 34587L);
        hashMap.put(6767, 34587L);
        hashMap.put(1242, 34587L);
        hashMap.put(5, 34587L);
        hashMap.put(4875, 34587L);
        hashMap.put(789, 34587L);
        hashMap.put(111, 34587L);
        hashMap.put(58, 34587L);

        assertEquals(10, hashMap.size());
    }

    @Test
    public void whenPutEntriesWithEqualHashCodeShouldReturnEach() {
        OpenAddressingHashMap hashMap = new OpenAddressingHashMap();

        hashMap.put(3, 8473L);
        hashMap.put(35, 23435L);
        hashMap.put(67, 11111111111L);

        assertEquals(Optional.of(8473L).get(), hashMap.get(3));
        assertEquals(Optional.of(23435L).get(), hashMap.get(35));
        assertEquals(Optional.of(11111111111L).get(), hashMap.get(67));

    }


}
