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

package com.adaptris.conditional.conditions;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.annotation.Removal;
import com.adaptris.core.services.conditional.Condition;
import com.adaptris.core.util.LoggingHelper;

/**
 * <p>
 * This {@link Condition} allows you to configure a list of child {@link Condition}'s where all must
 * evaluate to "true".
 * </p>
 * 
 * @deprecated since 3.9.0; config-conditional was promoted into interlok-core
 * @author amcgrath
 *
 */
@Deprecated
@Removal(version = "3.11.0", message = "config-conditional was promoted into interlok-core")
@AdapterComponent
@ComponentProfile(summary = "Allows you to test multiple conditions, where all must return true.", tag = "condition,service")
public class ConditionAnd extends com.adaptris.core.services.conditional.conditions.ConditionAnd {

  private transient boolean warningLogged = false;

  public ConditionAnd() {
    LoggingHelper.logDeprecation(warningLogged, () -> {
      warningLogged = true;
    }, this.getClass().getCanonicalName(),
        com.adaptris.core.services.conditional.conditions.ConditionAnd.class.getCanonicalName());

  }

}
