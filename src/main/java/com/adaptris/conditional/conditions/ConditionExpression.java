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
import com.adaptris.annotation.InputFieldHint;
import com.adaptris.conditional.Condition;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.CoreException;
import com.adaptris.core.util.LifecycleHelper;
import com.adaptris.expressions.FreeFormExpressionService;
import com.adaptris.interlok.InterlokException;
import com.adaptris.interlok.config.DataOutputParameter;
import com.adaptris.interlok.types.InterlokMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>
 * This {@link Condition} allows you to specify a boolean evaluated expression, with static values and resolved metadata values.
 * </p>
 * <p>
 * If your expression evaluates to "true", then this condition passes.
 * </p>
 * <p>
 * Static values mixed with metadata values allow you to create boolean expressions, such as; <br/>
 * <table>
 *  <tr>
 *      <th>Example description</th>
 *      <th>Example Expression</th>
 *  </tr>
 *  <tr>
 *      <td>Is the metadata value identified by key "myKey" equal to the value 1</td>
 *      <td>%message{myKey} == 1</td>
 *  </tr>
 *  <tr>
 *      <td>Is the metadata value identified by key "myKey" equal to the metadata item "myOtherKey"</td>
 *      <td>%message{myKey} == %message{myOtherKey}</td>
 *  </tr>
 *  <tr>
 *      <td>Is the metadata value identified by key "myKey" greater than "myOtherKey" plus 100</td>
 *      <td>%message{myKey} > (%message{myOtherKey} + 100)</td>
 *  </tr>
 * </table>
 * 
 * </p>
 * @author amcgrath
 *
 */
@XStreamAlias("expression")
@AdapterComponent
@ComponentProfile(summary = "Tests a static algorithm for a boolean result.", tag = "condition")
@DisplayOrder(order = {"algorithm"})
public class ConditionExpression extends ConditionImpl {
  
  @InputFieldHint(expression = true)
  private String algorithm;

  public ConditionExpression() {
  }

  @Override
  public boolean evaluate(AdaptrisMessage message) throws CoreException {
    final ReturnedExpressionValue expressionResult = new ReturnedExpressionValue();
    FreeFormExpressionService expressionService = new FreeFormExpressionService();
    try {
      expressionService.setAlgorithm(getAlgorithm());
      expressionService.setResult(new DataOutputParameter<String>() {
        @Override
        public void insert(String data, InterlokMessage msg) throws InterlokException {
          expressionResult.value = data;
        }
      });
      LifecycleHelper.initAndStart(expressionService, false);
      expressionService.doService(message);
    } finally {
      LifecycleHelper.stopAndClose(expressionService, false);
    }
    return expressionResult.value.equalsIgnoreCase("true");
  }

  public String getAlgorithm() {
    return algorithm;
  }

  public void setAlgorithm(String algorithm) {
    this.algorithm = algorithm;
  }

  class ReturnedExpressionValue {
    String value = "";
  }


}
