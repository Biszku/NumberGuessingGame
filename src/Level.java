public enum Level {
    EASY(10, "Easy"),
    MEDIUM(5, "Medium"),
    HARD(3, "Hard");

    private final int maxAttempts;
    private final String name;

    Level(int maxAttempts, String name) {
        this.maxAttempts = maxAttempts;
        this.name = name;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    @Override
    public String toString() {
        return name;
    }
}
