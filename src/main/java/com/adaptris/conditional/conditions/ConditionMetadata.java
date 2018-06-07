/*
    Copyright [yyyy] [name of copyright owner]

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

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.AffectsMetadata;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.annotation.DisplayOrder;
import com.adaptris.conditional.Condition;
import com.adaptris.conditional.Operator;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.CoreException;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>
 * This {@link Condition} targets message metadata.  All you need do is choose an {@link Operator} to apply the conditional test.
 * </p>
 * @author amcgrath
 *
 */
@XStreamAlias("metadata")
@AdapterComponent
@ComponentProfile(summary = "Tests a metadata key against a configured operator.", tag = "condition,metadata")
@DisplayOrder(order = {"metadataKey", "operator"})
public class ConditionMetadata implements Condition {
  
  protected transient Logger log = LoggerFactory.getLogger(this.getClass().getName());

  @NotBlank
  @AffectsMetadata
  private String metadataKey;
  
  @NotBlank
  private Operator operator;
  
  @Override
  public boolean evaluate(AdaptrisMessage message) throws CoreException {
    if(!StringUtils.isEmpty(this.getMetadataKey())) {
      log.trace("Testing metadata condition with key: {}", this.getMetadataKey());
      
      return this.getOperator().apply(message, message.getMetadataValue(this.getMetadataKey()));
    } else {
      log.warn("No metadata key supplied, returning false.");
      return false;
    }
  }

  public String getMetadataKey() {
    return metadataKey;
  }

  public void setMetadataKey(String metadataKey) {
    this.metadataKey = metadataKey;
  }

  public Operator getOperator() {
    return operator;
  }

  public void setOperator(Operator operator) {
    this.operator = operator;
  }

}
