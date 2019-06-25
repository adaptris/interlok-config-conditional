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
 * evaluate to "true" will run a configured set of services continuously until the configured
 * conditions do not evaluate to true.
 * </p>
 * <p>
 * You can also set a value for the maximum amount of times your services will run regardless of
 * whether your conditions continue to evaluate to true. <br/>
 * 
 * <pre>
 *  <max-loops>5</max-loops>
 * </pre>
 * 
 * The default value for the max-loops is 10. Setting this value to 0, will loop forever until your
 * configured conditions evaluate to false.
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
@Deprecated
@Removal(version = "3.11.0", message = "config-conditional was promoted into interlok-core")
@AdapterComponent
@ComponentProfile(
    summary = "Runs the configured service/list repeatedly 'WHILE' the configured condition is met.",
    tag = "service,conditional,loop")
@DisplayOrder(order = {"condition", "then", "maxLoops"})
public class While extends com.adaptris.core.services.conditional.While {
  private transient boolean warningLogged = false;

  public While() {
    LoggingHelper.logDeprecation(warningLogged, () -> {
      warningLogged = true;
    }, this.getClass().getCanonicalName(),
        com.adaptris.core.services.conditional.While.class.getCanonicalName());

  }
}
