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
import com.adaptris.annotation.DisplayOrder;
import com.adaptris.annotation.Removal;
import com.adaptris.core.services.conditional.Condition;
import com.adaptris.core.util.LoggingHelper;

/**
 * <p>
 * This {@link Condition} allows you to specify a boolean evaluated expression, with static values
 * and resolved metadata values.
 * </p>
 * <p>
 * If your expression evaluates to "true", then this condition passes.
 * </p>
 * <p>
 * Static values mixed with metadata values allow you to create boolean expressions, such as; <br/>
 * <table>
 * <tr>
 * <th>Example description</th>
 * <th>Example Expression</th>
 * </tr>
 * <tr>
 * <td>Is the metadata value identified by key "myKey" equal to the value 1</td>
 * <td>%message{myKey} == 1</td>
 * </tr>
 * <tr>
 * <td>Is the metadata value identified by key "myKey" equal to the metadata item "myOtherKey"</td>
 * <td>%message{myKey} == %message{myOtherKey}</td>
 * </tr>
 * <tr>
 * <td>Is the metadata value identified by key "myKey" greater than "myOtherKey" plus 100</td>
 * <td>%message{myKey} > (%message{myOtherKey} + 100)</td>
 * </tr>
 * </table>
 * 
 * </p>
 * 
 * @deprecated since 3.9.0; config-conditional was promoted into interlok-core
 * @author amcgrath
 *
 */
@Deprecated
@Removal(version = "3.11.0", message = "config-conditional was promoted into interlok-core")
@AdapterComponent
@ComponentProfile(summary = "Tests a static algorithm for a boolean result.", tag = "condition")
@DisplayOrder(order = {"algorithm"})
public class ConditionExpression
    extends com.adaptris.core.services.conditional.conditions.ConditionExpression {
  

  private transient boolean warningLogged = false;

  public ConditionExpression() {
    LoggingHelper.logDeprecation(warningLogged, () -> {
      warningLogged = true;
    }, this.getClass().getCanonicalName(),
        com.adaptris.core.services.conditional.conditions.ConditionExpression.class.getCanonicalName());

  }

}
