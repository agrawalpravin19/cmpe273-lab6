package edu.sjsu.cmpe.cache.client;

public class Client {

    public static void main(String[] args) throws Exception {
	
    	ConsistentHashService consistentHash = new ConsistentHashService();
    	consistentHash.addServer("http://localhost:3000");
    	consistentHash.addServer("http://localhost:3001");
    	consistentHash.addServer("http://localhost:3002");
    	
    	char value = 'a';
    	
    	for(int i=1; i<11; i++, value++) {
    		consistentHash.put(i, Character.toString(value));
    	}
    	
    	for(int i=1; i<11; i++) {
    		System.out.println(i+"=>"+consistentHash.get(i));
    	}
    }

}
