package com.adaptris.conditional.operator;

import com.adaptris.conditional.Operator;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.DefaultMessageFactory;

import junit.framework.TestCase;

public class NotNullTest extends TestCase {
  
  private Operator operator;
  
  private AdaptrisMessage message;
  
  public void setUp() throws Exception {
    operator = new NotNull();
    message = DefaultMessageFactory.getDefaultInstance().newMessage();
  }
  
  public void testExists() {
    assertTrue(operator.apply(message, "1"));
  }
  
  public void testNotExists() {
    assertFalse(operator.apply(message, null));
  }

}
