package com.adaptris.conditional.conditions;

import org.hibernate.validator.constraints.NotBlank;
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
  
  @NotBlank
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
