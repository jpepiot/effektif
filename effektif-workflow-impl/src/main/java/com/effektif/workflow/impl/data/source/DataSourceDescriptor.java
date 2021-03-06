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
package com.effektif.workflow.impl.data.source;

import java.util.HashMap;
import java.util.Map;

import com.effektif.workflow.impl.data.types.CustomType;


/**
 * @author Tom Baeyens
 */
public class DataSourceDescriptor {

  protected String dataSourceKey;
  protected String label;
  protected Map<String,CustomType> types;

  public String getDataSourceKey() {
    return this.dataSourceKey;
  }
  public void setDataSourceKey(String dataSourceKey) {
    this.dataSourceKey = dataSourceKey;
  }
  public DataSourceDescriptor dataSourceKey(String dataSourceKey) {
    this.dataSourceKey = dataSourceKey;
    return this;
  }

  public String getLabel() {
    return this.label;
  }
  public void setLabel(String label) {
    this.label = label;
  }
  public DataSourceDescriptor label(String label) {
    this.label = label;
    return this;
  }
  
  public Map<String, CustomType> getTypes() {
    return types;
  }
  
  public void setTypes(Map<String, CustomType> types) {
    this.types = types;
  }
  
  public void type(CustomType customType) {
    if (types==null) {
      this.types = new HashMap<>();
    }
    this.types.put(customType.getKey(), customType);
  }
}
