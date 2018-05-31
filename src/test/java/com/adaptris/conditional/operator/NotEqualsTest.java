package com.adaptris.conditional.operator;

import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.DefaultMessageFactory;

import junit.framework.TestCase;

public class NotEqualsTest extends TestCase {

  private NotEquals operator;
  
  private AdaptrisMessage message;
  
  public void setUp() throws Exception {
    operator = new NotEquals();
    message = DefaultMessageFactory.getDefaultInstance().newMessage();
  }
  
  public void testObjectEquals() {
    Object object = new Object();
    operator.setValue(object);
    
    assertFalse(operator.apply(message, object));
  }
  
  public void testObjectnotEquals() {
    Object object = new Object();
    operator.setValue(object);
    
    assertTrue(operator.apply(message, new Object()));
  }
  
  public void testStringEquals() {
    String object = new String("test");
    operator.setValue(object);
    
    assertFalse(operator.apply(message, "test"));
  }
  
  public void testStringNotEquals() {
    String object = new String("test");
    operator.setValue(object);
    
    assertTrue(operator.apply(message, "xxxx"));
  }
  
}
