# Jeff User Guide

Jeff is a **simple command-line task manager bot** that helps users track their tasks, deadlines, and events.  
It allows users to **add, delete, mark/unmark tasks, find specific tasks**, and **save them to a file** for future sessions.


## Setup and Installation

Prerequisites: JDK 17, Intellij 

1. Clone this repository into your computer
2. Open the project into Intellij as follows:
    1. Click `Open`.
    2. Select the project directory, and click `OK`.
    3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 17** (not other versions)
4. After that, locate the `src/main/java/jeff/Jeff and click 'Run'.

## Features
1.  Add **ToDo**, **Deadline**, and **Event** tasks  
2.  List all tasks  
3.  Mark/unmark tasks as completed  
4.  Delete tasks  
5.  Search for tasks by keyword or phrase  
6.  Saves tasks to a file 

---
### Adding Tasks

| Task Command | Description |
|-------------|------------|
| `todo TASK_NAME` | Adds a ToDo task |
| `deadline TASK_NAME /by DATE` | Adds a Deadline task |
| `event TASK_NAME /from START /to END` | Adds an Event task |

Example Usage:
- `todo Buy groceries`
- `deadline Submit report /by 2024-12-31`
- `event Project meeting /from 2pm /to 4pm`
---
### Managing Tasks
| Task Command         | Description              |
|----------------------|--------------------------|
| `list`               | Shows all tasks          |
| `mark TASK_NUMBER`   | Marks a task as done     |
| `unmark TASK_NUMBER` | Marks a task as not done |
| `delete TASK_NUMBER` | Deletes a task           |
Example Usage:
- `list` 
- `mark 1`
- `unmark 1`
- `delete 2`
- --
### Searching for Tasks
| Task Command   | Description                        |
|----------------|------------------------------------|
| `find KEYWORD` | Finds tasks containing the keyword |
| `find PHRASE`  | Finds tasks containing the phrase  |
Example Usage:
- `find report`
- `find project meeting`
- ---
### Exiting
| Task Command | Description   |
|--------------|---------------|
| `bye`        | Exits program |
Example Usage:
- `bye`