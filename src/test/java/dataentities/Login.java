package dataentities;

import io.qameta.allure.Step;

public class Login {
    private String username;

    private String password;

    public Login() {
    }


    public Login(String username, String password) {

        this.username = username;
        this.password=password;
    }

    @Step("Fetch login credentials and send to API")
    public String getUsername() {
        return username;
    }

    @Step("Fetch login credentials and send to API")
    public void setUsername(String username) {
        this.username = username;
    }

    @Step("Fetch login credentials and send to API")
    public String getPassword() {
        return password;
    }

    @Step("Fetch login credentials and send to API")
    public void setPassword(String password) {
        this.password = password;
    }


}
