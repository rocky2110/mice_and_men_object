package Logic;

public abstract class BaseLogic {

    abstract void start();

    public void execute() {
        start();
        do {
            processLoop();
        } while (!isEnd());
        finish();
    }

    abstract void processLoop();

    abstract void finish();

    abstract boolean isEnd();
}
