package com.adaptris.conditional.conditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adaptris.conditional.Condition;
import com.adaptris.core.CoreException;

public abstract class ConditionImpl implements Condition {
  protected transient Logger log = LoggerFactory.getLogger(this.getClass().getName());

  @Override
  public void init() throws CoreException {
    // Override as required.
  }

  @Override
  public void start() throws CoreException {
    // Override as required.
  }

  @Override
  public void stop() {
    // Override as required.
  }

  @Override
  public void close() {
    // Override as required.
  }

}
