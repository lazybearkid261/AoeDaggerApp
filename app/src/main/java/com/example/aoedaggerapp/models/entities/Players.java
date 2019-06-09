package com.example.aoedaggerapp.models.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.example.aoedaggerapp.models.fields.Description;
import com.example.aoedaggerapp.models.fields.Dob;
import com.example.aoedaggerapp.models.fields.HomeNet;
import com.example.aoedaggerapp.models.fields.Id;
import com.example.aoedaggerapp.models.fields.Name;
import com.example.aoedaggerapp.models.fields.NickName;
import com.example.aoedaggerapp.models.fields.Team;
import com.example.aoedaggerapp.models.fields.Title;

@Entity(tableName = "table_players")
public class Players {
    private Id id;

    private Name name;

    private NickName nickName;

    private Title title;

    private Description description;

    private HomeNet homeNet;

    private Team team;

    private Dob dob;

    public Players(){

    }

    public Players(Name name, NickName nickName, Title title, Description description, HomeNet homeNet, Team team, Dob dob) {
        this.name = name;
        this.nickName = nickName;
        this.title = title;
        this.description = description;
        this.homeNet = homeNet;
        this.team = team;
        this.dob = dob;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public NickName getNickName() {
        return nickName;
    }

    public void setNickName(NickName nickName) {
        this.nickName = nickName;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public HomeNet getHomeNet() {
        return homeNet;
    }

    public void setHomeNet(HomeNet homeNet) {
        this.homeNet = homeNet;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Dob getDob() {
        return dob;
    }

    public void setDob(Dob dob) {
        this.dob = dob;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name: " + getName().getStringValue() + "\n" +
                "NickName: " + getNickName().getStringValue() + "\n";

    }
}
