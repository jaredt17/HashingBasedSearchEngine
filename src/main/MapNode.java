package main;

/**
 * This is the underlining class that holds the true values
 * @param <T>
 */
class MapNode<T> {
    String k;
    T v;

    /**
     * creates a new map node
     * @param v value
     * @param k key
     */
    MapNode(T v, String k) {
        this.k = k;
        this.v = v;
    }

    @Override
    public String toString() {
        return String.format("MapNode: key: %s",k);
    }
}