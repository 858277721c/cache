/*
 * Copyright (C) 2017 zhengjun, fanwe (http://www.fanwe.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fanwe.lib.cache.handler;

import com.fanwe.lib.cache.Disk;
import com.fanwe.lib.cache.DiskInfo;

/**
 * Object处理类
 */
public class ObjectHandler extends ByteConverterHandler<Object> implements Disk.ObjectCache
{
    public ObjectHandler(DiskInfo diskInfo)
    {
        super(diskInfo);
    }

    @Override
    protected byte[] valueToByte(Object value)
    {
        final Disk.ObjectConverter converter = getDiskInfo().getObjectConverter();
        checkObjectConverter(converter);
        return converter.objectToByte(value);
    }

    @Override
    protected Object byteToValue(byte[] bytes, Class clazz)
    {
        final Disk.ObjectConverter converter = getDiskInfo().getObjectConverter();
        checkObjectConverter(converter);
        return converter.byteToObject(bytes, clazz);
    }

    @Override
    protected String getKeyPrefix()
    {
        return "object_";
    }

    private void checkObjectConverter(Disk.ObjectConverter converter)
    {
        if (converter == null)
            throw new NullPointerException("you must provide an ObjectConverter instance before this");
    }

    @Override
    public boolean put(Object value)
    {
        final String key = value.getClass().getName();
        return putCache(key, value);
    }

    @Override
    public <T> T get(Class<T> clazz)
    {
        final String key = clazz.getName();
        return (T) getCache(key, clazz);
    }

    @Override
    public boolean remove(Class clazz)
    {
        final String key = clazz.getName();
        return removeCache(key);
    }
}
