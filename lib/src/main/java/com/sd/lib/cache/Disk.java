package com.sd.lib.cache;

import java.io.Serializable;

public interface Disk
{
    /**
     * 设置保存缓存的时候是否加密
     *
     * @param encrypt
     * @return
     */
    Disk setEncrypt(boolean encrypt);

    /**
     * 设置是否支持内存存储
     *
     * @param memorySupport
     * @return
     */
    Disk setMemorySupport(boolean memorySupport);

    /**
     * 设置加解密转换器
     *
     * @param encryptConverter
     * @return
     */
    Disk setEncryptConverter(EncryptConverter encryptConverter);

    /**
     * 设置对象转换器
     *
     * @param objectConverter
     * @return
     */
    Disk setObjectConverter(ObjectConverter objectConverter);

    /**
     * 设置异常处理对象
     *
     * @param exceptionHandler
     * @return
     */
    Disk setExceptionHandler(ExceptionHandler exceptionHandler);

    /**
     * 检查目录是否可用
     *
     * @return
     */
    boolean checkDirectory();

    /**
     * 返回当前目录下所有缓存文件的总大小(字节B)
     *
     * @return
     */
    long size();

    /**
     * 删除该目录以及目录下的所有缓存
     */
    void delete();

    //---------- cache start ----------

    CommonCache<Integer> cacheInteger();

    CommonCache<Long> cacheLong();

    CommonCache<Float> cacheFloat();

    CommonCache<Double> cacheDouble();

    CommonCache<Boolean> cacheBoolean();

    CommonCache<String> cacheString();

    SerializableCache cacheSerializable();

    ObjectCache cacheObject();

    //---------- cache end ----------

    /**
     * 通用缓存接口
     */
    interface CommonCache<T>
    {
        /**
         * 放入缓存对象
         *
         * @param key
         * @param value
         * @return
         */
        boolean put(String key, T value);

        /**
         * 返回key对应的缓存
         *
         * @param key
         * @return
         */
        T get(String key);

        /**
         * 返回key对应的缓存
         *
         * @param key
         * @param defaultValue 如果获取的缓存为null或者不存在，则返回这个值
         * @return
         */
        T get(String key, T defaultValue);
    }

    /**
     * 对象缓存接口
     */
    interface ObjectCache
    {
        boolean put(Object value);

        <T> T get(Class<T> clazz);

        boolean remove(Class clazz);
    }

    /**
     * 序列化缓存接口
     */
    interface SerializableCache
    {
        <T extends Serializable> boolean put(T value);

        <T extends Serializable> T get(Class<T> clazz);

        <T extends Serializable> boolean remove(Class<T> clazz);
    }

    /**
     * 对象转换器
     */
    interface ObjectConverter
    {
        /**
         * 对象转byte
         *
         * @param object
         * @return
         */
        byte[] objectToByte(Object object);

        /**
         * byte转对象
         *
         * @param bytes
         * @param clazz
         * @param <T>
         * @return
         */
        <T> T byteToObject(byte[] bytes, Class<T> clazz);
    }

    /**
     * 加解密转换器
     */
    interface EncryptConverter
    {
        /**
         * 加密数据
         *
         * @param bytes
         * @return
         */
        byte[] encrypt(byte[] bytes);

        /**
         * 解密数据
         *
         * @param bytes
         * @return
         */
        byte[] decrypt(byte[] bytes);
    }

    /**
     * 异常处理类
     */
    interface ExceptionHandler
    {
        void onException(Exception e);
    }
}