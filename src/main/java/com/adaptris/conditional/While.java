package com.adaptris.conditional;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.annotation.InputFieldDefault;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.CoreException;
import com.adaptris.core.ServiceException;
import com.adaptris.core.ServiceImp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("while")
@AdapterComponent
@ComponentProfile(summary = "Runs the configured service/list repeatedly 'WHILE' the configured condition is met.", tag = "service, conditional")
public class While extends ServiceImp {
  
  protected transient Logger log = LoggerFactory.getLogger(this.getClass().getName());

  private static final int DEFAULT_MAX_LOOPS = 10;
  
  @NotNull
  private Condition condition;
  
  @NotNull
  private ThenService then;

  @InputFieldDefault("10")
  private Integer maxLoops;
  
  public While() {
    this.setMaxLoops(DEFAULT_MAX_LOOPS);
  }
  
  @Override
  public void doService(AdaptrisMessage msg) throws ServiceException {
    int loopCount = 0;
    try {
      log.trace("Running logical test on 'WHILE', with condition class {}", this.getCondition().getClass().getSimpleName());
      
      while(this.getCondition().evaluate(msg)) {
        log.trace("Logical 'IF' evaluated to true on WHILE test, running service.");
        getThen().getService().doService(msg);
        
        loopCount ++;
        if((this.getMaxLoops() > 0) && (loopCount >= this.getMaxLoops())) {
          log.debug("Reached maximum loops({}), breaking.", this.getMaxLoops());
          break;
        }
      }
      
      log.trace("Logical 'WHILE' completed, exiting.");
    } catch (CoreException e) {
      throw new ServiceException(e);
    }
    
  }

  @Override
  public void prepare() throws CoreException {
    if(this.getCondition() == null)
      throw new CoreException("No condition has been set for logical 'IF'");
    this.getThen().prepare();
  }

  @Override
  protected void initService() throws CoreException {
    this.getThen().init();
  }

  @Override
  protected void closeService() {
    this.getThen().close();
  }
  
  @Override
  public void start() throws CoreException {
    this.getThen().start();
  }
  
  @Override
  public void stop() {
    this.getThen().stop();
  }
  

  public Condition getCondition() {
    return condition;
  }

  public void setCondition(Condition condition) {
    this.condition = condition;
  }

  public ThenService getThen() {
    return then;
  }

  public void setThen(ThenService ifTrueService) {
    this.then = ifTrueService;
  }
  
  public Integer getMaxLoops() {
    return maxLoops;
  }

  public void setMaxLoops(Integer maxLoops) {
    this.maxLoops = maxLoops;
  }
}
