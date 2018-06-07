package com.adaptris.conditional;

import com.adaptris.conditional.conditions.ConditionMetadata;
import com.adaptris.conditional.conditions.ConditionPayload;
import com.adaptris.core.AdaptrisMessage;

/**
 * <p>
 * Operators are used with {@link Condition}'s in configuration such as {@link ConditionMetadata} and {@link ConditionPayload}.
 * </p>
 * <p>
 * While the {@link Condition} is the target for any test, such as metadata or payload, the Operator is the actual test; "equals", "null" etc.
 * </p>
 * @author amcgrath
 *
 */
public interface Operator {
  
  public boolean apply(AdaptrisMessage message, String object);

}
