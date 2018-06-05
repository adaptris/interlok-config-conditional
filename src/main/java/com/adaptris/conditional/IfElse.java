package com.adaptris.conditional;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.CoreException;
import com.adaptris.core.ServiceException;
import com.adaptris.core.ServiceImp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("if-else")
@AdapterComponent
@ComponentProfile(summary = "Runs the configured service/list 'IF' the configured condition is met, otherwise will run the 'else' service/list.", tag = "service, conditional")
public class IfElse extends ServiceImp {
  
  protected transient Logger log = LoggerFactory.getLogger(this.getClass().getName());

  @NotNull
  private Condition condition;
  
  @NotNull
  private ThenService thenService;
  
  @NotNull
  private ElseService elseService;
  
  public IfElse() {
    this.setThenService(new ThenService());
    this.setElseService(new ElseService());
  }
  
  
  @Override
  public void doService(AdaptrisMessage msg) throws ServiceException {
    try {
      log.trace("Running logical 'IF', with condition class {}", this.getCondition().getClass().getSimpleName());
      if(this.getCondition().evaluate(msg)) {
        log.trace("Logical 'IF' evaluated to true, running service.");
        this.getThenService().getService().doService(msg);
      } else {
        log.trace("Logical 'IF' evaluated to false, running 'else' service.");
        this.getElseService().getService().doService(msg);
      }
    } catch (CoreException e) {
      throw new ServiceException(e);
    }
    
  }

  @Override
  public void prepare() throws CoreException {
    if(this.getCondition() == null)
      throw new CoreException("No condition has been set for logical 'IF'");
    this.getThenService().prepare();
    this.getElseService().prepare();
  }

  @Override
  protected void initService() throws CoreException {
    this.getThenService().init();
    this.getElseService().init();
  }

  @Override
  protected void closeService() {
    this.getThenService().close();
    this.getElseService().close();
  }
  
  @Override
  public void start() throws CoreException {
    this.getThenService().start();
    this.getElseService().start();
  }
  
  @Override
  public void stop() {
    this.getThenService().stop();
    this.getElseService().stop();
  }
  

  public Condition getCondition() {
    return condition;
  }

  public void setCondition(Condition condition) {
    this.condition = condition;
  }

  public ThenService getThenService() {
    return thenService;
  }

  public void setThenService(ThenService thenService) {
    this.thenService = thenService;
  }

  public ElseService getElseService() {
    return elseService;
  }

  public void setElseService(ElseService elseService) {
    this.elseService = elseService;
  }


}