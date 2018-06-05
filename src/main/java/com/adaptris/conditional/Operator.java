package com.adaptris.conditional;

import com.adaptris.core.AdaptrisMessage;

public interface Operator {
  
  public boolean apply(AdaptrisMessage message, String object);

}
