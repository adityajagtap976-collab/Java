public class Task {
    private String title;
    private boolean done;

    public Task(String title) {
        this.title = title;
        this.done = false;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isDone() {
        return this.done;
    }

    public void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[x] " + this.title;
        }
        return "[ ] " + this.title;
    }

    public String toFileLine() {
        return this.done + "|" + this.title;
    }
}
