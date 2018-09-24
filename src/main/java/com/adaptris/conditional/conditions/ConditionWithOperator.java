package com.adaptris.conditional.conditions;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.adaptris.conditional.Operator;
import com.adaptris.core.CoreException;
import com.adaptris.core.util.Args;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public abstract class ConditionWithOperator extends ConditionImpl {

  @NotNull
  @XStreamImplicit
  @Valid
  private List<Operator> operator;

  public ConditionWithOperator() {
    setOperator(new ArrayList<>());
  }

  public List<Operator> getOperator() {
    return operator;
  }

  /**
   * Set the operators to apply.
   * 
   * <p>
   * Note that although this is a list, only the <strong>first</strong> operator is evaluated. It is a list so that the generated
   * XML is more natural via {@code XStreamImplicit}.
   * </p>
   * 
   * @param condition
   */
  public void setOperator(List<Operator> operators) {
    this.operator = Args.notNull(operators, "operator");
  }

  public void setOperator(Operator operator) {
    getOperator().add(Args.notNull(operator, "operator"));
  }

  protected Operator operator() throws CoreException {
    List<Operator> c = getOperator();
    if (c.size() == 0) {
      throw new CoreException("No Operator");
    }
    return c.get(0);
  }
}
