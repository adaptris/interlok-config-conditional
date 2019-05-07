/*
    Copyright Adaptris

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.adaptris.conditional.conditions;

import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.adaptris.conditional.operator.NotNull;
import com.adaptris.conditional.operator.Null;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.DefaultMessageFactory;
import com.adaptris.core.util.LifecycleHelper;

@SuppressWarnings("deprecation")
public class ConditionPayloadTest {
  
  private ConditionPayload condition;
  private AdaptrisMessage message;
  
  @Before
  public void setUp() throws Exception {
    message = DefaultMessageFactory.getDefaultInstance().newMessage();
    condition = new ConditionPayload();
    LifecycleHelper.initAndStart(condition);
  }

  @After
  public void tearDown() throws Exception {
    LifecycleHelper.stopAndClose(condition);
  }


  @Test
  public void testPayloadExists() throws Exception {
    condition.setOperator(new NotNull());
    message.setContent("some content", message.getContentEncoding());
    
    assertTrue(condition.evaluate(message));
  }
  
  @Test
  public void testPayloadDoesNotExist() throws Exception {
    condition.setOperator(new Null());
    
    assertTrue(condition.evaluate(message));
  }
  
}
