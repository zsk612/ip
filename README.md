# Duke project template

This is Duke, a Java project that allows you to store different types of tasks in a local file. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
1. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Import the project into Intellij as follows:
   1. Click `Open or Import`.
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. After the importing is complete, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the below:
   ```
   ---------------------
   Hello! I'm Duke
   What can I do for you?
   Tell me your plan!
   ---------------------
   file existed path: ...\data\tasks.txt
   ```

## Basic commands

This section introduces basic commands in Duke.

Here are a list of basic commands:
1. `list` 
1. `todo [task_name]`
1. `deadline [task_name] /by [date]`
1. `event [task_name] /at [date]`
1. `done [task_index]`
1. `undone [task_index]`
1. `delete [task_index]`
1. `find [task_name]`
1. `clear`
1. `bye`

#### `list`

Type `list` in the CLI to call this command.
<br />Format: `list`
<br />This command lists all tasks stored in the file.
<br />Example: 
* `list`

#### `todo [task_name]`

Type `todo` followed by a task name in the CLI to call this command.
<br />Format: `todo [task_name]`
<br />This command adds a task to be done where *task_name* is its name, and it is by default set as not done.
<br />Example: 
* `todo homework`
* `todo declare temperature`


#### `deadline [task_name] /by [date]`

Type `deadline` followed by a task name and `/by` followed by a date in the CLI to call this command.
<br />Format: `deadline [task_name] /by [date]`
<br />This command adds a dadline where *task_name* is its name and *date* is the deadline date, and it is by default set as not done.
<br /> This *date* can be a short phrase or in YYYY-MM-DD format, which will be shown in the list as MMM dd yyyy. For instance, 2010-11-30 will be shown as Nov 30 2010.
<br />Example: 
* `deadline homework /by 2020-11-20`
* `deadline declare temperature /by this afternoon`

#### `event [task_name] /at [date]`

Type `event` followed by a task name and `/at` followed by a date in the CLI to call this command.
<br />Format: `event [task_name] /at [date]`
<br />This command adds an event where *task_name* is its name and *date* is the event date, and it is by default set as not done.
<br /> This *date* can be a short phrase or in YYYY-MM-DD format, which will be shown in the list as MMM dd yyyy. For instance, 2010-11-30 will be shown as Nov 30 2010.
<br />Example: 
* `event soccer game /at 2021-01-20`
* `event dinner with friends /at next Tuesday`

#### `done [task_index]`

Type `done` followed by a valid task index in the CLI to call this command.
<br />Format: `done [task_index]`
<br />This command marks a task as done where *task_index* is its index.
<br />Example: 
* `done 2` 
<br />This will mark the second task in the list as done.

#### `undone [task_index]`

Type `undone` followed by a valid task index in the CLI to call this command.
<br />Format: `undone [task_index]`
<br />This command marks a task as not done where *task_index* is its index.
<br />Example: 
* `undone 2` 
<br />This will mark the second task in the list as undone.

#### `delete [task_index]`

Type `delete` followed by a valid task index in the CLI to call this command.
<br />Format: `delete [task_index]`
<br />This command delete a task where *task_index* is its index.
<br />Example: 
* `delete 2` 
<br />This will delete the second task in the list.

#### `find [task_name]`

Type `find` followed by a task name in the CLI to call this command.
<br />Format: `find [task_name]`
<br />This command finds all tasks that contain *task_name* in their task names.
<br />Example: 
* `find book` 
<br />This will return a list of tasks that contain `book` in their names.

#### `clear`

Type `clear` in the CLI to call this command.
<br />Format: `clear`
<br />This command clears all tasks stored in the file.
<br />Example: 
* `clear`

#### `bye`

Type `bye` in the CLI to call this command.
<br />Format: `bye`
<br />This command exits Duke.
<br />Example: 
* `bye`
