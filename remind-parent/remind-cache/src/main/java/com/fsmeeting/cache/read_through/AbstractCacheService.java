package com.fsmeeting.cache.read_through;

import com.google.common.cache.*;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.TimeUnit;

public abstract class AbstractCacheService<K, V> {

    protected LoadingCache<K, V> cache;

    public AbstractCacheService() {
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .initialCapacity(1000)
                .maximumSize(10000)
                .removalListener(new RemovalListener<K, V>() {
                    @Override
                    public void onRemoval(RemovalNotification<K, V> removalNotification) {
                        System.out.println(removalNotification.getKey() + "[" + removalNotification.getValue() + "] 被删除，删除原因[" +
                                removalNotification.getCause() + "]");
                    }
                })
                .refreshAfterWrite(15, TimeUnit.MINUTES)
                .concurrencyLevel(10)
                .recordStats()
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K k) throws Exception {
                        System.out.println(cache.stats().toString());
                        return loadData(k);
                    }

                    @Override
                    public ListenableFuture<V> reload(K key, V oldValue) throws Exception {
                        return super.reload(key, oldValue);
                    }
                });
    }

    protected abstract V loadData(K k);

    public V getCache(K param) {
        return cache.getUnchecked(param);
    }

    /**
     * Description: 刷新缓存，即重新取缓存数据，更新缓存
     *
     * @Author:yicai.liu<虚竹子>
     */
    public void refresh(K k) {
        cache.refresh(k);
    }

    public LoadingCache<K, V> getCache() {
        return cache;
    }

}