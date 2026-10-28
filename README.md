# Java Workspace

One repo, one folder per project. JDK 25, no build tools.

## Open it
`File > Open Workspace from File...` > `Java.code-workspace`
(Not "Open Folder". The workspace file is what makes each project work independently.)

## Layout
```
Java/
  Java.code-workspace   <- always open this
  new-project.ps1       <- creates a new project from _template
  _template/            <- never edit your real work here
  atm/                  <- project 1
    src/Atm.java
```

## New project
```
.\new-project.ps1 todolist
```
Then rename `src/Main.java` to match your project and reload the window.

## Run a project
```
java atm/src/Atm.java
```
Run from the `Java` folder. One file = `java path/to/File.java`.

## Rules
1. One project = one folder. Never mix two projects in one `src`.
2. Commit after every working chunk: `git add . && git commit -m "atm: chunk 2 variables"`.
3. Compiled output (`bin/`) is never committed.
