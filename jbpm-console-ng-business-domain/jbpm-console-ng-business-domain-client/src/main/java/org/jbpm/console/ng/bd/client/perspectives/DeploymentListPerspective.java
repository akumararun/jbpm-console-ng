/*
 * Copyright 2012 JBoss Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.jbpm.console.ng.bd.client.perspectives;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import org.jbpm.console.ng.bd.client.events.DeploymentsSearchEvent;
import org.kie.workbench.common.widgets.client.search.ContextualSearch;
import org.kie.workbench.common.widgets.client.search.SearchBehavior;
import org.uberfire.client.annotations.OnStart;

import org.uberfire.client.annotations.Perspective;
import org.uberfire.client.annotations.WorkbenchPerspective;
import org.uberfire.mvp.impl.DefaultPlaceRequest;
import org.uberfire.workbench.model.PanelType;
import org.uberfire.workbench.model.PerspectiveDefinition;
import org.uberfire.workbench.model.impl.PartDefinitionImpl;
import org.uberfire.workbench.model.impl.PerspectiveDefinitionImpl;

/**
 * A Perspective to show File Explorer
 */
@ApplicationScoped
@WorkbenchPerspective(identifier = "Deployments", isDefault = false)
public class DeploymentListPerspective {
    
    
    @Inject
    private ContextualSearch contextualSearch;
    
    @Inject
    private Event<DeploymentsSearchEvent> searchEvents;
    
    @Perspective
    public PerspectiveDefinition getPerspective() {
        final PerspectiveDefinition p = new PerspectiveDefinitionImpl(PanelType.ROOT_LIST);
        p.setName("Deployments");
        p.getRoot().addPart(new PartDefinitionImpl(new DefaultPlaceRequest("Deployments List")));
        p.setTransient(true);
        return p;
    }
    
    @OnStart
    public void init() {
        contextualSearch.setSearchBehavior(new SearchBehavior() {
            @Override
            public void execute(String searchFilter) {
                searchEvents.fire(new DeploymentsSearchEvent(searchFilter));
            }

        });
        
    }
}
