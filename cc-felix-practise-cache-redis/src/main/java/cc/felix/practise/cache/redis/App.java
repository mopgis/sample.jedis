package cc.felix.practise.cache.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class App {

	public static void main(String[] args) {
		testJedisPool();
	}
	
	public static void testJedis(){
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.set("singleJedis", "hello jedis!");
		System.out.println(jedis.get("singleJedis"));
		jedis.close();
	}

	public static void testJedisPool(){
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
	    jedisPoolConfig.setMaxTotal(10);
	    JedisPool pool = new JedisPool(jedisPoolConfig, "localhost", 6379);

	    Jedis jedis = null;
	    try{
	        jedis = pool.getResource();
	        jedis.set("pooledJedis", "hello jedis pool!");
	        System.out.println(jedis.get("pooledJedis"));
	    }catch(Exception e){
	        e.printStackTrace();
	    }finally {
	        //还回pool中
	        if(jedis != null){
	            jedis.close();
	        }
	    }

	    pool.close();
	}
}
