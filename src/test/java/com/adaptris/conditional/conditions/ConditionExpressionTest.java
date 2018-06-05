package com.adaptris.conditional.conditions;

import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.DefaultMessageFactory;
import com.adaptris.core.ServiceException;

import junit.framework.TestCase;

public class ConditionExpressionTest extends TestCase {

  private ConditionExpression condition;
  private AdaptrisMessage message;
  
  public void setUp() throws Exception {
    message = DefaultMessageFactory.getDefaultInstance().newMessage();
    condition = new ConditionExpression();
  }

  public void tearDown() throws Exception {
  }
  
  public void testMetadataExists() throws Exception {
    condition.setAlgorithm("%message{key1} > %message{key2}");
    message.addMessageHeader("key1", "10");
    message.addMessageHeader("key2", "5");
    
    assertTrue(condition.evaluate(message));
  }
  
  public void testMetadataDoesNotExist() throws Exception {
    condition.setAlgorithm("%message{key1} == 10");
    try {
      condition.evaluate(message);
      fail("Metadata does not exist, expect an exception");
    } catch (ServiceException ex) {
      // expected.
    }
  }
  
}
