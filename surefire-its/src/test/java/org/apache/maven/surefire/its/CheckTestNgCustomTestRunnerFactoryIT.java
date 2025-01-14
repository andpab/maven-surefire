package org.apache.maven.surefire.its;

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

import org.apache.maven.surefire.its.fixture.SurefireJUnit4IntegrationTestCase;
import org.apache.maven.surefire.its.fixture.SurefireLauncher;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test TestNG with custom test runner factory defined.
 *
 * @author <a href="mailto:orien@codehaus.org">Orien Madgwick</a>
 */
public class CheckTestNgCustomTestRunnerFactoryIT
    extends SurefireJUnit4IntegrationTestCase
{
    @Test
    public void testTestNgListenerReporter()
        throws IOException
    {
        File baseDir = unpack()
            .executeTest()
            .verifyErrorFreeLog()
            .assertTestSuiteResults( 1, 0, 0, 0 )
            .getBaseDir();

        baseDir = baseDir.getCanonicalFile();

        File targetDir = new File( baseDir, "target" );
        assertThat( targetDir ).isDirectory();
        assertThat( new File( targetDir, "testrunnerfactory-output.txt" ) ).isFile();
    }

    private SurefireLauncher unpack()
    {
        return unpack( "/testng-testRunnerFactory" );
    }
}

