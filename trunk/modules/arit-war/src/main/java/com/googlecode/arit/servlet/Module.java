/*
 * Copyright 2010 Andreas Veithen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.arit.servlet;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.arit.ModuleStatus;

public class Module {
    private final Integer id;
    private final String name;
    private final ModuleStatus status;
    private String icon;
    private final List<Resource> resources = new ArrayList<Resource>();
    private Module parent;
    private final List<Module> children = new ArrayList<Module>();
    
    public Module(Integer id, String name, ModuleStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ModuleStatus getStatus() {
        return status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Resource> getResources() {
        return resources;
    }
    
    public void addChild(Module child) {
        if (child.parent != null) {
            throw new IllegalStateException();
        }
        child.parent = this;
        children.add(child);
    }

    public Module getParent() {
        return parent;
    }

    public List<Module> getChildren() {
        return children;
    }
}
