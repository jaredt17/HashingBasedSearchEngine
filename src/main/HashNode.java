package main;

import java.util.ArrayList;

/**
 * This calls acts as a wrapper for our values that provides a way of providing collision
 * handling and other useful functions
 * @param <T>
 */
class HashNode<T>{
    T value;
    ArrayList<MapNode<T>> collisions = null;

    /**
     * Sets the init value of the hashnode
     * @param value
     */
    HashNode(T value){
        this.value = value;
    }

    /**
     * This returns all of the correct urls in a collision cluster
     * @param headKey
     * @param correctKey
     * @return
     */
    ArrayList<T> getAllLinksForCorrectKey(String headKey, String correctKey){
    	ArrayList<T> links = new ArrayList<>();
    	if(headKey.equals(correctKey)) {links.add(value);}
        for (MapNode<T> collision : collisions) {
            if (correctKey.equals(collision.k)) {
                links.add(collision.v);
            }
        }
        return links;
    }

    /**
     * This call is the same as getAllLinksFor..
     * But returns the first value it finds.
     * @param correctKey
     * @return null for key that does'nt exist error num is already set at this point
     */
    T getLinkForCorrectKey(String correctKey) {
        for (MapNode<T> collision : collisions) {
            if (correctKey.equals(collision.k)) {
                return collision.v;
            }
        }
    	return null;
    }

    /**
     * Handles a collision by checking the collision mapnode array and creating it if its null
     * and adding to it.
     * @param nodeToAdd node to add
     * @param key the key for the node
     */
    void handleCollision(HashNode<T> nodeToAdd, String key){
        if(this.collisions == null){
            this.collisions = new ArrayList<>();
        }
        this.collisions.add(new MapNode<>(nodeToAdd.value,key));
    }

    /**
     * Prints all the values in the collisions
     */
    void printAllCollisions(){
        System.out.printf("Collisions:{key,val} [");
        for(int i = 0;i<collisions.size();i++){
            System.out.printf("%s %s",collisions.get(i).k,collisions.get(i).v);
            if(i != collisions.size()-1){
                System.out.print(", ");
            }
        }
        System.out.printf("]\n");
    }

    @Override
    public String toString() {
        return String.format("HashNode: Value: %s",value);
    }

}
