/*
 * Copyright (c) 2014-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant 
 * of patent rights can be found in the PATENTS file in the same directory.
*/

package com.facebook.stetho.okhttp;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Provides easy integration with <a href="http://square.github.io/okhttp/">OkHttp</a> 2.2.0+
 * by way of the new <a href="https://github.com/square/okhttp/wiki/Interceptors">Interceptor</a>
 * system. To use:
 * <pre>
 *   OkHttpClient client = new OkHttpClient();
 *   client.networkInterceptors().add(new StethoInterceptor());
 * </pre>
 */
public class StethoInterceptor implements Interceptor {

  @Override
  public Response intercept(Chain chain) throws IOException {
    return chain.proceed(chain.request());
  }
}
