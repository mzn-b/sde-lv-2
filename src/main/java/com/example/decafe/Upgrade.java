package com.example.decafe;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Getter
@Setter
@AllArgsConstructor
// Function used to control all the methods used for Upgrades
public class Upgrade {
    private final int coinsNeeded;
    private boolean used;
    private final String filenameUpgradeNotUsed;
    private final String filenameUpgradeUsed;
    private final ImageView imageView;

    public int doUpgrades(int coin) throws FileNotFoundException {
        imageView.setImage(Game.createImage(filenameUpgradeUsed));
        imageView.setDisable(true);
        setUsed(true);
        coin -= coinsNeeded;
        return coin;
    }
}
