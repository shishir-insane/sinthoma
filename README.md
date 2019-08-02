# sinthoma
An in-progress comprehensive health and symptom tracker.

[![Status badge](https://img.shields.io/badge/status-in%20progress-yellow)]
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f1cf31c9a98e4e07a1483ce861537d05)](https://app.codacy.com/app/shishir.insane/sinthoma?utm_source=github.com&utm_medium=referral&utm_content=shishir-insane/sinthoma&utm_campaign=Badge_Grade_Settings)
[![Build Status](https://travis-ci.com/shishir-insane/sinthoma.svg?branch=master)](https://travis-ci.com/shishir-insane/sinthoma)
[![codecov](https://codecov.io/gh/shishir-insane/sinthoma/branch/master/graph/badge.svg)](https://codecov.io/gh/shishir-insane/sinthoma)
[![License: LGPL v3](https://img.shields.io/badge/License-LGPL%20v3-blue.svg)](https://www.gnu.org/licenses/lgpl-3.0)

## High Level Component Architecture Diagram

![Sinthoma Components](docs/assets/images/Sinthoma-UML.png)


## Low Level Design


### Maven Module Design - Current State

![Sinthoma Maven Modules](docs/assets/images/Sinthoma-Maven-Modules.png)


## Status Summary

| #  | Layer | Component                  | Status      | Planned | Designed    | Developed   | Tested | Released |
|----|-------|----------------------------|-------------|---------|-------------|-------------|--------|----------|
| 1  | UI    | Sinthoma Web               | IN PROGRESS | YES     | YES         | IN PROGRESS | NO     | NO       |
| 2  | UI    | Sinthoma App               | NOT PLANNED | NO      | NO          | NO          | NO     | NO       |
| 3  | BFF   | Dashboard BFF              | IN PROGRESS | YES     | YES         | IN PROGRESS | NO     | NO       |
| 4  | BFF   | Daily Tracker BFF          | IN PROGRESS | YES     | IN PROGRESS | NO          | NO     | NO       |
| 5  | BFF   | Stats Reports BFF          | NOT PLANNED | NO      | NO          | NO          | NO     | NO       |
| 6  | CORE  | User Manager API           | IN PROGRESS | YES     | YES         | IN PROGRESS | NO     | NO       |
| 7  | CORE  | Symptom Manager API        | IN PROGRESS | YES     | YES         | IN PROGRESS | NO     | NO       |
| 8  | CORE  | Appointment Manager API    | IN PROGRESS | YES     | IN PROGRESS | NO          | NO     | NO       |
| 9  | CORE  | Medication Manager API     | NOT PLANNED | NO      | NO          | NO          | NO     | NO       |
| 10 | CORE  | Food Nutrition Manager API | NOT STARTED | NO      | NO          | NO          | NO     | NO       |
| 11 | CORE  | Report Manager API         | NOT STARTED | NO      | NO          | NO          | NO     | NO       |
| 12 | CORE  | Notification Manager API   | NOT STARTED | NO      | NO          | NO          | NO     | NO       |
| 13 | PROXY | Weather Proxy API          | NOT STARTED | NO      | NO          | NO          | NO     | NO       |
| 14 | PROXY | Email Proxy API            | NOT STARTED | NO      | NO          | NO          | NO     | NO       |

