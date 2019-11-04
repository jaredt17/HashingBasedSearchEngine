package main;

/**
 * abstract class for map and mybesthashmap
 * @param <T>
 */
public abstract class MapInf<T> {
    public abstract int put(T ele, String key);
    public abstract T get(String key);
}
