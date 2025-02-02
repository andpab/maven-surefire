package org.apache.maven.surefire.api.report;

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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static org.apache.maven.surefire.shared.utils.StringUtils.isBlank;

/**
 * @author Kristian Rosenvold
 */
public class CategorizedReportEntry
    extends SimpleReportEntry
{
    public static final String GROUP_PREFIX = " (of ";

    private static final String GROUP_SUFIX = ")";

    private final String group;

    public CategorizedReportEntry( @Nonnull RunMode runMode, @Nonnegative Long testRunId,
                                   String source, String name, String group )
    {
        this( runMode, testRunId, source, name, group, null, null );
    }

    public CategorizedReportEntry( @Nonnull RunMode runMode, @Nonnegative Long testRunId,
                                   String source, String name, String group, StackTraceWriter stackTraceWriter,
                                   Integer elapsed )
    {
        super( runMode, testRunId, source, null, name, null, stackTraceWriter, elapsed );
        this.group = group;
    }

    public CategorizedReportEntry( @Nonnull RunMode runMode, @Nonnegative Long testRunId,
                                   String source, String name, String group, StackTraceWriter stackTraceWriter,
                                   Integer elapsed, String message )
    {
        this( runMode, testRunId, source, null, name, null,
                group, stackTraceWriter, elapsed, message, Collections.<String, String>emptyMap() );
    }

    public CategorizedReportEntry( @Nonnull RunMode runMode, @Nonnegative Long testRunId,
                                   String source, String sourceText, String name, String nameText,
                                   String group, StackTraceWriter stackTraceWriter,
                                   Integer elapsed, String message, Map<String, String> systemProperties )
    {
        super( runMode, testRunId, source, sourceText, name, nameText, stackTraceWriter, elapsed, message,
            systemProperties );
        this.group = group;
    }

    public static TestSetReportEntry reportEntry( @Nonnull RunMode runMode, @Nonnegative Long testRunId,
                                                  String source, String sourceText, String name, String nameText,
                                                  String group,
                                                  StackTraceWriter stackTraceWriter, Integer elapsed, String message,
                                                  Map<String, String> systemProperties )
    {
        return group != null
            ? new CategorizedReportEntry( runMode, testRunId, source, sourceText, name, nameText,
                group, stackTraceWriter, elapsed, message, systemProperties )
            : new SimpleReportEntry( runMode, testRunId, source, sourceText, name, nameText,
                stackTraceWriter, elapsed, message, systemProperties );
    }

    @Override
    public String getGroup()
    {
        return group;
    }

    @Override
    public String getNameWithGroup()
    {
        return isNameWithGroup() ? getSourceName() + GROUP_PREFIX + getGroup() + GROUP_SUFIX : getSourceName();
    }

    @Override
    public String getReportNameWithGroup()
    {
        String sourceText = getSourceText();

        if ( isBlank ( sourceText ) )
        {
            return null;
        }

        return isNameWithGroup() ? sourceText + GROUP_PREFIX + getGroup() + GROUP_SUFIX : sourceText;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }
        if ( !super.equals( o ) )
        {
            return false;
        }

        CategorizedReportEntry that = (CategorizedReportEntry) o;

        return Objects.equals( group, that.group );
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + ( group != null ? group.hashCode() : 0 );
        return result;
    }

    private boolean isNameWithGroup()
    {
        return getGroup() != null && !getGroup().equals( getSourceName() );
    }
}
