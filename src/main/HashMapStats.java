package main;

import java.util.ArrayList;
import java.io.*;

/**
 * Keeps track of valueable statics through our program as we test.
 */
class HashMapStats {
    static int NUM_COLLISIONS ,NUM_INSERTIONS,NUM_LOOKUPS = 0;
    private static long now,insertionTimeTotal,lookupTimeTotal = 0;

    private static ArrayList<Long> insertTimes = new ArrayList<>();
    private static ArrayList<Long> lookupAvgs = new ArrayList<>();

    /**
     * Starts a tracker
     */
    static void startRecording(){
        now = System.nanoTime();
    }

    /**
     * Stops the track on a stop call which identifies it a operation
     * @param type
     */
    static void stopRecording(int type){
        switch (type){
        	case -1:
        		System.out.println((System.nanoTime() - now)/1000000);
        		break;
            case 0:
                long curinsertTime = (System.nanoTime() - now);
                insertionTimeTotal+=curinsertTime;
                NUM_INSERTIONS++;
                insertTimes.add(curinsertTime);
                break;
            case 1:
                lookupTimeTotal+=(System.nanoTime() - now);
                NUM_LOOKUPS++;
                lookupAvgs.add(getAvgLookUpTime());
            default:
        }
    }

    /**
     * getter/setter
     * @return
     */
    static int getNumCollisions(){
        return NUM_COLLISIONS;
    }
    /**
     * getter/setter
     * @return
     */
    static long getAvgInsertionTime(){
        return insertionTimeTotal/(long)NUM_INSERTIONS;
    }
    /**
     * getter/setter
     * @return
     */
    static long getAvgLookUpTime(){
        return lookupTimeTotal/(long)NUM_LOOKUPS;
    }


    public static void exportAvgs() throws IOException {
        PrintWriter writer = new PrintWriter("avgLookUp.txt", "UTF-8");
        System.out.println();
        for(int i = 0;i<lookupAvgs.size();i++){
            writer.println(""+i+", "+lookupAvgs.get(i)/10000);
        }
        writer.flush();
        writer.close();

        writer = new PrintWriter("avgInsert.csv", "UTF-8");
        System.out.println();
        for(int i = 0;(i<insertTimes.size())&&i<500;i++){
            writer.println(""+i+", "+insertTimes.get(i)/10000);
        }
        writer.flush();
        writer.close();
    }
}
