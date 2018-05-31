package com.adaptris.conditional.operator;

import com.adaptris.conditional.Operator;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.DefaultMessageFactory;

import junit.framework.TestCase;

public class DoesNotExistTest extends TestCase {
  
  private Operator operator;
  
  private AdaptrisMessage message;
  
  public void setUp() throws Exception {
    operator = new DoesNotExist();
    message = DefaultMessageFactory.getDefaultInstance().newMessage();
  }
  
  public void testExists() {
    assertFalse(operator.apply(message,1L));
  }
  
  public void testNotExists() {
    assertTrue(operator.apply(message, null));
  }

}