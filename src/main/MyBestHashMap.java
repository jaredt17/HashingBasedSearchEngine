package main;

import java.util.ArrayList;

/**
 * This class extents from the map class
 * @param <T> type
 */

public class MyBestHashMap<T> extends MapInf<T> {

    private Map<HashNode<T>> dhm;

    /**
     * creates a new map with an init size
     */
    MyBestHashMap() {
        dhm = new Map<>(1000);
    }

    /**
     * add a new value into hashmap and checks for collisions
     * @param ele
     * @param key
     * @return
     */
    public int put(T ele, String key) {
        HashMapStats.startRecording();
        HashNode<T> node = new HashNode<>(ele);
        int callback = dhm.put(node, key);
        if (callback == -2) dhm.get(key).handleCollision(node, key);
        HashMapStats.stopRecording(0);
        return callback;
    }

    /**
     * gets all the links from a given node
     * @param key key for node
     * @return list of nodes
     */
    ArrayList<T> getAllLinks(String key) {
        HashMapStats.startRecording();
        if (errno.getERRNO() == 5) {
            return new ArrayList<>();
        }
        HashNode<T> hNode = dhm.get(key);
        MapNode<HashNode<T>> mapNode = dhm.getMapNode(key);
        if (errno.getERRNO() != 5 && hNode.collisions != null) {
            HashMapStats.stopRecording(1);
            return hNode.getAllLinksForCorrectKey(mapNode.k, key);
        }
        ArrayList<T> nodeValue = new ArrayList<>();
        if (errno.getERRNO() != 5) {
            nodeValue.add(hNode.value);
        }
        HashMapStats.stopRecording(-2);
        return nodeValue;
    }

    /**
     * gets a single now for a given key
     * @param key key
     * @return the node value
     */
    public T get(String key) {
        HashMapStats.startRecording();
        HashNode<T> hNode = dhm.get(key);
        MapNode<HashNode<T>> mapNode = dhm.getMapNode(key);
        if (mapNode == null)
            return null;
        if ((errno.getERRNO() != 5) && !mapNode.k.equals(key) && (hNode.collisions != null)) {
            return hNode.getLinkForCorrectKey(key);
        }
        HashMapStats.stopRecording(1);
        return dhm.get(key).value;
    }

    /**
     * get the node object for a key
     * @param key key
     * @return node object
     */

    private HashNode<T> getNode(String key) {
        return dhm.get(key);
    }

    /**
     * prints the hashmap and collisions
     */
    public void printHashMap() {
        System.out.println("---Printing Map---");
        String[] keys = dhm.getKeys();
        for (String key : keys) {
            HashNode<T> node = getNode(key);
            System.out.printf("KEY: %s | ", key);
            System.out.println(node.toString());
            if (node.collisions != null) {
                System.out.printf("--------Collisions for key: %s--------\n", key);
                node.printAllCollisions();
                System.out.printf("--------------------------------------\n");
            }
        }
    }
}
