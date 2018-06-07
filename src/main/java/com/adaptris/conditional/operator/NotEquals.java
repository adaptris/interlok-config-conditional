package com.adaptris.conditional.operator;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.conditional.Condition;
import com.adaptris.conditional.Operator;
import com.adaptris.core.AdaptrisMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>
 * This {@link Operator} simply tests two values for non-equality.
 * </p>
 * <p>
 * The first value used in the equality test is the {@link Condition} that this {@link Operator} is configured for; which could be the message payload or a metadata item for example. <br/>
 * The second value is the static value configured for this operator.
 * </p>
 * <p>
 * The static value can be a literal value; "myValue" or can be metadata resolved for example; <br/>
 * <pre>
 *  <value>%message{myKey}</value>
 * </pre>
 * The above will test the metadata value identified by the metadata key "myKey".
 * </p>
 * @author amcgrath
 *
 */
@XStreamAlias("not-equals")
@AdapterComponent
@ComponentProfile(summary = "Tests that a configured value does not equal the supplied value.", tag = "operator")
public class NotEquals implements Operator {

  private String value;
  
  @Override
  public boolean apply(AdaptrisMessage message, String object) {
    return !message.resolve((String) this.getValue()).equals(message.resolve((String) object));
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
