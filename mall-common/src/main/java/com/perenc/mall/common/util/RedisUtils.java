package com.perenc.mall.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtils
 * @Description //TODO redis服务接口，通过配置文件注入
 * @Author GR
 * @Date 2017/4/30 23:24
 */
public class RedisUtils<K, V> {

    @Autowired
    private RedisTemplate<K, V> redisTemplate;

    //默认有效时间
    private final long DEFAULT_EXPIRE_TIME = 1 * 24 * 60 * 60;

    /**
     * @param key   缓存Key值
     * @param value 缓存Value值（Object）
     * @Description //TODO 存储一个对象，默认永久存活
     * @Return void
     * @Throws
     * @Author GR
     * @Date 2017/4/30 23:54
     */
    public void set(K key, V value) {
        BoundValueOperations<K, V> kvBoundValueOperations = redisTemplate.boundValueOps(key);
        kvBoundValueOperations.set(value);
    }

    /**
     * @param key     缓存key值
     * @param value   缓存value值(object对象)
     * @param timeOut 缓存有效时间,单位秒,小于等于0表示永久缓存
     * @Description //TODO 存储一个对象，并指定存活时间
     * @Return void
     * @Throws
     * @Author GR
     * @Date 2017/5/1 15:28
     */
    public void set(K key, V value, long timeOut) {
        BoundValueOperations<K, V> kvBoundValueOperations = redisTemplate.boundValueOps(key);
        kvBoundValueOperations.set(value);
        if (timeOut > 0) {
            redisTemplate.expire(key, timeOut, TimeUnit.SECONDS);
        }
    }

    /**
     * @param key
     * @param value
     * @param expiredDate 失效日期
     * @Description //TODO 存储一个对象，并指定失效时间点
     * @Return void
     * @Throws
     * @Author GR
     * @Date 2017/5/1 15:31
     */
    public void set(K key, V value, Date expiredDate) {
        BoundValueOperations<K, V> kvBoundValueOperations = redisTemplate.boundValueOps(key);
        kvBoundValueOperations.set(value);
        redisTemplate.expireAt(key, expiredDate);
    }

    /**
     * @param key 缓存的key值
     * @Description //TODO 获取存储的对象
     * @Return V
     * @Throws
     * @Author GR
     * @Date 2017/5/1 15:34
     */
    public V get(K key) {
        BoundValueOperations<K, V> kvBoundValueOperations = redisTemplate.boundValueOps(key);
        return null == kvBoundValueOperations ? null : kvBoundValueOperations.get();
    }

    /**
     * @param key
     * @param value
     * @Description //TODO 往LIST里面的头部插入一条数据,如果队列不存在，会创建一个队列,默认永久保存
     * @Return void
     * @Throws
     * @Author GR
     * @Date 2017/5/1 15:39
     */
    public void leftPushList(K key, V value) {
        BoundListOperations<K, V> kvBoundListOperations = redisTemplate.boundListOps(key);
        kvBoundListOperations.leftPush(value);
    }

    /**
     * @param key
     * @param values
     * @Description //TODO 往LIST里面的头部插入多条数据,如果队列不存在，会创建一个队列,默认永久保存
     * @Return void
     * @Throws
     * @Author GR
     * @Date 2017/5/1 15:41
     */
    public void leftPushListAll(K key, V... values) {
        if (null == values || values.length < 1) {
            return;
        }
        BoundListOperations<K, V> kvBoundListOperations = redisTemplate.boundListOps(key);
        kvBoundListOperations.leftPushAll(values);
    }

    /**
     * @param key
     * @param value
     * @Description //TODO 往LIST里面追加一条数据,如果队列不存在，会创建一个队列,默认永久保存
     * @Return void
     * @Throws
     * @Author GR
     * @Date 2017/5/1 15:45
     */
    public void rightPushList(K key, V value) {
        BoundListOperations<K, V> kvBoundListOperations = redisTemplate.boundListOps(key);
        kvBoundListOperations.rightPush(value);
    }

    /**
     * @param key
     * @param values
     * @Description //TODO 往LIST里面追加多条数据,如果队列不存在，会创建一个队列,默认永久保存
     * @Return void
     * @Throws
     * @Author GR
     * @Date 2017/5/1 15:48
     */
    @SuppressWarnings("unchecked")
    public void rightPushListAll(K key, V... values) {
        if (null == values || values.length < 1) {
            return;
        }
        BoundListOperations<K, V> kvBoundListOperations = redisTemplate.boundListOps(key);
        kvBoundListOperations.rightPushAll(values);
    }

    /**
     * @param key
     * @param index
     * @Description //TODO 通过索引获取列表中的元素
     * @Return V
     * @Throws
     * @Author GR
     * @Date 2017/5/1 15:55
     */
    public V index(K key, long index) {
        if (null == key) {
            return null;
        }
        BoundListOperations<K, V> kvBoundListOperations = redisTemplate.boundListOps(key);
        return kvBoundListOperations.index(index);
    }

    /**
     * @param key
     * @param start
     * @param end
     * @Description //TODO 获取列表指定范围内的元素
     * @Return java.util.List<V>
     * @Throws
     * @Author GR
     * @Date 2017/5/1 15:59
     */
    public List<V> range(K key, long start, long end) {
        BoundListOperations<K, V> kvBoundListOperations = redisTemplate.boundListOps(key);
        return kvBoundListOperations.range(start, end);
    }

    /**
     * @param key
     * @Description //TODO 获取列表长度
     * @Return long
     * @Throws
     * @Author GR
     * @Date 2017/5/1 16:00
     */
    public long length(K key) {
        if (key == null) {
            return 0L;
        }
        BoundListOperations<K, V> kvBoundListOperations = redisTemplate.boundListOps(key);
        return kvBoundListOperations.size();
    }

    /**
     * @param key
     * @param value
     * @Description //TODO 删除列表中所有跟value相同的值
     * @Return void
     * @Throws
     * @Author GR
     * @Date 2017/5/1 16:00
     */
    public void remove(K key, V value) {
        if (key == null) {
            return;
        }
        BoundListOperations<K, V> kvBoundListOperations = redisTemplate.boundListOps(key);
        kvBoundListOperations.remove(0, value);
    }

    /**
     * @param key
     * @param field
     * @param value
     * @Description //TODO 存储一对键值对到hash里面，没有的话会创建，默认永久。
     * @Return void
     * @Throws
     * @Author GR
     * @Date 2017/5/1 16:03
     */
    public void hSet(K key, K field, V value) {
        if (key == null || field == null) {
            return;
        }
        BoundHashOperations<K, K, V> kkVBoundHashOperations = redisTemplate.boundHashOps(key);
        kkVBoundHashOperations.put(field, value);
    }

    /**
     * @param key
     * @param fvs
     * @Description //TODO 存储多个键值对到hash里面，没有的话会创建，默认永久。
     * @Return void
     * @Throws
     * @Author GR
     * @Date 2017/5/1 16:03
     */
    public void hmSet(K key, Map<? extends K, ? extends V> fvs) {
        if (key == null || fvs == null) {
            return;
        }
        BoundHashOperations<K, K, V> kkVBoundHashOperations = redisTemplate.boundHashOps(key);
        kkVBoundHashOperations.putAll(fvs);

    }

    /**
     * @param key
     * @Description //TODO 删除缓存
     * @Return void
     * @Throws
     * @Author GR
     * @Date 2017/5/1 16:04
     */
    public void delete(final K key) {
        if (key == null || "".equals(key)) {
            return;
        }
        redisTemplate.delete(key);
    }

    /**
     * @param key
     * @Description //TODO 判断缓存中是否存在某个key
     * @Return boolean
     * @Throws
     * @Author GR
     * @Date 2017/5/1 16:05
     */
    public boolean hasKey(K key) {
        if (key == null || "".equals(key)) {
            return false;
        }
        return redisTemplate.hasKey(key);
    }

    /**
     * @param key
     * @param fileds
     * @Description //TODO 获取所有给定字段的值
     * @Return java.util.List<V>
     * @Throws
     * @Author GR
     * @Date 2017/5/1 16:06
     */
    public List<V> hmGet(K key, List<K> fileds) {
        if (key == null || fileds == null || fileds.size() < 1) {
            return null;
        }
        BoundHashOperations<K, K, V> kkVBoundHashOperations = redisTemplate.boundHashOps(key);
        return kkVBoundHashOperations.multiGet(fileds);
    }

    /**
     * @param key
     * @Description //TODO 获取在哈希表中指定 key 的所有字段和值
     * @Return java.util.Map<K                                                                                                                                                                                                                                                               V>
     * @Throws
     * @Author GR
     * @Date 2017/5/1 16:06
     */
    public Map<K, V> hGetAll(K key) {
        if (key == null) {
            return null;
        }
        BoundHashOperations<K, K, V> kkVBoundHashOperations = redisTemplate.boundHashOps(key);
        return kkVBoundHashOperations.entries();
    }

    /**
     * @param key
     * @Description //TODO 获取哈希表中字段的数量
     * @Return long
     * @Throws
     * @Author GR
     * @Date 2017/5/1 16:07
     */
    public long hLength(K key) {
        if (key == null) {
            return 0;
        }
        BoundHashOperations<K, K, V> kkVBoundHashOperations = redisTemplate.boundHashOps(key);
        return kkVBoundHashOperations.size();
    }

    /**
     * @param key
     * @param field
     * @Description //TODO 获取存储在哈希表中指定字段的值。
     * @Return V
     * @Throws
     * @Author GR
     * @Date 2017/5/1 16:08
     */
    public V hGet(final K key, final K field) {
        if (key == null || field == null) {
            return null;
        }
        BoundHashOperations<K, K, V> kkVBoundHashOperations = redisTemplate.boundHashOps(key);
        return kkVBoundHashOperations.get(field);
    }

    /**
     * @param key
     * @param field
     * @Description //TODO 删除hash的字段
     * @Return void
     * @Throws
     * @Author GR
     * @Date 2017/5/1 16:08
     */
    public void hdeleteFiled(K key, K field) {
        if (key == null || field == null) {
            return;
        }
        BoundHashOperations<K, K, V> kkVBoundHashOperations = redisTemplate.boundHashOps(key);
        kkVBoundHashOperations.delete(field);
    }

    /**
     * @param key
     * @param list
     * @Description //TODO 保存List类型数据,存活时间为24小时
     * @Return void
     * @Throws
     * @Author GR
     * @Date 2017/5/1 16:21
     */
    public void pushList(final K key, final List<V> list) {
        redisTemplate.execute(new SessionCallback<V>() {
            public V execute(RedisOperations operations) throws DataAccessException {
                //监控此键
                operations.watch(key);
                //开启事务
                operations.multi();
                operations.delete(key);
                BoundListOperations boundListOperations = operations.boundListOps(key);
                for (V v : list) {
                    boundListOperations.leftPush(v);
                }
                operations.expire(key, DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
                //提交事务
                operations.exec();
                return null;
            }
        });
    }

    /**
     * @param key
     * @param list
     * @param timeOut 存活时间
     * @Description //TODO 保存list类型数据，并设置存活时间
     * @Return void
     * @Throws
     * @Author GR
     * @Date 2017/5/1 16:40
     */
    @SuppressWarnings("unchecked")
    public void putshList(final K key, final List<V> list, final long timeOut) {
        redisTemplate.execute(new SessionCallback<V>() {
            public V execute(RedisOperations operations) throws DataAccessException {
                operations.watch(rawKey(key));
                operations.multi();
                operations.delete(key);
                BoundListOperations<K, Object> listOper = operations.boundListOps(key);
                for (Object object : list) {
                    listOper.leftPush(object);
                }
                operations.expire(key, timeOut, TimeUnit.SECONDS);
                operations.exec();
                return null;
            }
        });
    }

    /**
     * @Param expiredDate 到期时间
     * @Param list
     * @Param key
     * @Description //TODO 保存list类型数据,并设置到期时间点
     * @Return void
     * @Throws
     * @Author GR
     * @Date 2017/5/1 16:40
     */
    public void pushList(final K key, final List<V> list, final Date expiredDate) {
        if (list == null) {
            return;
        }
        redisTemplate.execute(new SessionCallback<Object>() {
            @SuppressWarnings("unchecked")
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.watch(rawKey(key));
                operations.multi();
                operations.delete(key);
                BoundListOperations<K, V> kvBoundListOperations = operations.boundListOps(key);
                for (V v : list) {
                    kvBoundListOperations.rightPush(v);
                }
                operations.expireAt(key, expiredDate);
                operations.exec();
                return null;
            }
        });
    }

    /**
     * @param key
     * @Description //TODO 获取List值
     * @Return java.util.List
     * @Throws
     * @Author GR
     * @Date 2017/5/1 16:43
     */
    public List<V> getList(K key) {

        BoundListOperations<K, V> kvBoundListOperations = redisTemplate.boundListOps(key);
        if (kvBoundListOperations == null) {
            return null;
        }
        return kvBoundListOperations.range(0, -1);
    }

    public void remove(K key) {
        redisTemplate.delete(key);
    }

    public void remove(Collection<K> ks) {
        redisTemplate.delete(ks);
    }

    /**
     * @param key
     * @Description //TODO
     * @Return long
     * @Throws
     * @Author GR
     * @Date 2017/5/1 17:04
     */
    public long getExpire(K key) {
        return redisTemplate.getExpire(key);
    }

    public boolean containsKey(K key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 保存Set类型数据
     *
     * @param key     存储key
     * @param value   存储值
     * @param timeOut 存活时间,单位秒
     * @Throws
     * @Author GR
     * @Date 2017/5/1 18:12
     */
    @SuppressWarnings("unchecked")
    public void putSet(final K key, final V value, final long timeOut) {
        redisTemplate.execute(new SessionCallback<Object>() {
            public Object execute(RedisOperations operations) throws DataAccessException {
                BoundSetOperations<K, V> setOper = operations.boundSetOps(key);
                operations.watch(rawKey(key));
                operations.multi();
                operations.delete(key);
                setOper.add(value);
                operations.expire(key, timeOut, TimeUnit.SECONDS);
                operations.exec();
                return null;
            }
        });
    }

    /**
     * @param key
     * @param value
     * @param expiredDate
     * @Description //TODO 保存Set类型数据,并设置到期时间点
     * @Return void
     * @Throws
     * @Author GR
     * @Date 2017/5/1 16:23
     */
    @SuppressWarnings("unchecked")
    public void putSet(final K key, final V value, final Date expiredDate) {
        redisTemplate.execute(new SessionCallback<Object>() {
            public Object execute(RedisOperations operations) throws DataAccessException {
                BoundSetOperations<K, V> setOper = operations.boundSetOps(key);
                operations.watch(rawKey(key));
                operations.multi();
                operations.delete(key);
                setOper.add(value);
                operations.expireAt(key, expiredDate);
                operations.exec();
                return null;
            }
        });
    }

    /**
     * 保存set类型的值，默认有效期1天
     *
     * @param key
     * @param value
     * @Throws
     * @Author GR
     * @Date 2017/5/1 17:33
     */
    @SuppressWarnings("unchecked")
    public void pushSet(final K key, final V value) {
        putSet(key, value, DEFAULT_EXPIRE_TIME);
    }

    /**
     * 获取Set类型值
     *
     * @param key
     * @return
     * @Throws
     * @Author GR
     * @Date 2017/5/1 17:55
     */
    public Set<V> getSet(K key) {
        BoundSetOperations<K, V> kvBoundSetOperations = redisTemplate.boundSetOps(key);
        return kvBoundSetOperations.members();
    }

    /**
     * @param key
     * @param value
     * @Description //TODO
     * @Return void
     * @Throws
     * @Author GR
     * @Date 2017/5/1 16:49
     */
    public void pushHash(final K key, final Map<? extends V, ? extends Object> value) {
        redisTemplate.execute(new SessionCallback<Object>() {
            @SuppressWarnings("unchecked")
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.watch(rawKey(key));
                operations.multi();
                operations.delete(key);
                BoundHashOperations<K, Object, Object> hashOper = redisTemplate.boundHashOps(key);
                hashOper.putAll(value);
                operations.expire(key, DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
                operations.exec();
                return null;
            }
        });
    }

    /**
     * 获取hash值
     *
     * @param key
     * @return 获取结果为hash类型的值
     * @Throws
     * @Author GR
     * @Date 2017/5/1 16:32
     */
    public Map<Object, Object> getHash(K key) {
        BoundHashOperations<K, Object, Object> hashOper = redisTemplate.boundHashOps(key);
        if (hashOper == null) {
            return null;
        }
        return hashOper.entries();
    }

    public long incrementBy(final K key, final long incrBy, final long ttl) {
        Object obj = redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                long ret = connection.incrBy(rawKey(key), incrBy);
                connection.expire(rawKey(key), ttl);
                return ret;
            }
        });
        return (Long) obj;
    }

    public long decrement(final K key, final long ttl) {
        Object obj = redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                long ret = connection.decr(rawKey(key));
                connection.expire(rawKey(key), ttl);
                return ret;
            }
        });
        return (Long) obj;
    }

    private byte[] rawKey(K key) {
        return key.toString().getBytes();
    }

    public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

}
