/*
 * Copyright 2005-2007 WSO2, Inc. (http://wso2.com)
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

package org.wso2.carbon.mediation.dependency.mgt.resolvers;

import org.wso2.carbon.mediation.dependency.mgt.ConfigurationObject;
import org.apache.synapse.Mediator;
import org.apache.synapse.mediators.builtin.CalloutMediator;

import java.util.List;
import java.util.ArrayList;

public class CalloutMediatorResolver extends AbstractDependencyResolver {

    public List<ConfigurationObject> resolve(Mediator m) {
        if (!(m instanceof CalloutMediator)) {
            return null;            
        }

        CalloutMediator calloutMediator = (CalloutMediator) m;
        List<ConfigurationObject> providers = new ArrayList<ConfigurationObject>();
        String reqKey = calloutMediator.getRequestKey();
        if (reqKey != null) {
            addProvider(new ConfigurationObject(ConfigurationObject.TYPE_ENTRY, reqKey),
                    providers);
        }

        String targetKey = calloutMediator.getTargetKey();
        if (targetKey != null) {
            addProvider(new ConfigurationObject(ConfigurationObject.TYPE_ENTRY, targetKey),
                    providers);
        }

        return providers;
    }
}
