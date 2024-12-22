package com.example.mougreen;

public class Users {
    public String emailUsers;
    public String namaUsers;
    public String nomorRumahUsers;
    public String usernameUsers;
    public String roleUsers;

    public Users() {
    }


    public Users(String emailUsers, String namaUsers, String nomorRumahUsers, String roleUsers, String usernameUsers) {
        this.emailUsers = emailUsers;
        this.namaUsers = namaUsers;
        this.nomorRumahUsers = nomorRumahUsers;
        this.usernameUsers = usernameUsers;
        this.roleUsers = roleUsers;
    }

    public String getEmailUsers() {
        return emailUsers;
    }
    public void setEmailUsers(String emailUsers) {
        this.emailUsers = emailUsers;
    }

    public String getNamaUsers() {
        return namaUsers;
    }
    public void setNamaUsers(String namaUsers) {
        this.namaUsers = namaUsers;
    }

    public String getNomorRumahUsers() {
        return nomorRumahUsers;
    }
    public void setNomorRumahUsers(String nomorRumahUsers) {
        this.nomorRumahUsers = nomorRumahUsers;
    }

    public String getRoleUsers() {
        return roleUsers;
    }
    public void setRoleUsers(String roleUsers) {
        this.roleUsers = roleUsers;
    }

    public String getUsernameUsers() {
        return usernameUsers;
    }
    public void setUsernameUsers(String usernameUsers) {
        this.usernameUsers = usernameUsers;
    }


}