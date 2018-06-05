package com.adaptris.conditional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.adaptris.conditional.conditions.ConditionAnd;
import com.adaptris.conditional.conditions.ConditionExpression;
import com.adaptris.conditional.conditions.ConditionMetadata;
import com.adaptris.conditional.conditions.ConditionOr;
import com.adaptris.conditional.operator.Equals;
import com.adaptris.conditional.operator.NotNull;
import com.adaptris.conditional.service.NoOpService;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.CoreException;
import com.adaptris.core.DefaultMessageFactory;
import com.adaptris.core.Service;
import com.adaptris.core.ServiceCase;
import com.adaptris.core.ServiceException;
import com.adaptris.core.services.LogMessageService;
import com.adaptris.core.util.LifecycleHelper;

public class IfElseTest  extends ServiceCase {

  private IfElse logicalExpression;

  private AdaptrisMessage message;
  
  private ThenService thenService;
  
  private ElseService elseService;
  
  @Mock private Service mockService;
  
  @Mock private Service mockElseService;
  
  @Mock private Condition mockCondition;
  
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    
    thenService = new ThenService();
    elseService = new ElseService();
    
    thenService.setService(mockService);
    elseService.setService(mockElseService);
    
    logicalExpression = new IfElse();
    logicalExpression.setThen(thenService);
    logicalExpression.setOtherwise(elseService);
    logicalExpression.setCondition(mockCondition);
    
    message = DefaultMessageFactory.getDefaultInstance().newMessage();
    
    this.startMe(logicalExpression);
    
    this.setBaseDir("build/example-xml");
  }
  
  public void tearDown() throws Exception {
    this.StopMe(logicalExpression);
  }
  
  public void testNoThenService() throws Exception {
    logicalExpression.getThen().setService(new NoOpService());
    
    when(mockCondition.evaluate(message))
        .thenReturn(true);

    // purely to re-initiate the NoOpService
    this.StopMe(logicalExpression);
    this.startMe(logicalExpression);
    
    logicalExpression.doService(message);
    
    verify(mockElseService, times(0)).doService(message);
  }
  
  public void testNoElseService() throws Exception {
    logicalExpression.getOtherwise().setService(new NoOpService());
    
    when(mockCondition.evaluate(message))
        .thenReturn(true);

    // purely to re-initiate the NoOpService
    this.StopMe(logicalExpression);
    this.startMe(logicalExpression);
    
    logicalExpression.doService(message);
    
    verify(mockService, times(1)).doService(message);
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
    verify(mockElseService, times(1)).doService(message);
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
    
    Equals equals = new Equals();
    equals.setValue("myValue");
    ConditionMetadata condition2 = new ConditionMetadata();
    condition2.setMetadataKey("key2");
    condition2.setOperator(equals);
    
    ConditionOr conditionOr = new ConditionOr();
    ConditionExpression conditionExpression = new ConditionExpression();
    conditionExpression.setAlgorithm("(%message{key1} - 10) == %message{key2}");
    conditionOr.getConditions().add(conditionExpression);
    conditionOr.getConditions().add(condition2);
    
    ConditionAnd conditionAnd = new ConditionAnd();
    conditionAnd.getConditions().add(condition);
    conditionAnd.getConditions().add(conditionOr);
    
    ThenService thenSrvc = new ThenService();
    ElseService elseSrvc = new ElseService();
    
    thenSrvc.setService(new LogMessageService());
    elseSrvc.setService(new LogMessageService());
    
    logicalExpression.setCondition(conditionAnd);
    logicalExpression.setThen(thenSrvc);
    logicalExpression.setOtherwise(elseSrvc);
    
 // We init and start the service in the setup, lets stop it.
    try {
      this.StopMe(logicalExpression);
    } catch (Exception e) {}
    
    
    return logicalExpression;
  }
}
