package com.adaptris.conditional.operator;

import org.apache.commons.lang3.StringUtils;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.conditional.Operator;
import com.adaptris.core.AdaptrisMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("null")
@AdapterComponent
@ComponentProfile(summary = "Tests that a value does not exists (is null).", tag = "operator")
public class Null implements Operator {

  @Override
  public boolean apply(AdaptrisMessage message, Object object) {
    if(object == null)
      return true;
    else if (object instanceof String) {
      return StringUtils.isEmpty((String) object);
    } else
      return false;
  }

}
