package com.adaptris.conditional.operator;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.conditional.Operator;
import com.adaptris.core.AdaptrisMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("exists")
@AdapterComponent
@ComponentProfile(summary = "Tests that a value exists (is not null).", tag = "operator")
public class Exists implements Operator {

  @Override
  public boolean apply(AdaptrisMessage message, Object object) {
    return object != null;
  }

}
