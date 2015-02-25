# Effektif

Effektif is a flexible workflow engine that you can embed into your own apps.  

## What does a workflow engine do?

A workflow is based on a diagram (eg BPMN or nodes and edges) and specify an execution flow to coordinate tasks, automatic activities and timers.  The workflow engine keeps track of each execution (aka workflow instance) and executes the activities as specified in the workflow.

## Why I build Effektif ?

Workflows interact more and more with cloud services over REST APIs.  
Those services don't participate in a transaction. The engine needs to 
save it's state before each activity is being executed.  

Secondly, support for pluggable persistence.  For starters our cloud 
hosted solution we want to use MongoDB for horizontal scalability.  
It's the first engine that separates the persistence aspects from the 
core engine.      

# Feature highlights
* Intuitive Java API & REST API
* Pluggable persistence (eg in-memory & horizontally scalable NoSQL persistence)
* Microservice architecture
* Designed for the cloud
* Apache 2.0 license

## Example

A workflow that looks like this

![Example diagram](files/README-diagram.png?raw=true "Workflow diagram")

Can be created and executed in your Java code like this:

```java
// Create the default (in-memory) workflow engine
Configuration configuration = new MemoryConfiguration();
WorkflowEngine workflowEngine = configuration.getWorkflowEngine();
TaskService taskService = configuration.getTaskService();

// Create a workflow
Workflow workflow = new Workflow()
  .sourceWorkflowId("Release")
  .activity("Move open issues", new UserTask()
    .transitionToNext())
  .activity("Check continuous integration", new UserTask()
    .transitionToNext())
  .activity("Notify community", new EmailTask()
    .to("releases@example.com")
    .subject("New version released")
    .bodyText("Enjoy!"));

// Deploy the workflow to the engine
String workflowId = workflowEngine
  .deployWorkflow(workflow)
  .checkNoErrorsAndNoWarnings()
  .getWorkflowId();

// Start a new workflow instance
TriggerInstance triggerInstance = new TriggerInstance()
  .workflowId(workflowId);
WorkflowInstance workflowInstance = workflowEngine.start(triggerInstance);

List<Task> tasks = taskService.findTasks(new TaskQuery());
assertEquals("Move open issues", tasks.get(0).getName());
assertEquals(1, tasks.size());
```

## Introduction

* [Who is behind this project?](https://github.com/effektif/effektif-oss/wiki/Team)
* [Roadmap](https://github.com/effektif/effektif-oss/wiki/Roadmap)

## User Documentation

* [Getting started](https://github.com/effektif/effektif-oss/wiki/Getting-started)
* [Building workflows](https://github.com/effektif/effektif-oss/wiki/Building-workflows)
* [Workflow engine types](https://github.com/effektif/effektif-oss/wiki/Workflow-engine-types)
* Create your own activity
* Create your own datasource
* Run the REST service
* [Advanced features](https://github.com/effektif/effektif-oss/wiki/Advanced-features)

## Developer Documentation

* [Building the sources](https://github.com/effektif/effektif-oss/wiki/Building-the-sources)
* Architecture
* [Start contributing](https://github.com/effektif/effektif-oss/wiki/Contributing)
