package com.adaptris.conditional.conditions;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.adaptris.conditional.Operator;
import com.adaptris.core.util.Args;

public abstract class ConditionWithOperator extends ConditionImpl {

  @NotNull
  @Valid
  private Operator operator;

  public ConditionWithOperator() {
  }

  public Operator getOperator() {
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
  public void setOperator(Operator operators) {
    this.operator = Args.notNull(operators, "operator");
  }

  protected Operator operator() {
    return Args.notNull(getOperator(), "operator");
  }
}
