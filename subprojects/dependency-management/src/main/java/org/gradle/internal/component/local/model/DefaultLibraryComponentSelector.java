/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.internal.component.local.model;

import com.google.common.base.Objects;
import org.gradle.api.artifacts.component.ComponentIdentifier;
import org.gradle.api.artifacts.component.LibraryComponentSelector;
import org.gradle.api.artifacts.component.LibraryIdentifier;

public class DefaultLibraryComponentSelector implements LibraryComponentSelector {
    private final String projectPath;
    private final String libraryName;

    public DefaultLibraryComponentSelector(String projectPath, String libraryName) {
        this.projectPath = projectPath;
        this.libraryName = libraryName;
    }

    @Override
    public String getDisplayName() {
        return DefaultLibraryComponentIdentifier.libraryToConfigurationName(getProjectPath(), getLibraryName());
    }

    @Override
    public String getProjectPath() {
        return projectPath;
    }

    @Override
    public String getLibraryName() {
        return libraryName;
    }

    public boolean matchesStrictly(ComponentIdentifier identifier) {
        assert identifier != null : "identifier cannot be null";

        if(identifier instanceof LibraryIdentifier) {
            LibraryIdentifier projectComponentIdentifier = (LibraryIdentifier)identifier;
            return projectPath.equals(projectComponentIdentifier.getProjectPath()) && libraryName.equals(projectComponentIdentifier.getLibraryName());
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DefaultLibraryComponentSelector that = (DefaultLibraryComponentSelector) o;
        return Objects.equal(getLibraryName(), that.getLibraryName()) && Objects.equal(getProjectPath(), that.getProjectPath());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), getLibraryName(), getProjectPath());
    }
}