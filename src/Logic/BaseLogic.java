package Logic;

public abstract class BaseLogic {

    abstract void start();

    public void execute() {
        start();
        do {
            processLoop();
        } while (!isEnd());
    }

    abstract void processLoop();

    abstract boolean isEnd();
}
