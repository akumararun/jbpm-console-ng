/*
 * Copyright 2014 JBoss by Red Hat.
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
package org.jbpm.console.ng.ht.forms.client.editors.taskform.generic;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.workbench.events.NotificationEvent;

/**
 *
 * @author salaboy
 */
@Dependent
@Templated(value = "GenericFormDisplayViewImpl.html")
public class GenericFormDisplayView extends Composite implements GenericFormDisplayPresenter.GenericFormDisplayView {
  


  private GenericFormDisplayPresenter presenter;

  @Inject
  private Event<NotificationEvent> notification;
  
  @Inject
  @DataField
  private FlowPanel formContainer;
  

  @Override
  public void init(GenericFormDisplayPresenter presenter) {
    this.presenter = presenter;
  }
  
  

  @Override
  public void displayNotification(String text) {
    notification.fire(new NotificationEvent(text));
  }

  @Override
  public void render(FlowPanel content) {
    formContainer.add(content);
  }
}
