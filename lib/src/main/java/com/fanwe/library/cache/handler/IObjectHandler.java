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
package com.fanwe.library.cache.handler;

import com.fanwe.library.cache.IEncryptConverter;

/**
 * Created by zhengjun on 2017/8/31.
 */
public interface IObjectHandler<T>
{
    void setEncrypt(boolean encrypt);

    void setEncryptConverter(IEncryptConverter encryptConverter);

    boolean hasObject(String key);

    boolean putObject(String key, T object);

    T getObject(String key);

    boolean removeObject(String key);
}