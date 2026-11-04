public class Task {
    private String title;
    private boolean done;
    private Priority priority;

    public Task(String title, Priority priority) {
        this.title = title;
        this.priority = priority;
        this.done = false;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isDone() {
        return this.done;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[x] (" + this.priority + ") " + this.title;
        }
        return "[ ] (" + this.priority + ") " + this.title;
    }

    public String toFileLine() {
        return this.done + "|" + this.priority + "|" + this.title;
    }
}
