package com.example.decafe;


import javafx.scene.image.ImageView;
import java.io.FileNotFoundException;

// Function used to control all the methods used for Upgrades
public class Upgrade {
    private final int coinsNeeded;
    private boolean used;
    private final String filenameUpgradeNotUsed;
    private final String filenameUpgradeUsed;
    private final ImageView imageView;

    public Upgrade(int coinsNeeded, boolean used, String filenameUpgradeNotUsed, String filenameUpgradeUsed, ImageView imageView) {
        this.filenameUpgradeNotUsed = filenameUpgradeNotUsed;
        this.filenameUpgradeUsed = filenameUpgradeUsed;
        this.imageView = imageView;
        this.used = used;
        this.coinsNeeded = coinsNeeded;
    }

    public int doUpgrades(int coin) throws FileNotFoundException {
        imageView.setImage(Game.createImage(filenameUpgradeUsed));
        imageView.setDisable(true);
        setUsed(true);
        coin -= coinsNeeded;
        return coin;
    }

    public int getCoinsNeeded() {
        return coinsNeeded;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getFilenameUpgradeNotUsed() {
        return filenameUpgradeNotUsed;
    }

    public String getFilenameUpgradeUsed() {
        return filenameUpgradeUsed;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
