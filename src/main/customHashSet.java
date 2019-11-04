package main;

import java.util.ArrayList;

/**
 * HashSet
 * @param <T>
 */

public class customHashSet<T> extends MapInf<T> {

    private Map<HashNode<T>> dhm;

    /**
     * creates a new map with an init size
     */
    customHashSet() {
        dhm = new Map<>(1000);
    }

    /**
     * add a new value into hash set and checks for collisions
     * @param ele
     * @param key
     * @return
     */
    public int put(T ele, String key) {
        HashNode<T> node = new HashNode<>(ele);
        int callback = dhm.put(node, key);
        if (callback == -2) dhm.get(key).handleCollision(node, key);
        errno.resetWarn();
        errno.resetErr();
        return callback;
    }

    /**
     * gets a single now for a given key
     * @param key key
     * @return the node value
     */
    public T get(String key) {
        HashNode<T> hashNode = dhm.get(key);
        if(hashNode != null){
            MapNode<HashNode<T>> mapNode = dhm.getMapNode(key);
            if(mapNode != null){
                if(mapNode.k.equals(key)){return hashNode.value;}
                if( hashNode.collisions != null){
                    errno.resetWarn();
                    errno.resetErr();
                    return hashNode.getLinkForCorrectKey(key);
                }
            }
        }
        errno.resetWarn();
        errno.resetErr();
        return null;
    }
}

