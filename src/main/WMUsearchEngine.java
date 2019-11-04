package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * This class runs and builds expression that are the input that comes from the user by scanner input or file input.
 */
public class WMUsearchEngine {

	private static MyBestHashMap<String> MAP;

	/**
	 * Starts/Loads/Runs the program
	 * @param args cm args
	 * @throws IOException only thrown on file name is not correct
	 */
	public static void main(String[] args) throws IOException {
		MAP = new MyBestHashMap<>();

		//Load in user input data
		InOut data = new InOut();
		data.loadOps();
		data.loadURLs();

		//build the hash map
		buildIndexes(data,MAP);

		//run the expressions
		GenerateExpression GEN = new GenerateExpression(data);

		HashMapStats.exportAvgs();
		System.exit(0);
	}

	/**
	 * Compares 2 collections of urls by USING AND
	 * We used a hashset in this function because these function
	 * do not interact with the logic or anything thing with our hash map.
	 * We just wanted a effective way of comparing two lists
	 * @param j collection 1
	 * @param k coolection 2
	 * @return all the matches between the two collections
	 */
	private static ArrayList<String> compareToANDTheFastWay(ArrayList<String> j,ArrayList<String> k) {
		HashSet<String> h = new HashSet<>();
		ArrayList<String> search;
		ArrayList<String> matches = new ArrayList<>();

		if(k.size() < j.size()) {
			//add all values of k
			h.addAll(k);
			search = j;
		}else {
			//add all values of j
			h.addAll(j);
			search = k;
		}

		for (String search1 : search) {
			//if h contains the current value then there was a match
			if (h.contains(search1)) {
				matches.add(search1);
			}
		}
		return matches;
	}

	/**
	 * This function combines two collection with removing duplicates
	 * Compares 2 collections of urls by USING AND
	 * We used a hashset in this function because these function
	 * do not interact with the logic or anything thing with our hash map.
	 * We just wanted a effective way of comparing two lists
	 * @param j collection one
	 * @param k coolection two
	 * @return collection one + collection two
	 */
	private static ArrayList<String> compareToORTheFastWay(ArrayList<String> j, ArrayList<String> k){
		ArrayList<String> values = new ArrayList<>();
		HashSet<String> h = new HashSet<>();
		for(int i = 0; i < j.size()+k.size(); i++)
		{
			if(i<j.size()){
				if(!h.contains(j.get(i))) {
					values.add(j.get(i));
					h.add(j.get(i));
				}
			}
			if(i<k.size()){
				if(!h.contains(k.get(i))) {
					values.add(k.get(i));
					h.add(k.get(i));
				}
			}

		}
		return values;
	}

	/**
	 * This is the other way of comparing using our custom hashset
	 * @param j
	 * @param k
	 * @return
	 */
	/*
	public static ArrayList<String> compareToANDTheOtherWay(ArrayList<String> j,ArrayList<String> k){
		MyBestHashMap<String> h = new MyBestHashMap<>();
		ArrayList<String> search;
		ArrayList<String> matches = new ArrayList<>();

		if(k.size() < j.size()) {
			//add all values of k
			for(String ks:k){
				h.put(ks,ks);
			}
			search = j;
		}else {
			//add all values of j
			for(String js:k){
				h.put(js,js);
			}
			search = k;
		}

		for (String search1 : search) {
			//if h contains the current value then there was a match
			if (h.get(search1)!= null) {
				matches.add(search1);
			}
		}
		return matches;
	}*/

	/**
	 * This is the other way of comparing using our custom hashset
	 * @param j
	 * @param k
	 * @return
	 */
	/*
	private static ArrayList<String> compareToORTheOther(ArrayList<String> j, ArrayList<String> k){
		ArrayList<String> values = new ArrayList<>();
		customHashSet<String> h = new customHashSet<>();
		for(int i = 0; i < j.size()+k.size(); i++)
		{
			if(i<j.size()){
				if(h.get(j.get(i)) == null) {
					values.add(j.get(i));
					h.put(j.get(i),j.get(i));
				}
			}
			if(i<k.size()){
				if(h.get(k.get(i)) == null) {
					values.add(k.get(i));
					h.put(k.get(i),k.get(i));
				}
			}
		}
		return values;
	}*/

	/**UNUSED
	 * These are what we started with they are very slow and bad :C
	 * @param j
	 * @param k
	 * @return
	 */
	public static ArrayList<String> compareToANDTheSlowWay(ArrayList<String> j,ArrayList<String> k) {
		ArrayList<String> matches = new ArrayList<>();
		for (String s : j) {
			for (int x = 0; x < k.size(); x++) {
				if (s.equals(k.get(x))) {
					matches.add(s);
				}
			}
		}
		return matches;
	}
	/**UNUSED
	 * These are what we started with they are very slow and bad :C
	 * @param j
	 * @param k
	 * @return
	 */
	public static ArrayList<String> compareToORTheSlowWay(ArrayList<String> j,ArrayList<String> k) {
		ArrayList<String> values = new ArrayList<>();
		for(int i = 0; i < j.size()+k.size(); i++)
		{
			if(i<j.size()){
				values.add(j.get(i));
			}
			if(i<k.size()){
				values.add(k.get(i));
			}
		}
		return values;
	}


	/**
	 * This functions takes in the loaded urls and descriptions and adds entries to the hashmap
	 * @param data urls and descriptions data object
	 * @param h current hashmap
	 */
	private static void buildIndexes(InOut data, MyBestHashMap<String> h) {
		HashMapStats.startRecording();
		Object[] decs = data.getDecs().toArray();

		//go through each through
		for(int i = 0;i<data.getURLs().size();i++) {
			//for each url add its value under a description or key
			String[] split = ((String) decs[i]).split(" ");
			for (String s : split) {
				//add values,key to hash map
				h.put(data.getURLs().get(i), s);
			}
		}
		System.out.println("Index Created");
		HashMapStats.stopRecording(-1);
	}

	/**
	 * Run expression is called from generateExpression call when the stack processor found a ?
	 * Which means we need to find the value for the current expression on the stack
	 * @param Expression current expression
	 */
	static void runExpression(String Expression) {
		System.out.println("Expression: "+Expression);
		ArrayList<String> result1 = new ArrayList<>();
		ArrayList<String> result2 = new ArrayList<>();

		String[] split = Expression.split(" ");
		if(split.length == 0) {printResults(result1);}

		if(split.length == 1) {
			 printResults(MAP.getAllLinks(split[0]));
			 return;
		}
		int counter = 0;
		for (String s : split) {
			if (s.equals("&&") || s.equals("||")) {
				if (s.equals("&&")) {
					result1 = compareToANDTheFastWay(result1, result2);

				} else {
					result1 = compareToORTheFastWay(result1, result2);
				}
			} else {
				if (counter % 2 == 0) {
					result1 = MAP.getAllLinks(s);
				} else {
					result2 = MAP.getAllLinks(s);
				}
				errno.resetErr();
			}
			counter++;
		}
		printResults(result1);
	}

	/**
	 * Prints all the results from a compare (and or) statment
	 * @param results final list of urls
	 */
	private static void printResults(ArrayList<String> results){
		if(results.size() == 0){
			System.out.println("-----------------\nNo Results");
		}else {
			System.out.println("-----------------\nResults");
		}
		for(String link:results){
			System.out.printf("%s\n",link);
		}
		if(errno.getWARN() == 5) {
			System.out.println(errno.getError(5)+"The results returned were retrieved when there was one or more keys that did not exist in the hashtable");
			errno.resetWarn();
			//errno.resetErr();
		}
		System.out.println("-----------------");
	}
}




