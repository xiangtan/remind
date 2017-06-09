package com.fsmeeting.guava.cache;

import com.fsmeeting.model.User;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.graph.Graph;
import com.google.common.util.concurrent.ListenableFuture;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 本地缓存工具
 * @Author: yicai.liu
 * @Date: 10:23 2017/6/9
 */
public class CacheTest {

    @Test
    public void test() {
        Cache<Integer, Graph> cache = CacheBuilder
                .newBuilder()
                //初始化容量
                .initialCapacity(100)
                //基于容量回收
                .maximumSize(10000)
                // 基于引用回收
                .weakKeys()//键弱引用
                //.weakValues()//值弱引用
                .softValues()//值软引用
                //时间回收
                .expireAfterAccess(5, TimeUnit.SECONDS) // 指定时间内没有读写操作
                .expireAfterWrite(5, TimeUnit.SECONDS)  // 指定时间内没有写操作
                // 删除回调操作
                .removalListener(new RemovalListener<Integer, Graph>() {
                    @Override
                    public void onRemoval(RemovalNotification<Integer, Graph> removalNotification) {
                        RemovalCause cause = removalNotification.getCause();
                        Integer key = removalNotification.getKey();
                        Graph value = removalNotification.getValue();
                        System.out.println(key + "[" + value + "] 被删除，删除原因[" + cause + "]");
                    }
                })
                //流控，写入操作限制最大并发
                .concurrencyLevel(10)
                //开启统计功能：缓存命中率、加载新值的平均时间、缓存项被懂回收的总数（不包括显示清理）
                .recordStats()
                //构建cache实例
                .build(new CacheLoader<Integer, Graph>() {

                    @Override
                    public Graph load(Integer id) throws Exception {
                        return null;
                    }

                    @Override
                    public ListenableFuture<Graph> reload(Integer key, Graph oldValue) throws Exception {
                        return super.reload(key, oldValue);
                    }
                });

        //显示清理
        //cache.invalidate(1); //某个key
        //cache.invalidateAll(list); //部分key
        //cache.invalidateAll(); // 全部key
    }

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        LoadingCache<Integer, User> userCache = CacheBuilder.newBuilder()
                .concurrencyLevel(8)
                .expireAfterWrite(8, TimeUnit.SECONDS)
                .initialCapacity(10)
                .maximumSize(100)
                .recordStats()
                .removalListener(new RemovalListener<Integer, User>() {
                    @Override
                    public void onRemoval(RemovalNotification<Integer, User> notification) {
                        System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause());
                    }
                })
                //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
                .build(new CacheLoader<Integer, User>() {
                           @Override
                           public User load(Integer key) throws Exception {
                               System.out.println("load User " + key);
                               return loadStorage(key);
                           }
                       }
                );

        for (int i = 0; i < 20; i++) {
            //从缓存中得到数据，由于我们没有设置过缓存，所以需要通过CacheLoader加载缓存数据
            User user = userCache.get(1);
            System.out.println(user);
            //休眠1秒
            TimeUnit.SECONDS.sleep(1);
        }

        //最后打印缓存的命中率等 情况
        System.out.println(userCache.stats().toString());
    }

    private User loadStorage(Integer userId) {
        User user = new User();
        user.setId(userId);
        user.setName("name " + userId);
        return user;
    }
}
