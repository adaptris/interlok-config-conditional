package com.adaptris.conditional.operator;

import com.adaptris.conditional.Operator;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.DefaultMessageFactory;

import junit.framework.TestCase;

public class ExistsTest extends TestCase {
  
  private Operator operator;
  
  private AdaptrisMessage message;
  
  public void setUp() throws Exception {
    operator = new Exists();
    message = DefaultMessageFactory.getDefaultInstance().newMessage();
  }
  
  public void testExists() {
    assertTrue(operator.apply(message, 1L));
  }
  
  public void testNotExists() {
    assertFalse(operator.apply(message, null));
  }

}
