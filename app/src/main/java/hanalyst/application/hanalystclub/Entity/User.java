package hanalyst.application.hanalystclub.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String email;
    private String password;
    private String bio;
    private int experience;
    private String teamId;

    public User(@NonNull String id, String name, String email, String password, String bio, int experience, String teamId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.experience = experience;
        this.teamId = teamId;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
