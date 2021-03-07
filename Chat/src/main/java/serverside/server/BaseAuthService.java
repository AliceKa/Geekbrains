package serverside.server;

import serverside.AuthService;

import java.util.ArrayList;
import java.util.List;

public class BaseAuthService implements AuthService {

    private List<Entry> entryList;
    public BaseAuthService() {
        entryList = new ArrayList<>();
        entryList.add(new Entry("David","pass1","nick1"));
        entryList.add(new Entry("Victor","pass2","nick2"));
        entryList.add(new Entry("Vladimir","pass3","nick3"));

    }

    @Override
    public void start() {
        System.out.println("AuthService start");
    }

    @Override
    public void stop() {
        System.out.println("AuthService stop");

    }
    @Override
    public String getNickByLoginAndPassword(String login, String password){
        for (Entry e: entryList) {
            if (e.login.equals(login) && e.password.equals(password)) {
                return e.nickname;
            }
        }
        return null;
    }

    private class Entry {
        private String login;
        private String password;
        private String nickname;

        public Entry(String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;
        }
    }
}
