/*
 * Copyright (c) 2014-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package com.facebook.stetho.urlconnection;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Individual connection flow manager that aids in communicating network events to Stetho
 * via the {@link com.facebook.stetho.inspector.network.NetworkEventReporter} API.  This class is
 * stateful and should be instantiated for each individual HTTP request.
 * <p>
 * Be aware that there are caveats with inspection using {@link HttpURLConnection} on Android:
 * <ul>
 * <li>Compressed payload sizes are typically not available, even when compression was in use over
 * the wire.
 * <li>Redirects are by default handled internally, making it impossible to visualize them.
 * To visualize them, redirects must be handled manually by invoking
 * {@link HttpURLConnection#setFollowRedirects(boolean)}.
 * </ul>
 */
public class StethoURLConnectionManager {

  public StethoURLConnectionManager(String friendlyName) {
  }

  public boolean isStethoEnabled() {
    return false;
  }

  /**
   * Indicates that the {@link HttpURLConnection} instance has been configured and is about
   * to be used to initiate an actual HTTP connection.  Call this method before any of the
   * active methods such as {@link HttpURLConnection#connect()},
   * {@link HttpURLConnection#getInputStream()}, or {@link HttpURLConnection#getOutputStream()}
   *
   * @param connection Connection instance configured with a method and headers.
   * @param requestEntity Represents the request body if the request method supports it.
   */
  public void preConnect(
      HttpURLConnection connection,
      SimpleRequestEntity requestEntity) {
  }

  /**
   * Indicates that the {@link HttpURLConnection} has just successfully exchanged HTTP messages
   * (request headers + body and response headers) with the server but has not yet consumed
   * the response body.
   *
   * @throws IOException May throw an exception internally due to {@link HttpURLConnection}
   *     method signatures.  The request should be considered aborted/failed if this method
   *     throws.
   */
  public void postConnect() throws IOException {
  }

  /**
   * Indicates that there was a non-recoverable failure during HTTP message exchange at some
   * point between {@link #preConnect} and {@link #interpretResponseStream}.
   *
   * @param ex Relay the exception that was thrown from {@link java.net.HttpURLConnection}
   */
  public void httpExchangeFailed(IOException ex) {
  }

  /**
   * Deliver the response stream from {@link HttpURLConnection#getInputStream()} to
   * Stetho so that it can be intercepted.  Note that compression is transparently
   * supported on modern Android systems and no special awareness is necessary for
   * gzip compression on the wire.  Unfortunately this means that it is sometimes impossible
   * to determine whether compression actually occurred and so Stetho may report inflated
   * byte counts.
   * <p>
   * If the {@code Content-Length} header is provided by the server, this will be assumed to be
   * the raw byte count on the wire.
   *
   * @param responseStream Stream as furnished by {@link HttpURLConnection#getInputStream()}.
   *
   * @return The filtering stream which is to be read after this method is called.
   */
  public InputStream interpretResponseStream(InputStream responseStream) {
    return new InputStream() {
      @Override
      public int read() throws IOException {
        return 0;
      }
    };
  }

  /**
   * Convenience method to access the lower level
   * {@link com.facebook.stetho.inspector.network.NetworkEventReporter} API (must be explicitly
   * cast).
   *
   * @deprecated This should no longer be used as it could potentially break the mechanism
   *     we use to allow convenient stripping of Stetho from release builds when using this
   *     module.  If you need access to this, consider writing your own custom version of this
   *     module.
   */
  @Deprecated
  public Object getStethoHook() {
    return null;
  }

  /**
   * Low level method to access this request's unique identifier according to
   * {@link com.facebook.stetho.inspector.network.NetworkEventReporter}.  Most callers won't
   * need this.
   */
  public String getStethoRequestId() {
      return null;
  }
}
