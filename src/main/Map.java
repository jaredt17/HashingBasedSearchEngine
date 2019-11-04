package main;

/**
 * Basic map
 * */
public class Map<T> extends MapInf<T> {

    private MapNode<T>[] bucket;
    private int REAL_SIZE = 0;

    /**
     * creates a new map object with a start size
     * @param startSize
     */
    Map(int startSize) {
        bucket = new MapNode[startSize];
    }

    /**
     * adds a new value to the bucket
     * and calls back for an error
     * -1 key is null
     * -2 key is a collision
     * @param ele element value to insert
     * @param key key for the element
     * @return
     */
    public int put(T ele, String key) {
        if(key == null) {
            System.out.println("Error: Key Can't Be NULL");
            return -1;
        }

        int index = getHashIndex(key);

        //check for collision
        if(bucket[index] != null) {
            HashMapStats.NUM_COLLISIONS++;
            return -2;
        }

        bucket[index] = new MapNode<>(ele, key);
        REAL_SIZE++;

        if (REAL_SIZE / bucket.length > .7)
            rehash();

        HashMapStats.NUM_INSERTIONS++;
        return index;
    }

    public int forcePut(T ele,String key){
        int index = getHashIndex(key);
        bucket[index] = new MapNode<>(ele, key);
        return index;
    }

    /**
     * Gets the map node value at a given key
     * if the key does'nt exist the error number is set
     * @param key
     * @return node value
     */
    public T get(String key) {
        MapNode<T> mNode = bucket[getHashIndex(key)];
        if(mNode == null) {
            errno.setWARN(5);
            errno.setERRNO(5);
            return null;
        }
        return mNode.v;
    }

    /**
     * Get a map node at a given key
     * @param key
     * @return
     */
    MapNode<T> getMapNode(String key){
        return bucket[getHashIndex(key)];
    }

    /**
     * Calculates the hash code then the index in the bucket for a given key.
     * @param ele key
     * @return
     */
    private int getHashIndex(String ele) {
        int sum = 0;
        for (char i : ele.toCharArray()) {
            sum += (int) i;
        }
        return sum % bucket.length;
    }

    /**
     * Rehashes all the values in the bucket for a new bucket size
     */
    private void rehash() {
        System.out.println("brb busy...Re-Hashing");
        REAL_SIZE = 0;
        MapNode<T>[] temp = bucket;
        bucket = new MapNode[bucket.length*2];
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null) {
                MapNode<T> node = temp[i];
                put(node.v, node.k);
            }
        }
    }

    /**
     * returns all the non null keys in the map
     * @return array of keys
     */
    String[] getKeys() {
        String[] keys = new String[REAL_SIZE];
        int x = 0;
        for (MapNode<T> tMapNode : bucket) {
            if (tMapNode != null) {
                keys[x] = tMapNode.k;
                x++;
            }
        }
        return keys;
    }

}
