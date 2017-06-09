package com.fsmeeting.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public abstract class BaseCacheService<K, V> {
    private LoadingCache<K, V> cache;

    public BaseCacheService() {
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K k) throws Exception {
                        return loadData(k);
                    }
                });
    }

    public BaseCacheService(long duration) {
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(duration, TimeUnit.MINUTES)
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K k) throws Exception {
                        return loadData(k);
                    }
                });
    }

    protected abstract V loadData(K k);

    public V getCache(K param) {
        return cache.getUnchecked(param);
    }

    //更新缓存中数据
    public void refresh(K k) {
        cache.refresh(k);
    }
}