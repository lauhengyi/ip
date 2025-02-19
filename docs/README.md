# L.A.U.R.A User Guide
A personal assistant that will always be there for you

It can help you
- Keep track of tasks
- Mark done and undone tasks
- Keep track of dates
- Add tags!

## Quick overview

There are fundamental operations that you need to know in order to use LAURA: 

These are:
- `list` - Show all the tasks available
- `todo DESCRIPTION` - Add a todo task
- `deadline DESCRIPTION /by DEADLINE` - Add a deadline task
- `event DESCRIPTION /from STARTTIME /to ENDTIME` - Add an event task
- `mark TASKNUMBER` - Mark a task as done
- `unmark TASKNUMBER` - Mark a task as undone
- `remove TASKNUMBER` - Remove a task
- `tag TASKNUMBER TAGNAME` - Tag a task

## Documentation

### List

Usage:
```dtd
list
```

Shows a list of all the tasks, along with their task number, and other accompanying details.

### Adding a Todo
Usage:
```dtd
todo DESCRIPTION
```
Adds a Todo task to LAURA

#### Params
- `DESCRIPTION`: The name of the task

Examples:
```dtd
todo Do mod mapping for SEP
todo Walk my dog
```
 
### Adding a Deadline
Usage:
```dtd
deadline DESCRIPTION DEADLINE
```
Adds a Deadline task to LAURA

#### Params
- `DESCRIPTION`: The name of the task
- `DEADLINE`: The deadline of the task. This needs to be a date in the format dd/mm/yyyy
 
Examples:
```dtd
deadline Get a birthday gift /by 13/04/2025
deadline Apply for internship /by 17/05/2025
```

### Adding an Event
Usage:
```dtd
event DESCRIPTION /from STARTTIME /to ENDTIME
```
Adds a Event task to LAURA

#### Params
- `DESCRIPTION`: The name of the task
- `STARTTIME`: The start time of the task. This needs to be a date in the format dd/mm/yyyy
- `ENDTIME`: The ending time of the task. This needs to be a date in the format dd/mm/yyyy

Examples:
```dtd
event Mod Bidding Round 0 /from 01/01/2025 /to 14/01/2025
event Recess week /from 23/02/2025 /to 30/02/2025
```

### Mark a Task as Done
Usage:
```dtd
mark TASKNUMBER
```
Mark the task with the corresponding Task Number as Done

#### Params
- `TASKNUMBER`: The task number of the task to be marked (This can be found with the `list` command)

Examples:
```dtd
mark 1
mark 4
```

### Mark a Task as Undone
Usage:
```dtd
unmark TASKNUMBER
```
Mark the task with the corresponding Task Number as undone

#### Params
- `TASKNUMBER`: The task number of the task to be marked (This can be found with the `list` command)

Examples:
```dtd
unmark 1
unmark 4
```
### Remove a task
Usage:
```dtd
remove TASKNUMBER
```
Remove the task with the corresponding Task Number from LAURA

#### Params
- `TASKNUMBER`: The task number of the task to be removed (This can be found with the `list` command)

Examples:
```dtd
remove 1
remove 4
```

### Tag a Task
Usage:
```dtd
remove TASKNUMBER TAGNAME
```
Tag the task with the corresponding Tag Number with the Tag Name

#### Params
- `TASKNUMBER`: The task number of the task to be tagged (This can be found with the `list` command)
- `TAGNAME`: The name of the tag to be tagged to the task (There can be no spaces)

Examples:
```dtd
tag 2 urgent
tag 4 birthday
```
