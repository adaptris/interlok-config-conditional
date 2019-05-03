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
import com.adaptris.annotation.GenerateBeanInfo;
import com.adaptris.annotation.Removal;
import com.adaptris.conditional.Condition;
import com.adaptris.conditional.Operator;
import com.adaptris.core.util.LoggingHelper;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>
 * This {@link Operator} simply tests a single value is null.
 * </p>
 * <p>
 * The value used in the not-null test is the {@link Condition} that this {@link Operator} is
 * configured for; which could be the message payload or a metadata item for example.
 * </p>
 * <p>
 * <strong> this class is likely to cause you problems since the alias name is poor; and interferes
 * if you have any classes that have the {@link GenerateBeanInfo} annotation.</strong>. Switch to
 * using {@link IsNull} as soon as practical.
 * </p>
 * 
 * @deprecated config-conditional was promoted into interlok-core; this class will be removed w/o
 *             warning.
 * @author amcgrath
 *
 */
@Deprecated
@Removal(version = "3.11.0",
    message = "config-conditional was promoted into interlok-core; this class will be removed w/o warning as it interfers with marshalling behaviour.")
@XStreamAlias("null")
@AdapterComponent
@ComponentProfile(summary = "Tests that a value does not exists (is null).", tag = "operator")
public class Null extends com.adaptris.core.services.conditional.operator.IsNull {

  private transient boolean warningLogged = false;

  public Null() {
    LoggingHelper.logDeprecation(warningLogged, () -> {
      warningLogged = true;
    }, this.getClass().getCanonicalName(),
        com.adaptris.core.services.conditional.operator.IsNull.class
            .getCanonicalName());

  }


}
