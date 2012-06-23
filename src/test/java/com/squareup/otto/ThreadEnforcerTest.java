/*
 * Copyright (C) 2012 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.squareup.otto;

import com.squareup.otto.ThreadEnforcer;
import junit.framework.TestCase;

public class ThreadEnforcerTest extends TestCase {

  private static class RecordingThreadEnforcer implements ThreadEnforcer {
    boolean called = false;

    @Override public void enforce(Bus bus) {
      called = true;
    }
  }

  public void testEnforerCalledForRegister() {
    RecordingThreadEnforcer enforcer = new RecordingThreadEnforcer();
    Bus bus = new Bus(enforcer);

    assertFalse(enforcer.called);
    bus.register(this);
    assertTrue(enforcer.called);
  }

  public void testEnforcerCalledForPost() {
    RecordingThreadEnforcer enforcer = new RecordingThreadEnforcer();
    Bus bus = new Bus(enforcer);

    assertFalse(enforcer.called);
    bus.post(this);
    assertTrue(enforcer.called);
  }

  public void testEnforcerCalledForUnregister() {
    RecordingThreadEnforcer enforcer = new RecordingThreadEnforcer();
    Bus bus = new Bus(enforcer);

    assertFalse(enforcer.called);
    bus.unregister(this);
    assertTrue(enforcer.called);
  }

}