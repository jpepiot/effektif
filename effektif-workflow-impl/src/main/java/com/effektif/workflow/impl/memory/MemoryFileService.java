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
package com.effektif.workflow.impl.memory;

import java.util.HashMap;
import java.util.Map;

import com.effektif.workflow.api.acl.Authentication;
import com.effektif.workflow.api.acl.Authentications;
import com.effektif.workflow.api.model.FileId;
import com.effektif.workflow.api.model.UserId;
import com.effektif.workflow.impl.file.File;
import com.effektif.workflow.impl.file.FileService;
import com.effektif.workflow.impl.util.Time;


/**
 * @author Tom Baeyens
 */
public class MemoryFileService implements FileService {
  
  Map<FileId,MemoryFile> files = new HashMap<>();
  long nextId = 1;
  
  @Override
  public File createFile(File file) {
    MemoryFile memoryFile = null;
    if (file instanceof MemoryFile) {
      memoryFile = (MemoryFile) file;
    } else {
      memoryFile = new MemoryFile(file);
    }
    
    Authentication authentication = Authentications.current();
    String organizationId = authentication!=null ? authentication.getOrganizationId() : null;
    if (organizationId!=null) {
      memoryFile.setOrganizationId(organizationId);
    }
    String userId = authentication!=null ? authentication.getUserId() : null;
    if (userId!=null) {
      memoryFile.creatorId(new UserId(userId));
    }
    
    FileId fileId = new FileId(Long.toString(nextId++));
    memoryFile.setId(fileId);
    memoryFile.createTime(Time.now());
    files.put(fileId, memoryFile);
    
    return file;
  }

  @Override
  public File getFileById(FileId fileId) {
    return files.get(fileId);
  }
}
