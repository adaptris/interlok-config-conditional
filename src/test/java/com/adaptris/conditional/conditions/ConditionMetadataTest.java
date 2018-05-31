package com.adaptris.conditional.conditions;

import com.adaptris.conditional.operator.DoesNotExist;
import com.adaptris.conditional.operator.Exists;
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
    condition.setOperator(new Exists());
    message.addMessageHeader("key1", "value1");
    
    assertTrue(condition.evaluate(message));
  }
  
  public void testMetadataDoesNotExist() throws Exception {
    condition.setMetadataKey("key1");
    condition.setOperator(new DoesNotExist());
    
    assertTrue(condition.evaluate(message));
  }
  
  public void testMetadataNotSet() throws Exception {
    assertFalse(condition.evaluate(message));
  }
  
}
