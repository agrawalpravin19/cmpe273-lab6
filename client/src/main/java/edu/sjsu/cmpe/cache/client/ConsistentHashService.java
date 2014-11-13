package edu.sjsu.cmpe.cache.client;

import java.util.ArrayList;

import com.google.common.hash.Hashing;

public class ConsistentHashService implements CacheServiceInterface {
	
	private ArrayList<DistributedCacheService> servers = new ArrayList<DistributedCacheService>();
	
	public void addServer(String serverURL) {
		servers.add(new DistributedCacheService(serverURL));
	}
	
	public void removeServer(String serverURL) {
		servers.remove(new DistributedCacheService(serverURL));
	}
	
	@Override
	public String get(long key) {
		return servers.get(getBucket(key)).get(key) +" from server: "+ servers.get(getBucket(key)).getServerURL();
	}

	@Override
	public void put(long key, String value) {
		servers.get(getBucket(key)).put(key, value);
		System.out.println(key+"=>"+value+" added to server: "+servers.get(getBucket(key)).getServerURL());
	}
	
	public int getBucket(long key){
		return Hashing.consistentHash(Hashing.md5().hashLong(key), servers.size());
	}

}
