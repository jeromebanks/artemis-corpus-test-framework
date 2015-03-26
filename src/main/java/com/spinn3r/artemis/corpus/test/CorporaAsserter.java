/**
 * boilerpipe
 *
 * Copyright (c) 2009, 2014 Christian Kohlschütter
 *
 * The author licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.spinn3r.artemis.corpus.test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Code that tests for the assertion of a test but instead of testing with
 * data embedded in unit tests we test with data loaded from system resources.
 */
public class CorporaAsserter {

    /**
     * When in update mode, we just write data to resources and all tests pass.
     * This allows us to bulk approve a large number of tests if we've updated
     * an algorithm and think that all of them pass.
     */
    public static boolean DEFAULT_UPDATE_MODE = "true".equals( System.getProperty( "corpora-asserter.update_mode" ) );

    private final CorporaCache corporaCache;

    private final boolean updateMode;

    private final Class<?> parent;

    public CorporaAsserter(Class<?> parent) {
        this( parent, DEFAULT_UPDATE_MODE );
    }

    /**
     * Create a corpora assertion tester using the given class as a parent
     * for logging and resource loading.
     * @param parent
     */
    public CorporaAsserter(Class<?> parent, boolean updateMode ) {

        this.corporaCache = new CorporaCache( parent );
        this.parent = parent;
        this.updateMode = updateMode;

        System.out.printf( "%s (corpora-asserter): Loaded with updateMode=%s\n", parent.getName(), updateMode );

    }

    /**
     * Read the cached output from resources, compare it with the actual output, and
     * throw fail the test is the expected content doesn't meet the actual content .
     *
     * @param key
     * @param actual
     * @throws IOException
     */
    public void assertCorpora( String key, String actual ) throws IOException {

        System.out.printf( " === START corpora data for %s ===\n", key );

        System.out.printf( "%s\n", actual );

        System.out.printf( " === END corpora data for %s ===\n", key );

        if ( isUpdateMode() ) {

            corporaCache.write( key, actual );

        } else {

            String expected = corporaCache.read( key );

            assertEquals( expected, actual );

        }

    }

    public boolean isUpdateMode() {
        return updateMode;
    }

    /**
     * Get the backing cache.
     */
    public CorporaCache getCorporaCache() {
        return corporaCache;
    }

}
