package com.adaptris.conditional.operator;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.conditional.Condition;
import com.adaptris.conditional.Operator;
import com.adaptris.core.AdaptrisMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>
 * This {@link Operator} simply tests a single value is not null.
 * </p>
 * <p>
 * The value used in the not-null test is the {@link Condition} that this {@link Operator} is configured for; which could be the message payload or a metadata item for example. <br/>
 * </p>
 * @author amcgrath
 *
 */
@XStreamAlias("not-null")
@AdapterComponent
@ComponentProfile(summary = "Tests that a value exists (is not null).", tag = "operator")
public class NotNull implements Operator {

  @Override
  public boolean apply(AdaptrisMessage message, String object) {
    return object != null;
  }

}
