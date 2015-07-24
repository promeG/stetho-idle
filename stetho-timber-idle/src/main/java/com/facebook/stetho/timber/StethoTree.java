/*
 * Copyright (c) 2014-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package com.facebook.stetho.timber;

import android.util.Log;

import timber.log.Timber;

/**
 * Timber tree implementation which forwards logs to the Chrome Dev console.
 * Plant it using {@link Timber#plant(Timber.Tree)}
 * <pre>
 *   {@code
 *   Timber.plant(new StethoTree())
 *   }
 * </pre>
 */
public class StethoTree extends Timber.Tree {

  /**
   * Write a log message to its destination. Called for all level-specific methods by default.
   *
   * @param priority Log level. See {@link Log} for constants.
   * @param tag      Explicit or inferred tag. May be {@code null}.
   * @param message  Formatted log message. May be {@code null}, but then {@code t} will not be.
   * @param t        Accompanying exceptions. May be {@code null}, but then {@code message} will
   *                 not be.
   */
  @Override
  protected void log(int priority, String tag, String message, Throwable t) {

  }
}
