package serverside;

public interface AuthService {
    void start();
    void stop();
    String getNickByLoginAndPassword(String login, String password);
}
