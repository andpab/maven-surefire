package org.apache.maven.plugin.failsafe;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Adapt the JUnit4 tests which use only annotations to the JUnit3 test suite.
 *
 * @author Tibor Digana (tibor17)
 * @since 2.21.0
 */
@SuiteClasses( {
        IntegrationTestMojoTest.class,
        MarshallerUnmarshallerTest.class,
        RunResultTest.class,
        VerifyMojoTest.class
} )
@RunWith( Suite.class )
public class JUnit4SuiteTest
{
    public static Test suite()
    {
        return new JUnit4TestAdapter( JUnit4SuiteTest.class );
    }
}
