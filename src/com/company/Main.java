package com.company;

import java.util.Random;

public class Main {
    public static int medicAbility = 30;

    public static int bossHealth = 3000;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {250, 220, 230, 360, 700, 170, 250, 200};
    public static int[] heroesDamage = {10, 15, 20, 0, 3, 5, 5, 10};
    public static String[] heroesAttackType = {"Physical", "Magical",
            "Kinetic", "Medic", "Golem", "Lucky", "Berserk", "Thor"};
    public static int roundNumber = 0;

    public static int berserkAbility = bossDamage * 1 / 5;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        medicProcedure();
        printStatistics();
    }

    public static void chooseBossDefence() {
        for (int i = 0; i < heroesAttackType.length; i++) {
            Random random = new Random();

            int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
            if (randomIndex == 3) {
                continue;
            } else {
                bossDefence = heroesAttackType[randomIndex];
                break;
            }

        }
    }

    public static void bossHits() {
        int luckyAbility;
        int golemAbility = bossDamage * 1 / 5;
        Random in = new Random();
        boolean chanceLucky = in.nextBoolean();
        Random random = new Random();
        boolean skip = random.nextBoolean();


        for (int j = 0; j < heroesHealth.length; j++) {

            if (heroesHealth[4] != 0 && heroesHealth[4] > 0) {
                if (heroesHealth[j] == 0 && heroesHealth[j] < 0) {
                    continue;
                }
                if (heroesHealth[j] == heroesHealth[4]) {
                    continue;
                }
                if (chanceLucky == true) {
                    if (skip == true) {
                        break;
                    }
                    if (heroesHealth[j] == heroesHealth[5]) {
                        continue;
                    }
                }
                if (skip == true) {
                    break;
                } else
                    heroesHealth[4] = heroesHealth[4] - golemAbility;

            }

        }


        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[4] != 0 && heroesHealth[4] > 0) {
                    if (heroesHealth[i] != 0 && heroesHealth[i] > 0) {
                        if ((heroesHealth[i] - (bossDamage - golemAbility)) < 0) {
                            heroesHealth[i] = 0;
                        } else {
                            if (skip == true) {
                                break;
                            }

                            if (chanceLucky == true) {
                                if (heroesHealth[i] == heroesHealth[5]) {
                                    continue;
                                }
                            }
                            if (heroesHealth[i] == heroesHealth[6]) {
                                heroesHealth[i] += berserkAbility;
                            }

                            heroesHealth[i] = heroesHealth[i] - (bossDamage - golemAbility);
                        }


                    }
                } else if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    if (skip == true) {
                        break;
                    }
                    if (chanceLucky == true) {
                        if (heroesHealth[i] == heroesHealth[5]) {
                            continue;
                        }
                    }
                    if (heroesHealth[i] == heroesHealth[6]) {
                        heroesHealth[i] += berserkAbility;
                    }
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }


        }
    }

    public static void medicProcedure() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < 100) {
                if (heroesHealth[3] <= 0) {
                    break;
                }
                if (heroesHealth[i] == 0) {
                    continue;
                }
                if (heroesHealth[3] < 100) {
                    continue;
                }
                if (heroesHealth[i] == heroesHealth[3]) {
                    continue;
                }
                heroesHealth[i] = heroesHealth[i] + medicAbility;
                break;

            }

        }

    }

    public static int berserkPower = heroesDamage[6] + berserkAbility;


    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefence == heroesAttackType[i]) {
                    if (heroesAttackType[i] == heroesAttackType[3]) {
                        System.out.println("Critical damage: " + 0);
                        continue;
                    }
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        if (heroesDamage[i] == heroesDamage[6]) {
                            heroesDamage[i] += berserkAbility;
                        }
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);


                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        if (heroesDamage[i] == heroesDamage[6]) {
                            heroesDamage[i] = berserkPower;
                        } else {
                            bossHealth = bossHealth - heroesDamage[i];
                        }
                    }
                }
            }


        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        boolean allHeroesDead = true;
        for (int healthOfCurrentHero : heroesHealth) {
            if (healthOfCurrentHero > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        if (roundNumber == 0) {
            System.out.println("BEFORE START -------------");
        } else {
            System.out.println("ROUND " + roundNumber + " -------------");
        }
        /*String value;
        if (bossDefence == null) {
            value = "No defence";
        } else {
            value = bossDefence;
        }*/
        System.out.println("Boss health: " + bossHealth + "; damage: "
                + bossDamage + "; defence: "
                + (bossDefence == null ? "No defence" : bossDefence));
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " +
                    heroesHealth[i] + "; damage: " + heroesDamage[i]);


        }
    }
}