package com.adaptris.conditional.service;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.CoreException;
import com.adaptris.core.ServiceException;
import com.adaptris.core.ServiceImp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("no-op-service")
@AdapterComponent
@ComponentProfile(summary = "A service that literally does nothing. ", tag = "service, conditional")
public class NoOpService extends ServiceImp {

  @Override
  public void doService(AdaptrisMessage msg) throws ServiceException {
  }

  @Override
  public void prepare() throws CoreException {
  }

  @Override
  protected void initService() throws CoreException {
  }

  @Override
  protected void closeService() {
  }

}
