package com.adaptris.conditional.operator;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.conditional.Operator;
import com.adaptris.core.AdaptrisMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("not-exists")
@AdapterComponent
@ComponentProfile(summary = "Tests that a value does not exists (is null).", tag = "operator")
public class DoesNotExist implements Operator {

  @Override
  public boolean apply(AdaptrisMessage message, Object object) {
    return object == null;
  }

}
