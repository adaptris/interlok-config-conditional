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

@XStreamAlias("if-then-otherwise")
@AdapterComponent
@ComponentProfile(summary = "Runs the configured service/list 'IF' the configured condition is met, otherwise will run the 'else' service/list.", tag = "service, conditional")
public class IfElse extends ServiceImp {
  
  protected transient Logger log = LoggerFactory.getLogger(this.getClass().getName());

  @NotNull
  private Condition condition;
  
  @NotNull
  private ThenService then;
  
  @NotNull
  private ElseService otherwise;
  
  public IfElse() {
    this.setThen(new ThenService());
    this.setOtherwise(new ElseService());
  }
  
  
  @Override
  public void doService(AdaptrisMessage msg) throws ServiceException {
    try {
      log.trace("Running logical 'IF', with condition class {}", this.getCondition().getClass().getSimpleName());
      if(this.getCondition().evaluate(msg)) {
        log.trace("Logical 'IF' evaluated to true, running service.");
        this.getThen().getService().doService(msg);
      } else {
        log.trace("Logical 'IF' evaluated to false, running 'else' service.");
        this.getOtherwise().getService().doService(msg);
      }
    } catch (CoreException e) {
      throw new ServiceException(e);
    }
    
  }

  @Override
  public void prepare() throws CoreException {
    if(this.getCondition() == null)
      throw new CoreException("No condition has been set for logical 'IF'");
    this.getThen().prepare();
    this.getOtherwise().prepare();
  }

  @Override
  protected void initService() throws CoreException {
    this.getThen().init();
    this.getOtherwise().init();
  }

  @Override
  protected void closeService() {
    this.getThen().close();
    this.getOtherwise().close();
  }
  
  @Override
  public void start() throws CoreException {
    this.getThen().start();
    this.getOtherwise().start();
  }
  
  @Override
  public void stop() {
    this.getThen().stop();
    this.getOtherwise().stop();
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

  public void setThen(ThenService thenService) {
    this.then = thenService;
  }

  public ElseService getOtherwise() {
    return otherwise;
  }

  public void setOtherwise(ElseService elseService) {
    this.otherwise = elseService;
  }


}