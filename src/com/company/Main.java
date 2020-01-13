package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 900;
    public static int bossDamage = 70;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {250, 250, 250, 250, 200};
    public static int[] heroesDamage = {20, 20, 20, 0, 5};

    public static int heal = 90;
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Mental", "Heal", "ability"};

    public static void main(String[] args) {
        printStatistics();
        while (!isFinished()) {
            round();
        }
    }

    public static void healing() {
        for (int i = 0; i < heroesHealth.length; i++) {
            heroesHealth[i] = heroesHealth[i] + heal;
            System.out.println("heroeshealth heal heroesattack" + heal);

        }
    }
    public static void ability() {
        Random r = new Random();
        int coef = r.nextInt(2);
        if (coef == 1) {
            heroesHealth[4] = heroesHealth[4] + bossDamage;
            System.out.println("ability miss");
        } else {
            bossDamage = bossDamage = 70;
        }


    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length); //0, 1, 2
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static void round() {
        changeBossDefence();
        healing();
        ability();
        heroesHit();
        bossHit();
        printStatistics();
    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1]
                <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType.equals(heroesAttackType[i])) {
                    Random r = new Random();
                    int coef = r.nextInt(10) + 2; // 0,1,2,3,4,5
                    if (bossHealth - heroesDamage[i] * coef < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
                    System.out.println(heroesAttackType[i] +
                            " critically hit " + heroesDamage[i] * coef);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println("_________________");
        System.out.println("Boss health: " + bossHealth);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magic health: " + heroesHealth[1]);
        System.out.println("Kinetic health: " + heroesHealth[2]);
        System.out.println("medic health: " + heroesHealth[2]);
        System.out.println("ability health: " + heroesHealth[2]);

        System.out.println("_________________");
    }
}

