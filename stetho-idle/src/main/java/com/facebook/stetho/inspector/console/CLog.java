/*
 * Copyright (c) 2014-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package com.facebook.stetho.inspector.console;

import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.protocol.module.Console;

/**
 * Utility for reporting an event to the console
 */
public class CLog {
  public static void writeToConsole(
      ChromePeerManager chromePeerManager,
      Console.MessageLevel logLevel,
      Console.MessageSource messageSource,
      String messageText) {
  }

  public static void writeToConsole(
      Console.MessageLevel logLevel,
      Console.MessageSource messageSource,
      String messageText
  ) {

  }
}
