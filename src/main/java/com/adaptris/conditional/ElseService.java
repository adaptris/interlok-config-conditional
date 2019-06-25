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

import com.adaptris.annotation.AdapterComponent;
import com.adaptris.annotation.ComponentProfile;
import com.adaptris.annotation.Removal;
import com.adaptris.core.util.LoggingHelper;

/**
 * <p>
 * This service holder is used to hold the service or list of services that will be executed by
 * logical expressions, such as {@link IfElse} should configured {@link Condition}'s NOT pass.
 * </p>
 * >
 * 
 * @author amcgrath
 * @deprecated since 3.9.0; config-conditional was promoted into interlok-core
 * 
 */
@AdapterComponent
@ComponentProfile(summary = "A service/list that should be executed after conditions have NOT been met. ", tag = "service, conditional")
@Deprecated
@Removal(version = "3.11.0", message = "config-conditional was promoted into interlok-core")
public class ElseService extends com.adaptris.core.services.conditional.ElseService {

  private transient boolean warningLogged = false;

  public ElseService() {
    LoggingHelper.logDeprecation(warningLogged, () -> {
      warningLogged = true;
    }, this.getClass().getCanonicalName(),
        com.adaptris.core.services.conditional.ElseService.class.getCanonicalName());

  }

}
