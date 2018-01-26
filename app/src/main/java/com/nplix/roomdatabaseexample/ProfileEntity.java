package com.nplix.roomdatabaseexample;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by PK on 1/23/2018.
 */
@Entity()
public class ProfileEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public Integer ID;
    public String Name;
    public String Email;

    public String About;
    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }



    @NonNull
    public Integer getID() {
        return ID;
    }

    public void setID(@NonNull Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
