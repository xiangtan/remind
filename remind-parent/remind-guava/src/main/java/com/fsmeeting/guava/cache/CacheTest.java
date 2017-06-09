package com.fsmeeting.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 本地缓存工具
 * @Author: yicai.liu
 * @Date: 10:23 2017/6/9
 */
public class CacheTest {

    @Test
    public void test() {
        Cache<Integer, Object> cache = CacheBuilder
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
                .removalListener(new RemovalListener<Integer, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<Integer, Object> removalNotification) {
                        RemovalCause cause = removalNotification.getCause();
                        Integer key = removalNotification.getKey();
                        Object value = removalNotification.getValue();
                        System.out.println(key + "[" + value + "] 被删除，删除原因[" + cause + "]");
                    }
                })
                //流控，写入操作限制最大并发
                .concurrencyLevel(10)
                //开启统计功能：缓存命中率、加载新值的平均时间、缓存项被懂回收的总数（不包括显示清理）
                .recordStats()
                //构建cache实例
                .build(new CacheLoader<Integer, Object>() {

                    @Override
                    public Object load(Integer id) throws Exception {
                        return null;
                    }
                });

        //显示清理
        //cache.invalidate(1); //某个key
        //cache.invalidateAll(list); //部分key
        //cache.invalidateAll(); // 全部key
    }
}
