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

package com.adaptris.conditional.operator;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.annotation.Removal;
import com.adaptris.conditional.Condition;
import com.adaptris.conditional.Operator;
import com.adaptris.core.util.LoggingHelper;

/**
 * <p>
 * This {@link Operator} simply tests a single value is not null.
 * </p>
 * <p>
 * The value used in the not-null test is the {@link Condition} that this {@link Operator} is
 * configured for; which could be the message payload or a metadata item for example. <br/>
 * </p>
 * 
 * @deprecated since 3.9.0; config-conditional was promoted into interlok-core
 * @author amcgrath
 *
 */
@Deprecated
@Removal(version = "3.11.0", message = "config-conditional was promoted into interlok-core")
@AdapterComponent
@ComponentProfile(summary = "Tests that a value exists (is not null).", tag = "operator")
public class NotNull extends com.adaptris.core.services.conditional.operator.NotNull {

  private transient boolean warningLogged = false;

  public NotNull() {
    LoggingHelper.logDeprecation(warningLogged, () -> {
      warningLogged = true;
    }, this.getClass().getCanonicalName(),
        com.adaptris.core.services.conditional.operator.NotNull.class
            .getCanonicalName());

  }


}
