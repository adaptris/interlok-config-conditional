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

package com.adaptris.conditional;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.annotation.DisplayOrder;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.CoreException;
import com.adaptris.core.Service;
import com.adaptris.core.ServiceException;
import com.adaptris.core.ServiceImp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>
 * This {@link Service} allows you to test boolean (true or false) {@link Condition}'s, which if evaluate to "true" will run a configured set of services, otherwise run a different set of services.
 * </p>
 * <p>
 * Note, that although you must specify a service or list of services should the configured conditions pass, you do not have to configure services to run should the conditions fail.
 * </p>
 * <p>
 * Typically your {@link Condition} will test for equality, in-line expressions or whether values exist or not.  The values to test will generally come from the payload or message metadata. <br/>
 * Also note that some conditions can be nested, such that you can test that a value is equal to another AND / OR a value is equal/not to another value.
 * </p>
 * @author aaron
 *
 */
@XStreamAlias("if-then-otherwise")
@AdapterComponent
@ComponentProfile(summary = "Runs the configured service/list 'IF' the configured condition is met, otherwise will run the 'else' service/list.", tag = "service, conditional", since="3.7.3")
@DisplayOrder(order = {"condition", "then","otherwise"})
public class IfElse extends ServiceImp {

  protected transient Logger log = LoggerFactory.getLogger(this.getClass().getName());

  @NotNull
  private Condition condition;

  @NotNull
  private ThenService then;

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
        log.trace("Logical 'IF' evaluated to false, running 'otherwise' service.");
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
