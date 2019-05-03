/*
    Copyright Adaptris

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.adaptris.conditional;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.annotation.DisplayOrder;
import com.adaptris.annotation.Removal;
import com.adaptris.core.Service;
import com.adaptris.core.util.LoggingHelper;

/**
 * <p>
 * This {@link Service} allows you to test boolean (true or false) {@link Condition}'s, which if
 * evaluate to "true" will run a configured set of services, otherwise run a different set of
 * services.
 * </p>
 * <p>
 * Note, that although you must specify a service or list of services should the configured
 * conditions pass, you do not have to configure services to run should the conditions fail.
 * </p>
 * <p>
 * Typically your {@link Condition} will test for equality, in-line expressions or whether values
 * exist or not. The values to test will generally come from the payload or message metadata. <br/>
 * Also note that some conditions can be nested, such that you can test that a value is equal to
 * another AND / OR a value is equal/not to another value.
 * </p>
 * 
 * @author aaron
 * @deprecated since 3.9.0; config-conditional was promoted into interlok-core
 * 
 */
@AdapterComponent
@ComponentProfile(summary = "Runs the configured service/list 'IF' the configured condition is met, otherwise will run the 'else' service/list.", tag = "service, conditional", since="3.7.3")
@DisplayOrder(order = {"condition", "then","otherwise"})
@Deprecated
@Removal(version = "3.11.0", message = "config-conditional was promoted into interlok-core")
public class IfElse extends com.adaptris.core.services.conditional.IfElse {
  private transient boolean warningLogged = false;

  public IfElse() {
    LoggingHelper.logDeprecation(warningLogged, () -> {
      warningLogged = true;
    }, this.getClass().getCanonicalName(),
        com.adaptris.core.services.conditional.IfElse.class.getCanonicalName());

  }

}
