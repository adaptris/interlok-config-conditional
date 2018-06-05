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

@XStreamAlias("and")
@AdapterComponent
@ComponentProfile(summary = "Allows you to test multiple conditions, where all must return true.", tag = "condition,service")
public class ConditionAnd implements Condition {

  @NotNull
  @XStreamImplicit(itemFieldName = "condition")
  private List<Condition> conditions;

  public ConditionAnd() {
    this.setConditions(new ArrayList<Condition>());
  }
  
  @Override
  public boolean evaluate(AdaptrisMessage message) throws CoreException {
    if(this.getConditions().size() == 0)
      return false;
    
    boolean returnValue = true;
    for(Condition condition : this.getConditions()) {
      if(!condition.evaluate(message)) {
        returnValue = false;
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
