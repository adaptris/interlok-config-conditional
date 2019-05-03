package com.adaptris.conditional.conditions;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.annotation.Removal;
import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.util.LoggingHelper;

/**
 * A javascript condition.
 * 
 * <p>
 * This makes use of the {@link Invocable} extension to {@link ScriptEngine}, to allow you to define
 * the function that will be executed to evaluate the condition. The function name should always be
 * {@code evaluateScript}; take a single parameter (in this case it will be the current
 * {@link AdaptrisMessage}; and return {@code true or false}. For instance to check a specific
 * metadata value then you might have this function definition
 * 
 * <pre>
 * {@code
     function evaluateScript(message) { 
       return message.getMetadataValue('myMetadataKey').equals('myValue'); 
     } 
 * }
 * </pre>
 * </p>
 * <p>
 * Similar to {@link com.adaptris.core.service.EmbeddedScriptingService}; the logger is bound as
 * {@code log}.
 * </p>
 * 
 * @deprecated since 3.9.0; config-conditional was promoted into interlok-core
 * @author amcgrath
 *
 */
@Deprecated
@Removal(version = "3.11.0", message = "config-conditional was promoted into interlok-core")
@AdapterComponent
@ComponentProfile(summary = "Condition that makes use of the built in nashorn scripting engine for condition evaluation", tag = "condition,service")
public class ConditionFunction
    extends com.adaptris.core.services.conditional.conditions.ConditionFunction {


  private transient boolean warningLogged = false;

  public ConditionFunction() {
    LoggingHelper.logDeprecation(warningLogged, () -> {
      warningLogged = true;
    }, this.getClass().getCanonicalName(),
        com.adaptris.core.services.conditional.conditions.ConditionFunction.class
            .getCanonicalName());

  }

  public ConditionFunction(String func) {
    this();
    setDefinition(func);
  }
}
