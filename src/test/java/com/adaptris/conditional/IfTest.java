package com.adaptris.conditional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.adaptris.conditional.conditions.ConditionMetadata;
import com.adaptris.conditional.operator.Exists;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.CoreException;
import com.adaptris.core.DefaultMessageFactory;
import com.adaptris.core.Service;
import com.adaptris.core.ServiceCase;
import com.adaptris.core.ServiceException;
import com.adaptris.core.services.LogMessageService;
import com.adaptris.core.util.LifecycleHelper;

public class IfTest extends ServiceCase {

  private If logicalExpression;

  private AdaptrisMessage message;
  
  @Mock private Service mockService;
  
  @Mock private Condition mockCondition;
  
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    
    logicalExpression = new If();
    logicalExpression.setIfService(mockService);
    logicalExpression.setCondition(mockCondition);
    
    message = DefaultMessageFactory.getDefaultInstance().newMessage();
    
    this.startMe(logicalExpression);
    
    this.setBaseDir("build/example-xml");
  }
  
  public void tearDown() throws Exception {
    this.StopMe(logicalExpression);
  }
  
  public void testShouldRunService() throws Exception {
    when(mockCondition.evaluate(message))
        .thenReturn(true);
    
    logicalExpression.doService(message);
    
    verify(mockService).doService(message);
  }
  
  public void testShouldNotRunService() throws Exception {
    when(mockCondition.evaluate(message))
        .thenReturn(false);
    
    logicalExpression.doService(message);
    
    verify(mockService, times(0)).doService(message);
  }
  
  public void testInnerServiceExceptionPropagated() throws Exception {
    when(mockCondition.evaluate(message))
        .thenReturn(true);
    doThrow(new ServiceException())
        .when(mockService)
        .doService(message);
    
    try {
      logicalExpression.doService(message);
      fail("Expected a service exception");
    } catch (ServiceException ex) {
      // expected.
    }
  }
  
  public void testNoConditionSet() throws Exception {
    logicalExpression.setCondition(null);
    try {
      logicalExpression.prepare();
      fail("Expected an exception because the condition is null");
    } catch (CoreException ex) {
      // expected
    }
    
  }
  
  private void startMe(Service service) throws Exception {
    LifecycleHelper.init(service);
    LifecycleHelper.start(service);
  }
  
  private void StopMe(Service service) throws Exception {
    LifecycleHelper.stop(service);
    LifecycleHelper.close(service);
  }

  @Override
  protected Object retrieveObjectForSampleConfig() {
    ConditionMetadata condition = new ConditionMetadata();
    condition.setMetadataKey("key1");
    condition.setOperator(new Exists());
    
    logicalExpression.setCondition(condition);
    logicalExpression.setIfService(new LogMessageService());
    
    // We init and start the service in the setup, lets stop it.
    try {
      this.StopMe(logicalExpression);
    } catch (Exception e) {}
    
    return logicalExpression;
  }
}
