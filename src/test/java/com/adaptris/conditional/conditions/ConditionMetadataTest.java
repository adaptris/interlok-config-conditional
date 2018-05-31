package com.adaptris.conditional.conditions;

import com.adaptris.conditional.operator.Null;
import com.adaptris.conditional.operator.NotNull;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.DefaultMessageFactory;

import junit.framework.TestCase;

public class ConditionMetadataTest extends TestCase {
  
  private ConditionMetadata condition;
  private AdaptrisMessage message;
  
  public void setUp() throws Exception {
    message = DefaultMessageFactory.getDefaultInstance().newMessage();
    condition = new ConditionMetadata();
  }

  public void tearDown() throws Exception {
  }
  
  public void testMetadataExists() throws Exception {
    condition.setMetadataKey("key1");
    condition.setOperator(new NotNull());
    message.addMessageHeader("key1", "value1");
    
    assertTrue(condition.evaluate(message));
  }
  
  public void testMetadataDoesNotExist() throws Exception {
    condition.setMetadataKey("key1");
    condition.setOperator(new Null());
    
    assertTrue(condition.evaluate(message));
  }
  
  public void testMetadataNotSet() throws Exception {
    assertFalse(condition.evaluate(message));
  }
  
}
