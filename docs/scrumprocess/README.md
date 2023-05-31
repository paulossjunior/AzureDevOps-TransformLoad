# 📕Documentation: ScrumProcess
Subontology addresses the events that occur in a project that adopts Scrum, such as the Scrum ceremonies

## 🌀 Package's Data Model
![Domain Diagram](classdiagram.png)

### ⚡Entities

* **ScrumProject** : Is a Software Project that adopts Scrum in its process (Scrum Process).
* **ScrumProcess** : Is a General Performed Project Process (i.e., it is an overall process performed in a project) composed        of two types of Specific Performed Project Process.
* **ProductBacklogDefinition** : Aims at defining and prioritizing the functionalities to be produced in the Scrum Project.
* **Sprint** : A time-box used to perform a project activity.
* **Ceremony** : The ceremonies that compose a Sprint are Planning Meeting, Daily Standup Meeting, Review Meeting and Retrospective Meeting.
* **ScrumDevelopmentTask** : Is a Performed activity in a Scrum Project.

## ✒️ Mapping Rules
### ScrumProject
Is a Software Project that adopts Scrum in its process (Scrum Process).

#### From project to ScrumProject

* *name* **equal** *name*
* *description* **equal** *description*
* *id* **equal** *externalId*
* *internal_uuid* **equal** *internalId*
* *url* **equal** *url*

### Sprint
A time-box used to perform a project activity.

#### From interaction to Sprint

* *name* **equal** *name*
* *url* **equal** *url*
* *id* **equal** *externalId*
* *internal_uuid* **equal** *internalId*

### ScrumDevelopmentTask
Is a Performed activity in a Scrum Project.

#### From workitem to ScrumDevelopmentTask

* *id* **equal** *externalId*
* *internal_uuid* **equal** *internalId*
* *fields.System.Title* **equal** *name*

**When** *fields.System.WorkItemType* **equal** *Task*
