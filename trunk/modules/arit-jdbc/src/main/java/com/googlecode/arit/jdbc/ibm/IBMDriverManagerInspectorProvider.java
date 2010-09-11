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
package com.googlecode.arit.jdbc.ibm;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.arit.Provider;
import com.googlecode.arit.jdbc.DriverManagerInspector;
import com.googlecode.arit.rbeans.RBeanFactory;
import com.googlecode.arit.rbeans.RBeanFactoryException;

public class IBMDriverManagerInspectorProvider implements Provider<DriverManagerInspector> {
    public DriverManagerInspector getImplementation() {
        try {
            RBeanFactory rbf = new RBeanFactory(DriverManagerRBean.class);
            // "theDrivers" is a final field; we only need to retrieve it once
            DriverManagerRBean driverManager = rbf.createRBean(DriverManagerRBean.class);
            final List<Driver> drivers = driverManager.getDrivers();
            return new DriverManagerInspector() {
                public List<Class<?>> getDriverClasses() {
                    List<Class<?>> driverClasses = new ArrayList<Class<?>>(drivers.size());
                    for (Object driver : drivers) {
                        driverClasses.add(driver.getClass());
                    }
                    return driverClasses;
                }
            };
        } catch (RBeanFactoryException ex) {
            return null;
        }
    }
}
