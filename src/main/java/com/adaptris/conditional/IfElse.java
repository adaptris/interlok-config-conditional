package com.adaptris.conditional;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.CoreException;
import com.adaptris.core.Service;
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
  private Service ifService;
  
  @NotNull
  private Service elseService;
  
  
  @Override
  public void doService(AdaptrisMessage msg) throws ServiceException {
    try {
      log.trace("Running logical 'IF', with condition class {}", this.getCondition().getClass().getSimpleName());
      if(this.getCondition().evaluate(msg)) {
        log.trace("Logical 'IF' evaluated to true, running service.");
        this.getIfService().doService(msg);
      } else {
        log.trace("Logical 'IF' evaluated to false, running 'else' service.");
        this.getElseService().doService(msg);
      }
    } catch (CoreException e) {
      throw new ServiceException(e);
    }
    
  }

  @Override
  public void prepare() throws CoreException {
    if(this.getCondition() == null)
      throw new CoreException("No condition has been set for logical 'IF'");
    this.getIfService().prepare();
    this.getElseService().prepare();
  }

  @Override
  protected void initService() throws CoreException {
    this.getIfService().init();
    this.getElseService().init();
  }

  @Override
  protected void closeService() {
    this.getIfService().close();
    this.getElseService().close();
  }
  
  @Override
  public void start() throws CoreException {
    this.getIfService().start();
    this.getElseService().start();
  }
  
  @Override
  public void stop() {
    this.getIfService().stop();
    this.getElseService().stop();
  }
  

  public Condition getCondition() {
    return condition;
  }

  public void setCondition(Condition condition) {
    this.condition = condition;
  }

  public Service getIfService() {
    return ifService;
  }

  public void setIfService(Service ifTrueService) {
    this.ifService = ifTrueService;
  }

  public Service getElseService() {
    return elseService;
  }

  public void setElseService(Service ifNottrueService) {
    this.elseService = ifNottrueService;
  }

}