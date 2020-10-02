# User Guide

## Features 

### Feature 1 
#### `list`
This command lists all tasks stored in the file.

### Feature 2 
#### `todo [task_name]`
This command adds a task to be done where *task_name* is its name, and it is by default set as not done.

### Feature 3 
#### `deadline [task_name] /by [date]`
This command adds a dadline where *task_name* is its name and *date* is the deadline date, and it is by default set as not done.

### Feature 4 
#### `event [task_name] /at [date]`
This command adds an event where *task_name* is its name and *date* is the event date, and it is by default set as not done.

### Feature 5 
#### `done [task_index]`
This command marks a task as done where *task_index* is its index.

### Feature 6 
#### `undone [task_index]`
This command marks a task as not done where *task_index* is its index.

### Feature 7 
#### `delete [task_index]`
This command delete a task where *task_index* is its index.

### Feature 8 
#### `find [task_name]`
This command finds all tasks that contain *task_name* in their task names.

### Feature 9 
#### `clear`
This command clears all tasks stored in the file.

### Feature 10 
#### `bye`
This command exits Duke.

## Usage

This section introduces basic commands in Duke.

#### `list`

Type `list` in the CLI to call this command.
<br />Format: `list`
<br />This command lists all tasks stored in the file.
<br />Example: 
* `list`
<br /> Expected outcome:
```
---------------------
Here are the tasks in your list:
1. [T][✘] homework
---------------------
---------------------
What else?
---------------------
```

#### `todo [task_name]`

Type `todo` followed by a task name in the CLI to call this command.
<br />Format: `todo [task_name]`
<br />This command adds a task to be done where *task_name* is its name, and it is by default set as not done.
<br />Example: 
* `todo declare temperature`
<br /> Expected outcome:
```
---------------------
Got it. I've added this task:
	[T][✘] declare temperature
Now you have 2 tasks
---------------------
---------------------
What else?
---------------------
```

#### `deadline [task_name] /by [date]`

Type `deadline` followed by a task name and `/by` followed by a date in the CLI to call this command.
<br />Format: `deadline [task_name] /by [date]`
<br />This command adds a dadline where *task_name* is its name and *date* is the deadline date, and it is by default set as not done.
<br /> This *date* can be a short phrase or in YYYY-MM-DD format, which will be shown in the list as MMM dd yyyy. For instance, 2010-11-30 will be shown as Nov 30 2010.
<br />Example: 
* `deadline homework /by 2020-11-20`
* `deadline declare temperature /by this afternoon`
<br /> Expected outcome:
```
---------------------
Got it. I've added this task:
	[D][✘] homework (by: Nov 20 2020)
Now you have 3 tasks
---------------------
---------------------
What else?
---------------------
```
```
---------------------
Got it. I've added this task:
	[D][✘] declare temperature (by: this afternoon)
Now you have 4 tasks
---------------------
---------------------
What else?
---------------------
```

#### `event [task_name] /at [date]`

Type `event` followed by a task name and `/at` followed by a date in the CLI to call this command.
<br />Format: `event [task_name] /at [date]`
<br />This command adds an event where *task_name* is its name and *date* is the event date, and it is by default set as not done.
<br /> This *date* can be a short phrase or in YYYY-MM-DD format, which will be shown in the list as MMM dd yyyy. For instance, 2010-11-30 will be shown as Nov 30 2010.
<br />Example: 
* `event soccer game /at 2021-01-20`
* `event dinner with friends /at next Tuesday`
<br /> Expected outcome:
```
---------------------
Got it. I've added this task:
	[E][✘] soccer game (at: Jan 20 2021)
Now you have 5 tasks
---------------------
---------------------
What else?
---------------------
```
```
---------------------
Got it. I've added this task:
	[E][✘] dinner with friends (at: next Tuesday)
Now you have 6 tasks
---------------------
---------------------
What else?
---------------------
```

#### `done [task_index]`

Type `done` followed by a valid task index in the CLI to call this command.
<br />Format: `done [task_index]`
<br />This command marks a task as done where *task_index* is its index.
<br />Example: 
* `done 2` 
<br />This will mark the second task in the list as done.
<br /> Expected outcome:
```
---------------------
Nice! I've marked this task as done: 
	[T][✓] declare temperature
---------------------
What else?
---------------------
```

#### `undone [task_index]`

Type `undone` followed by a valid task index in the CLI to call this command.
<br />Format: `undone [task_index]`
<br />This command marks a task as not done where *task_index* is its index.
<br />Example: 
* `undone 3` 
<br />This will mark the third task in the list as undone.
<br /> Expected outcome:
```
---------------------
Oops! I've marked this task as undone: 
	[D][✘] homework (by: Nov 20 2020)
---------------------
What else?
---------------------
```

#### `delete [task_index]`

Type `delete` followed by a valid task index in the CLI to call this command.
<br />Format: `delete [task_index]`
<br />This command delete a task where *task_index* is its index.
<br />Example: 
* `delete 4` 
<br />This will delete the fourth task in the list.
<br /> Expected outcome:
```
---------------------
Got it. I've removed this task:
	[D][✘] declare temperature (by: this afternoon)
Now you have 5 tasks
---------------------
---------------------
What else?
---------------------
```

#### `find [task_name]`

Type `find` followed by a task name in the CLI to call this command.
<br />Format: `find [task_name]`
<br />This command finds all tasks that contain *task_name* in their task names.
<br />Example: 
* `find dinner` 
<br />This will return a list of tasks that contain `dinner` in their names.
<br /> Expected outcome:
```
---------------------
Here are the matching tasks in your list:
1. [E][✘] dinner with friends (at: next Tuesday)
---------------------
---------------------
What else?
---------------------
```

#### `clear`

Type `clear` in the CLI to call this command.
<br />Format: `clear`
<br />This command clears all tasks stored in the file.
<br />Example: 
* `clear`
<br /> Expected outcome:
```
---------------------
I've cleared all the tasks!
---------------------
---------------------
What else?
---------------------
```

#### `bye`

Type `bye` in the CLI to call this command.
<br />Format: `bye`
<br />This command exits Duke.
<br />Example: 
* `bye`
<br /> Expected outcome:
```
---------------------
Bye. Hope to see you again soon!
---------------------
```

