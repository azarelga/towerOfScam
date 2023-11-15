package com.towerofscam.level;

public abstract class Ruangan {
   private boolean isEmpty;

    public boolean CekEmpty() {
        return this.isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

}
