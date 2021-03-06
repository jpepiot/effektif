/* Copyright (c) 2014, Effektif GmbH.
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
 * limitations under the License. */
package com.effektif.workflow.impl.data.types;

import com.effektif.workflow.api.Configuration;
import com.effektif.workflow.api.types.JavaBeanType;
import com.effektif.workflow.api.types.Type;
import com.effektif.workflow.impl.identity.User;


/**
 * @author Tom Baeyens
 */
public class UserTypeImpl extends JavaBeanTypeImpl<JavaBeanType> {
  
  public UserTypeImpl(Configuration configuration) {
    super(User.class, configuration);
  }

  public UserTypeImpl(Type type, Configuration configuration) {
    super(User.class, configuration);
  }

  @Override
  protected void initializeFields(Configuration configuration) {
    addField(new JavaBeanFieldImpl(User.class, "id", new UserIdTypeImpl(configuration)));
    addField(new JavaBeanFieldImpl(User.class, "fullName", new TextTypeImpl(configuration)));
    addField(new JavaBeanFieldImpl(User.class, "email", new TextTypeImpl(configuration)));
  }
}
