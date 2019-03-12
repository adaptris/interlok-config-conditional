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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.annotation.DisplayOrder;
import com.adaptris.annotation.InputFieldDefault;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.CoreException;
import com.adaptris.core.Service;
import com.adaptris.core.ServiceException;
import com.adaptris.core.ServiceImp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>
 * This {@link Service} allows you to test boolean (true or false) {@link Condition}'s, which if evaluate to "true" will run a configured set of services continuously until the configured conditions do not evaluate to true.
 * </p>
 * <p>
 * You can also set a value for the maximum amount of times your services will run regardless of whether your conditions continue to evaluate to true. <br/>
 * <pre>
 *  <max-loops>5</max-loops>
 * </pre>
 * The default value for the max-loops is 10.  Setting this value to 0, will loop forever until your configured conditions evaluate to false.
 * </p>
 * <p>
 * Typically your {@link Condition} will test for equality, in-line expressions or whether values exist or not.  The values to test will generally come from the payload or message metadata. <br/>
 * Also note that some conditions can be nested, such that you can test that a value is equal to another AND / OR a value is equal/not to another value.
 * </p>
 * @author aaron
 *
 */
@XStreamAlias("while")
@AdapterComponent
@ComponentProfile(summary = "Runs the configured service/list repeatedly 'WHILE' the configured condition is met.", tag = "service, conditional")
@DisplayOrder(order = {"condition,then,maxLoops"})
public class While extends ServiceImp {
  
  private static final int DEFAULT_MAX_LOOPS = 10;
  
  @NotNull
  @Valid
  private Condition condition;
  
  @NotNull
  @Valid
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
