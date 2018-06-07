package com.adaptris.conditional;

import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.CoreException;

/**
 * <p>
 * Conditions are used with logical expressions in configuration such as {@link IfElse} and {@link While}.
 * </p>
 * <p>
 * Logical expressions allow us to branch through services based on the results of Conditions.  Conditions generally test for equality, nulls and not-nulls against a message payload or metadata item.
 * </p>
 * @author amcgrath
 *
 */
public interface Condition {
  
  public boolean evaluate(AdaptrisMessage message) throws CoreException;

}
