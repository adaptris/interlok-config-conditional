package com.adaptris.conditional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.adaptris.conditional.conditions.ConditionMetadata;
import com.adaptris.conditional.conditions.ConditionOr;
import com.adaptris.conditional.operator.NotNull;
import com.adaptris.conditional.operator.Null;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.CoreException;
import com.adaptris.core.DefaultMessageFactory;
import com.adaptris.core.Service;
import com.adaptris.core.ServiceCase;
import com.adaptris.core.ServiceException;
import com.adaptris.core.services.LogMessageService;
import com.adaptris.core.util.LifecycleHelper;

public class WhileTest extends ServiceCase {

  private While logicalExpression;

  private AdaptrisMessage message;
  
  private ThenService thenService;
  
  @Mock private Service mockService;
  
  @Mock private Condition mockCondition;
  
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    
    thenService = new ThenService();
    thenService.setService(mockService);
    
    logicalExpression = new While();
    logicalExpression.setThen(thenService);
    logicalExpression.setCondition(mockCondition);
    
    message = DefaultMessageFactory.getDefaultInstance().newMessage();
    
    this.startMe(logicalExpression);
    
    this.setBaseDir("build/example-xml");
  }
  
  public void tearDown() throws Exception {
    this.StopMe(logicalExpression);
  }
  
  public void testShouldRunServiceOnce() throws Exception {
    when(mockCondition.evaluate(message))
        .thenReturn(true)
        .thenReturn(false);
    
    logicalExpression.doService(message);
    
    verify(mockService, times(1)).doService(message);
  }
  
  public void testShouldRunServiceMaxDefault() throws Exception {
    when(mockCondition.evaluate(message))
        .thenReturn(true);
    
    logicalExpression.doService(message);
    
    verify(mockService, times(10)).doService(message);
  }
  
  public void testShouldRunServiceConfiguredFive() throws Exception {
    when(mockCondition.evaluate(message))
        .thenReturn(true);
    
    logicalExpression.setMaxLoops(5);
    logicalExpression.doService(message);
    
    verify(mockService, times(5)).doService(message);
  }
  
  public void testShouldRunServiceUnconfiguredFive() throws Exception {
    when(mockCondition.evaluate(message))
        .thenReturn(true)
        .thenReturn(true)
        .thenReturn(true)
        .thenReturn(true)
        .thenReturn(true)
        .thenReturn(false);
    
    logicalExpression.setMaxLoops(0); // loop forever
    logicalExpression.doService(message);
    
    
    verify(mockService, times(5)).doService(message);
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
    condition.setOperator(new NotNull());
    
    ConditionMetadata condition2 = new ConditionMetadata();
    condition2.setMetadataKey("key2");
    condition2.setOperator(new Null());
    
    ConditionOr conditionOr = new ConditionOr();
    conditionOr.getConditions().add(condition);
    conditionOr.getConditions().add(condition2);
    
    ThenService thenSrvc = new ThenService();
    thenSrvc.setService(new LogMessageService());
    
    logicalExpression.setCondition(conditionOr);
    logicalExpression.setThen(thenSrvc);
    
    // We init and start the service in the setup, lets stop it.
    try {
      this.StopMe(logicalExpression);
    } catch (Exception e) {}
    
    return logicalExpression;
  }
}