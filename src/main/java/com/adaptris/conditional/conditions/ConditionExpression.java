package com.adaptris.conditional.conditions;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.annotation.DisplayOrder;
import com.adaptris.conditional.Condition;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.CoreException;
import com.adaptris.expressions.FreeFormExpressionService;
import com.adaptris.interlok.InterlokException;
import com.adaptris.interlok.config.DataOutputParameter;
import com.adaptris.interlok.types.InterlokMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("expression")
@AdapterComponent
@ComponentProfile(summary = "Tests a static algorithm for a boolean result.", tag = "condition")
@DisplayOrder(order = {"algorithm"})
public class ConditionExpression implements Condition {
  
  private String algorithm;
  
  protected transient FreeFormExpressionService expressionService;
  
  public ConditionExpression() {
    expressionService = new FreeFormExpressionService();
    
  }

  @Override
  public boolean evaluate(AdaptrisMessage message) throws CoreException {
    final ReturnedExpressionValue expressionResult = new ReturnedExpressionValue();
    
    expressionService.setAlgorithm(getAlgorithm());
    expressionService.setResult(new DataOutputParameter<String>() {
      @Override
      public void insert(String data, InterlokMessage msg) throws InterlokException {
        expressionResult.value = data;
      }
    });
    
    expressionService.doService(message);
    
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
