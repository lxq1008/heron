// Copyright 2016 Twitter. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.twitter.heron.spi.common;

import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

public class MiscTest {
  private static final Logger LOG = Logger.getLogger(MiscTest.class.getName());

  /**
   * Test if the ${HERON_HOME} variable can be substituted
   */
  @Test
  public void testHeronHome() {
    // check no occurrence
    Assert.assertEquals(
        "./bin",
        Misc.substitute("/usr/local/heron", "./bin")
    );

    // check a single subsitution at the begining
    Assert.assertEquals(
        "/usr/local/heron/bin",
        Misc.substitute("/usr/local/heron", "${HERON_HOME}/bin")
    );

    // check a single subsitution at the begining with relative path
    Assert.assertEquals(
        "./usr/local/heron/bin",
        Misc.substitute("/usr/local/heron", "./${HERON_HOME}/bin")
    );

    // check a single substitution at the end
    Assert.assertEquals(
        "/bin/usr/local/heron",
        Misc.substitute("/usr/local/heron", "/bin/${HERON_HOME}")
    );

    // check a single substitution at the end with relative path
    Assert.assertEquals(
        "./bin/usr/local/heron",
        Misc.substitute("/usr/local/heron", "./bin/${HERON_HOME}")
    );

    // check a single substitution in the middle
    Assert.assertEquals(
        "/bin/usr/local/heron/etc",
        Misc.substitute("/usr/local/heron", "/bin/${HERON_HOME}/etc")
    );

    // check a single substitution in the middle with relative path
    Assert.assertEquals(
        "./bin/usr/local/heron/etc",
        Misc.substitute("/usr/local/heron", "./bin/${HERON_HOME}/etc")
    );
  }

  @Test
  public void testURL() {
    Assert.assertTrue(
        Misc.isURL("file:///users/john/afile.txt")
    );
    Assert.assertFalse(
        Misc.isURL("/users/john/afile.txt")
    );
    Assert.assertTrue(
        Misc.isURL("https://gotoanywebsite.net/afile.html")
    );
    Assert.assertFalse(
        Misc.isURL("https//gotoanywebsite.net//afile.html")
    );
  }
}
