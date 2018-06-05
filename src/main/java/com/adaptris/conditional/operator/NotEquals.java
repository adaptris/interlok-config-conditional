package com.adaptris.conditional.operator;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.conditional.Operator;
import com.adaptris.core.AdaptrisMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

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
