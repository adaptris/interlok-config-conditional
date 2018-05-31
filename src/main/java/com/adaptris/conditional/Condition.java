package com.adaptris.conditional;

import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.CoreException;

public interface Condition {
  
  public boolean evaluate(AdaptrisMessage message) throws CoreException;

}
