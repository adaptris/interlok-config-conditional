package com.adaptris.conditional;

import javax.validation.constraints.NotNull;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.conditional.service.NoOpService;
import com.adaptris.core.ComponentLifecycle;
import com.adaptris.core.ComponentLifecycleExtension;
import com.adaptris.core.CoreException;
import com.adaptris.core.Service;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>
 * This service holder is used to hold the service or list of services that will be executed by logical expressions, such as {@link IfElse} and {@link While}, should configured {@link Condition}'s pass.
 * </p>>
 * @author amcgrath
 *
 */
@XStreamAlias("then")
@AdapterComponent
@ComponentProfile(summary = "A service/list that should be executed after conditions have been met. ", tag = "service, conditional")
public class ThenService implements ComponentLifecycle, ComponentLifecycleExtension {

  @NotNull
  private Service service;
  
  public ThenService() {
    this.setService(new NoOpService());
  }
  
  @Override
  public void prepare() throws CoreException {
      service.prepare();
  }

  @Override
  public void init() throws CoreException {
      service.init();
  }

  @Override
  public void start() throws CoreException {
      service.start();
  }

  @Override
  public void stop() {
      service.stop();
  }

  @Override
  public void close() {
      service.close();
  }

  public Service getService() {
    return service;
  }

  public void setService(Service thenService) {
    this.service = thenService;
  }

}
