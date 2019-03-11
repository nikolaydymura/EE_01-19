package edu.ee.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CacheRepository {
  @Autowired
  private RedisTemplate<String, String> template;

  @Resource(name = "redisTemplate")
  private ListOperations<String, String> listOps;

  public void put(String key, String data) {
    listOps.leftPush(key, data);
  }

  public String get(String key) {
    return listOps.leftPop(key);
  }
}
