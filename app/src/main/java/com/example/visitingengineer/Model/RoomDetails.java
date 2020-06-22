package com.example.visitingengineer.Model;

import java.io.Serializable;

public class RoomDetails implements Serializable {
    String roomname,boardname,variant;

    public RoomDetails() {

    }

    public RoomDetails(String roomname, String boardname, String variant) {
        this.roomname = roomname;
        this.boardname = boardname;
        this.variant = variant;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getBoardname() {
        return boardname;
    }

    public void setBoardname(String boardname) {
        this.boardname = boardname;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }
}
