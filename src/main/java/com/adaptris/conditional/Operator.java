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

import com.adaptris.annotation.Removal;
import com.adaptris.conditional.conditions.ConditionMetadata;
import com.adaptris.conditional.conditions.ConditionPayload;

/**
 * <p>
 * Operators are used with {@link Condition}'s in configuration such as {@link ConditionMetadata}
 * and {@link ConditionPayload}.
 * </p>
 * <p>
 * While the {@link Condition} is the target for any test, such as metadata or payload, the Operator
 * is the actual test; "equals", "null" etc.
 * </p>
 * 
 * @author amcgrath
 * @deprecated since 3.9.0; config-conditional was promoted into interlok-core
 * 
 */
@Deprecated
@Removal(version = "3.11.0", message = "config-conditional was promoted into interlok-core")
public interface Operator extends com.adaptris.core.services.conditional.Operator {

}
