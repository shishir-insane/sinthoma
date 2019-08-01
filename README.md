# sinthoma
An in-progress comprehensive health and symptom tracker.


## High Level Component Architecture Diagram

![Sinthoma Components](docs/assets/images/Sinthoma-UML.png)


### Component Status Summary

| Layer | Component                  | Status      | Planned | Designed    | Developed   | Tested | Released |
|-------|----------------------------|-------------|---------|-------------|-------------|--------|----------|
| UI    | Sinthoma Web               | IN PROGRESS | YES     | YES         | IN PROGRESS | NO     | NO       |
| UI    | Sinthoma App               | NOT PLANNED | NO      | NO          | NO          | NO     | NO       |
| BFF   | Dashboard BFF              | IN PROGRESS | YES     | YES         | IN PROGRESS | NO     | NO       |
| BFF   | Daily Tracker BFF          | IN PROGRESS | YES     | IN PROGRESS | NO          | NO     | NO       |
| BFF   | Stats Reports BFF          | NOT PLANNED | NO      | NO          | NO          | NO     | NO       |
| CORE  | User Manager API           | IN PROGRESS | YES     | YES         | IN PROGRESS | NO     | NO       |
| CORE  | Symptom Manager API        | IN PROGRESS | YES     | YES         | IN PROGRESS | NO     | NO       |
| CORE  | Appointment Manager API    | IN PROGRESS | YES     | IN PROGRESS | NO          | NO     | NO       |
| CORE  | Medication Manager API     | NOT PLANNED | NO      | NO          | NO          | NO     | NO       |
| CORE  | Food Nutrition Manager API | NOT STARTED | NO      | NO          | NO          | NO     | NO       |
| CORE  | Report Manager API         | NOT STARTED | NO      | NO          | NO          | NO     | NO       |
| CORE  | Notification Manager API   | NOT STARTED | NO      | NO          | NO          | NO     | NO       |
| PROXY | Weather Proxy API          | NOT STARTED | NO      | NO          | NO          | NO     | NO       |
| PROXY | Email Proxy API            | NOT STARTED | NO      | NO          | NO          | NO     | NO       |
