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

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.conditional.Condition;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.CoreException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * <p>
 * This {@link Condition} allows you to configure a list of child {@link Condition}'s where only one has to evaluate to "true".
 * </p>
 * @author amcgrath
 *
 */
@XStreamAlias("or")
@AdapterComponent
@ComponentProfile(summary = "Allows you to test multiple conditions, where only one has to return true.", tag = "condition,service")
public class ConditionOr implements Condition {
  
  @NotNull
  @XStreamImplicit(itemFieldName = "condition")
  private List<Condition> conditions;

  public ConditionOr() {
    this.setConditions(new ArrayList<Condition>());
  }
  
  @Override
  public boolean evaluate(AdaptrisMessage message) throws CoreException {
    boolean returnValue = false;
    for(Condition condition : this.getConditions()) {
      if(condition.evaluate(message)) {
        returnValue = true;
        break;
      }
    }
    return returnValue;
  }

  public List<Condition> getConditions() {
    return conditions;
  }

  public void setConditions(List<Condition> conditions) {
    this.conditions = conditions;
  }

}
