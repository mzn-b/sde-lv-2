package com.example.decafe;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

//Class that is used mainly to control certain assets of the Game like Machines, Upgrades and the Coin Score
public class Game {
    private final Machine coffeeMachine; // A Machine Object used to make Coffee
    private final Machine cakeMachine; // A Machine Object used to make Cake
    private final Upgrade coffeeUpgrade; // An Upgrade Object used to upgrade the Coffee Machine
    private final Upgrade cakeUpgrade; // An Upgrade Object used to upgrade the Cake Machine
    private final Upgrade playerUpgrade; // An Upgrade Object used to make the Player faster
    private int coinsEarned; // The amount of Coins earned/used in the Game - 0 at the beginning
    private final String filenameImageThreeCoins; // Image of small amount of money earned
    private final String filenameImageFourCoins; // Image of normal amount of money earned
    private final String filenameImageDollar; // Images of huge amount of money earned

    // Constructor
    Game(ImageView upgradeCoffee, ImageView upgradeCake, ImageView upgradePlayer){
        this.coffeeMachine = new Machine(5, "coffeeMachineWithCoffee.png", "coffeeMachine.png", "coffee");
        this.cakeMachine = new Machine(5, "kitchenAidUsed.png", "kitchenAid.png", "cake");
        this.coffeeUpgrade = new Upgrade(20, false, "coffeeUpgrade.png", "coffeeUsed.png",  upgradeCoffee);
        this.cakeUpgrade = new Upgrade(20, false, "cakeUpgrade.png", "cakeUsed.png", upgradeCake);
        this.playerUpgrade = new Upgrade(40, false, "upgradeSkates.png", "upgradeSkatesUsed.png",  upgradePlayer);
        this.coinsEarned = 0;
        this.filenameImageDollar = "5coins.png";
        this.filenameImageFourCoins = "4coins.png";
        this.filenameImageThreeCoins = "3coins.png";
    }

    // Method used to create an Image Object
    public static Image createImage(String filename) throws FileNotFoundException {
       InputStream stream = new FileInputStream(filename); // Convert path into stream
        return new Image(stream); // Convert stream to Image and return it
    }

    // Method to check if the Player can use a certain Upgrade
    public void checkUpgradePossible(Upgrade upgrade) throws FileNotFoundException {
        if (!upgrade.isUsed() && this.coinsEarned >= upgrade.getCoinsNeeded()) { // If upgrade was not already used and the Player earned enough coins to buy it
            // Enable the ImageView
            upgrade.getImageView().setDisable(false);
            // Set the Image to the "activated" Upgrade Image
            upgrade.getImageView().setImage(createImage(upgrade.getFilenameUpgradeNotUsed()));
        } else { // If the upgrade was used already or the Player hasn't enough coins to buy it
            // Disable the Image
            upgrade.getImageView().setDisable(true);
            // Set the Image to "deactivated" Upgrade Image
            upgrade.getImageView().setImage(createImage(upgrade.getFilenameUpgradeUsed()));
        }
    }

    // Method to do a certain upgrade
    public void doUpgrade(UpgradeEnum type, Player cofiBrew) throws FileNotFoundException {
        switch (type) { // Switch the type of upgrade you received
            case COFFEE -> { // If the player chose the coffee upgrade
                // Set the coin score according to what the upgrade cost + change Image and Disable upgrade
                coinsEarned = coffeeUpgrade.doUpgrades(coinsEarned);
                // Increase the speed of the Coffee Machine
                coffeeMachine.setDuration(2);
            }
            case CAKE -> { // If the player chose the cake upgrade
                // Set the coin score according to what the upgrade cost + change Image and Disable upgrade
                coinsEarned = cakeUpgrade.doUpgrades(coinsEarned);
                // Increase the speed of the Cake Machine
                cakeMachine.setDuration(2);
            }
            case PLAYER -> { // If the player chose the player upgrade
                // Set the coin score according to what the upgrade cost + change Image and Disable upgrade
                coinsEarned = playerUpgrade.doUpgrades(coinsEarned);
                // Increase the movement speed of the Player
                cofiBrew.setMovement(6);
            }
        }
    }

    // Method to increase coins earned according to how satisfied the customer was
    public void setCoinsEarned(Customer customer){
        if (customer.isGreen()){ // If customer was happy
            // Increase coin score by 5
            this.coinsEarned += 7;
        } else if (customer.isYellow()){ // If customer left in a "normal" mood
            // Increase coin score by 4
            this.coinsEarned += 5;
        }else if (customer.isRed()){ // If customer lef in a bad mood
            // Increase coin score by 3
            this.coinsEarned += 3;
        }
    }

    public Machine getCoffeeMachine() {
        return coffeeMachine;
    }

    public Machine getCakeMachine() {
        return cakeMachine;
    }

    public Upgrade getCoffeeUpgrade() {
        return coffeeUpgrade;
    }

    public Upgrade getCakeUpgrade() {
        return cakeUpgrade;
    }

    public Upgrade getPlayerUpgrade() {
        return playerUpgrade;
    }

    public int getCoinsEarned() {
        return coinsEarned;
    }

    public String getFilenameImageThreeCoins() {
        return filenameImageThreeCoins;
    }

    public String getFilenameImageFourCoins() {
        return filenameImageFourCoins;
    }

    public String getFilenameImageDollar() {
        return filenameImageDollar;
    }
}
