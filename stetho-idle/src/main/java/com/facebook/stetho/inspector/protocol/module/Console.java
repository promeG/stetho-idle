/*
 * Copyright (c) 2014-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;

import android.annotation.SuppressLint;

public class Console implements ChromeDevtoolsDomain {
  public Console() {
  }

  @SuppressLint({ "UsingDefaultJsonDeserializer", "EmptyJsonPropertyUse" })
  public static class MessageAddedRequest {
    public ConsoleMessage message;
  }

  @SuppressLint({ "UsingDefaultJsonDeserializer", "EmptyJsonPropertyUse" })
  public static class ConsoleMessage {
    public MessageSource source;

    public MessageLevel level;

    public String text;
  }

  public enum MessageSource {
    XML("xml"),
    JAVASCRIPT("javascript"),
    NETWORK("network"),
    CONSOLE_API("console-api"),
    STORAGE("storage"),
    APPCACHE("appcache"),
    RENDERING("rendering"),
    CSS("css"),
    SECURITY("security"),
    OTHER("other");

    private final String mProtocolValue;

    private MessageSource(String protocolValue) {
      mProtocolValue = protocolValue;
    }

    public String getProtocolValue() {
      return mProtocolValue;
    }
  }

  public enum MessageLevel {
    LOG("log"),
    WARNING("warning"),
    ERROR("error"),
    DEBUG("debug");

    private final String mProtocolValue;

    private MessageLevel(String protocolValue) {
      mProtocolValue = protocolValue;
    }

    public String getProtocolValue() {
      return mProtocolValue;
    }
  }

  @SuppressLint({ "UsingDefaultJsonDeserializer", "EmptyJsonPropertyUse" })
  public static class CallFrame {
    public String functionName;

    public String url;

    public int lineNumber;

    public int columnNumber;

    public CallFrame() {
    }

    public CallFrame(String functionName, String url, int lineNumber, int columnNumber) {
      this.functionName = functionName;
      this.url = url;
      this.lineNumber = lineNumber;
      this.columnNumber = columnNumber;
    }
  }
}
