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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.annotation.DisplayOrder;
import com.adaptris.conditional.Condition;
import com.adaptris.conditional.Operator;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.CoreException;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>
 * This {@link Condition} targets the message payload.  All you need do is choose an {@link Operator} to apply the conditional test.
 * </p>
 * @author amcgrath
 *
 */
@XStreamAlias("payload")
@AdapterComponent
@ComponentProfile(summary = "Tests a payload against a configured operator.", tag = "condition,payload")
@DisplayOrder(order = {"operator"})
public class ConditionPayload implements Condition {
  
  protected transient Logger log = LoggerFactory.getLogger(this.getClass().getName());
  
  @NotNull
  @Valid
  private Operator operator;
  
  @Override
  public boolean evaluate(AdaptrisMessage message) throws CoreException {
    log.trace("Testing payload condition");
      
    return this.getOperator().apply(message, message.getContent());
  }

  public Operator getOperator() {
    return operator;
  }

  public void setOperator(Operator operator) {
    this.operator = operator;
  }

}
