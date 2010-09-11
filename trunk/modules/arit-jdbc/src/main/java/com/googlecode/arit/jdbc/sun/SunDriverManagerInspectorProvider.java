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
package com.googlecode.arit.jdbc.sun;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.arit.Provider;
import com.googlecode.arit.jdbc.DriverManagerInspector;
import com.googlecode.arit.rbeans.RBeanFactory;
import com.googlecode.arit.rbeans.RBeanFactoryException;

public class SunDriverManagerInspectorProvider implements Provider<DriverManagerInspector> {
    public DriverManagerInspector getImplementation() {
        try {
            final RBeanFactory rbf = new RBeanFactory(DriverManagerRBean.class);
            final DriverManagerRBean driverManager = rbf.createRBean(DriverManagerRBean.class);
            return new DriverManagerInspector() {
                public List<Class<?>> getDriverClasses() {
                    // We need to get the field value every time, because in JRE 1.6, the Vector
                    // is replaced when a new driver is added.
                    List<Class<?>> driverClasses = new ArrayList<Class<?>>();
                    for (DriverInfoRBean driverInfo : driverManager.getDrivers()) {
                        driverClasses.add(driverInfo.getDriverClass());
                    }
                    return driverClasses;
                }
            };
        } catch (RBeanFactoryException ex) {
            return null;
        }
    }
}
