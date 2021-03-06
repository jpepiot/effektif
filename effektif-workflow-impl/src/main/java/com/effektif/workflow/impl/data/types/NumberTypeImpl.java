/*
 * Copyright 2014 Effektif GmbH.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.effektif.workflow.impl.data.types;

import com.effektif.workflow.api.Configuration;
import com.effektif.workflow.api.types.NumberType;
import com.effektif.workflow.impl.data.AbstractDataType;
import com.effektif.workflow.impl.data.InvalidValueException;


/**
 * @author Tom Baeyens
 */
public class NumberTypeImpl extends AbstractDataType {

  public NumberTypeImpl(Configuration configuration) {
    super(NumberType.INSTANCE, Number.class, configuration);
  }
  
  @Override
  public boolean isStatic() {
    return true;
  }

  @Override
  public Object convertJsonToInternalValue(Object jsonValue) throws InvalidValueException {
    if (jsonValue instanceof Double) {
      return jsonValue;
    } else if (jsonValue instanceof Number) {
      return ((java.lang.Number)jsonValue).doubleValue();
    } else if (jsonValue instanceof String) { 
      try {
        return Double.parseDouble((String) jsonValue);
      } catch (NumberFormatException e) {
        throw new InvalidValueException("Invalid number string "+jsonValue);
      }
    }
    return null;
  }
}
