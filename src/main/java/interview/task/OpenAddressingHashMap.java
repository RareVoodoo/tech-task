package interview.task;


/**
 * Simplified implementation of a HashMap with open addressing.
 *
 * @author Vlad Konyshevksyi
 */
public class OpenAddressingHashMap {
    /**
     * Table initial capacity
     */
    private int capacity = 32;

    /**
     * Number of included in table Entries
     */
    private int size = 0;

    /**
     * Table load factor
     */
    private final float loadFactor = 0.76f;

    /**
     * Table max capacity before resizing
     */
    private int threshold;

    /**
     * Table with key-value pairs (Entries)
     */
    private Entry[] table;

    /**
     * Container for storing a key-value pairs
     */
    static class Entry {
        int key;
        long value;
        int hash;
        boolean switcher;

        public Entry(int key, long value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.switcher = false;
        }
    }

    public OpenAddressingHashMap() {
        table = new Entry[capacity];
        threshold = (int) (capacity * loadFactor);

        for (int i = 0; i < capacity; i++) {
            table[i] = null;
        }
    }

    /**
     * Computes hashcode for a input key
     *
     * @param key entry key
     * @return key hash code
     */
    int hash(Integer key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * Adds new key-value pair to the table
     *
     * @param key   entry key
     * @param value entry value
     */
    void put(int key, long value) {
        int hashIdx = hash(key) & (capacity - 1);

        while (table[hashIdx] != null && table[hashIdx].key != key) {
            table[hashIdx].switcher = true;
            hashIdx++;
            hashIdx %= capacity;
        }

        if (table[hashIdx] == null) {
            size++;
            table[hashIdx] = new Entry(key, value, hash(key));
        }

        if (size >= threshold) {
            resize(2 * table.length);
        }

    }

    /**
     * Increases table size
     *
     * @param newCapacity new capacity of the table
     */
    void resize(int newCapacity) {
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        capacity = newCapacity;
        threshold = (int) (table.length * loadFactor);
    }

    /**
     * Transfer all entries to newTable
     *
     * @param newTable empty table with new capacity
     */
    void transfer(Entry[] newTable) {
        for (int i = 0; i < table.length; i++) {
            Entry entry = table[i];
            if (entry != null) {
                table[i] = null;
                int elemIdx = indexFor(entry.hash, newTable.length);
                newTable[elemIdx] = entry;
            }
        }
    }

    /**
     * Computes Entry place (index) in the table
     *
     * @param hash     computed Entry hash code
     * @param capacity table capacity
     * @return index in the table
     */
    int indexFor(int hash, int capacity) {
        return hash & (capacity - 1);
    }

    /**
     * Retrieves a value from consists Entry
     *
     * @param key entry key
     * @return entry value
     */
    Long get(int key) {
        int hashIdx = indexFor(hash(key), capacity);


        if (table[hashIdx] != null && table[hashIdx].switcher) {
            for (int i = hashIdx; i < table.length; i++) {
                Entry currEntry = table[i];
                if (currEntry.key == key) {
                    return currEntry.value;
                }
            }
        }

        if ((table[(capacity - 1) & hash(key)]) != null) {

            while (table[hashIdx] != null) {
                int counter = 0;

                if (counter++ > capacity) {
                    return null;
                }

                if (table[hashIdx].key == key) {
                    return table[hashIdx].value;
                }

                hashIdx++;
                hashIdx %= capacity;
            }
        }
        return null;
    }

    /**
     * Returns size of the HashMap
     *
     * @return size of the HashMap
     */
    public int size() {
        return size;
    }

    public String toString() {

        String description = "OAHashMap: [ ";

        for (int i = 0; i < capacity; i++) {
            if (this.table[i] == null) {
                description += "__  ";
            } else {
                description += String.format("%2d  ", this.table[i].key);
            }
        }

        description += "]";

        return description;
    }
}
