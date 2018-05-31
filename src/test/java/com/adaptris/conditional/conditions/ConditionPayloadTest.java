package com.adaptris.conditional.conditions;

import com.adaptris.conditional.operator.NotNull;
import com.adaptris.conditional.operator.Null;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.DefaultMessageFactory;

import junit.framework.TestCase;

public class ConditionPayloadTest extends TestCase {
  
  private ConditionPayload condition;
  private AdaptrisMessage message;
  
  public void setUp() throws Exception {
    message = DefaultMessageFactory.getDefaultInstance().newMessage();
    condition = new ConditionPayload();
  }

  public void tearDown() throws Exception {
  }
  
  public void testPayloadExists() throws Exception {
    condition.setOperator(new NotNull());
    message.setContent("some content", message.getContentEncoding());
    
    assertTrue(condition.evaluate(message));
  }
  
  public void testPayloadDoesNotExist() throws Exception {
    condition.setOperator(new Null());
    
    assertTrue(condition.evaluate(message));
  }
  
}
