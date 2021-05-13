package com.company;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Hero hero1 = new Hero();
        play(false, hero1);
    }

    public static void play(boolean played_before, Hero hero1) {
        if (played_before) {
            int temp_gold = hero1.get_gold();
            hero1 = new Hero(temp_gold); // get amount of gold
        }
        else { hero1 = new Hero(); }
        Scanner keyboard = new Scanner(System.in);
        int step_counter = 0;
        while (hero1.get_health_points() > 0) {
            System.out.println("~ Step " + ++step_counter + " ~~");
            if (step_counter == 10) { // every 10 steps
                hero1.set_level(hero1.get_level()+1);
                System.out.println("You reached level " + hero1.get_level() + ".");
                buy_potions(hero1, keyboard);
            }
            double rand = Math.random();
            if (rand <= 0.5) {
                System.out.println("Goblin encountered!");
                fight(hero1);
            }
            else { System.out.println("No goblin encountered."); }
        }
    }
    public static void buy_potions(Hero hero1, Scanner keyboard) {
        System.out.println("Would you like to buy more potions for 4 gold each?");
            String x = keyboard.next();
            if (x.equals("Yes")) {
            System.out.println("How many potions, for 4 gold each would you like to purchase?");
            int request_amount = keyboard.nextInt();
            int cost = request_amount*4;
            int current_potion_amount = 0;
            for (int i = 0; i > hero1.get_potions().length - 1; i++) {
                if (hero1.get_potions()[i] == 2) { current_potion_amount++; }
            }
            if (cost <= hero1.get_gold() && 10-current_potion_amount!=0) {
                hero1.set_gold(hero1.get_gold() - cost);
                int count = 0; // count of potions to add
                int[] temp_array = new int[5];
                for (int i = 0; i > hero1.get_potions().length - 1; i++) {
                     if (hero1.get_potions()[i]==0 && count < request_amount) {
                         temp_array[i] = 2;
                         current_potion_amount++;
                     }
                     else {temp_array[i] = hero1.get_potions()[i]; }
                }
                hero1.set_potions(temp_array);
            }
            else { System.out.println("Not enough space, or not enough gold"); }
        }
        System.out.println("Would you like to drink a potion?");
        x = keyboard.next();
        if (x.equals("Yes")) {
            int[] temp_array = new int[5];
            Boolean potion_not_taken = true;
            for (int i = 0; i > hero1.get_potions().length - 1; i++) {
                if (hero1.get_potions()[i]==2 && potion_not_taken) {
                    temp_array[i] = 0;
                    hero1.set_health_points(hero1.get_health_points()+2);
                    System.out.print("Potion taken!");
                    potion_not_taken = false;
                }
                else {temp_array[i] = hero1.get_potions()[i]; }
            }
            hero1.set_potions(temp_array);
        }
    }

    public static void play_again_prompt(Hero hero1) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Game over! Would you like to play again?");
        String x = keyboard.next();
        if (x.equals("Yes")) {
            System.out.println("You said yes");
            play(true, hero1);
        }
        else {
            System.out.println("Level: " + hero1.get_level());
            System.out.println("Goblins slain: " + hero1.get_goblins_slain());
            System.out.println("Thanks for playing!");
        }
    }

    public static void fight(Hero hero1) { // hero attacks first, then goblin
        Goblin goblin = new Goblin();
        int temp_defense = hero1.get_defense_power();
        while (hero1.get_health_points() > 0 && goblin.get_health_points() > 0) {
            int hero_attack_power_remaining = hero1.get_attack_power();
            if (goblin.get_defense_power() - hero1.get_attack_power() < 0) {
                hero_attack_power_remaining = hero_attack_power_remaining - goblin.get_defense_power();
                goblin.set_defense_power(0);
                if (goblin.get_health_points() - hero_attack_power_remaining > 0) { // goblin alive
                    goblin.set_health_points(goblin.get_health_points() - hero_attack_power_remaining);
                    // goblin attacks now
                    int goblin_attack_power_remaining = goblin.get_attack_power();
                    if (hero1.get_defense_power() - goblin_attack_power_remaining > 0) {
                        hero1.set_defense_power(hero1.get_defense_power() - goblin_attack_power_remaining);
                    } else {
                        goblin_attack_power_remaining = goblin_attack_power_remaining - hero1.get_defense_power();
                        hero1.set_defense_power(0);
                        if (hero1.get_health_points() - goblin_attack_power_remaining > 0) {
                            hero1.set_health_points(hero1.get_health_points() - goblin_attack_power_remaining);
                        } else {
                            hero1.set_health_points(0);
                            play_again_prompt(hero1);
                        }
                    }
                }
                else { // goblin dies
                    hero1.set_goblins_slain(hero1.get_goblins_slain() + 1);
                    hero1.set_gold(hero1.get_gold() + 2);
                    goblin.set_health_points(0);
                    }
                }
                else {
                    goblin.set_defense_power(goblin.get_defense_power() - hero_attack_power_remaining);
                }
        }
        hero1.set_defense_power(temp_defense);
    }

}
