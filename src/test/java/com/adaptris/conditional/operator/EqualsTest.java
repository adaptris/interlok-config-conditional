package com.adaptris.conditional.operator;

import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.DefaultMessageFactory;

import junit.framework.TestCase;

public class EqualsTest extends TestCase {

  private Equals operator;
  
  private AdaptrisMessage message;
  
  public void setUp() throws Exception {
    operator = new Equals();
    message = DefaultMessageFactory.getDefaultInstance().newMessage();
  }
  
  public void testObjectEquals() {
    Object object = new Object();
    operator.setValue(object);
    
    assertTrue(operator.apply(message, object));
  }
  
  public void testObjectnotEquals() {
    Object object = new Object();
    operator.setValue(object);
    
    assertFalse(operator.apply(message, new Object()));
  }
  
  public void testStringEquals() {
    String object = new String("test");
    operator.setValue(object);
    
    assertTrue(operator.apply(message, "test"));
  }
  
  public void testStringNotEquals() {
    String object = new String("test");
    operator.setValue(object);
    
    assertFalse(operator.apply(message, "xxxx"));
  }
  
}
