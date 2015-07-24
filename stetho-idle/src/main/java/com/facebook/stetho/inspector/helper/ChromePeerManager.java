/*
 * Copyright (c) 2014-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package com.facebook.stetho.inspector.helper;

/**
 * Interface glue that allows a particular domain to manage the enabled peers.  The way the
 * WebKit inspector protocol works is that each functionality domain has an enable/disable JSON-RPC
 * method call which alerts the server (that's us) that we can now begin sending local events
 * to the peer to have them appear in the inspector UI.  This class simplifies managing those
 * enabled peers for each functionality domain.
 */
public class ChromePeerManager {
  public ChromePeerManager() {
  }
}
