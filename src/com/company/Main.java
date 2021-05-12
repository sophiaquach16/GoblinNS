package com.company;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        play();
    }

    public static void play() {
        Hero hero1 = new Hero();
        Scanner keyboard = new Scanner(System.in);
        int step_counter = 0;
        while (hero1.get_health_points() > 0) {
            System.out.println("~ Step " + ++step_counter + " ~~");
            if (step_counter == 10) {
                hero1.set_level(hero1.get_level()+1);
                System.out.println("You reached level " + hero1.get_level() + ".");
                System.out.println("Would you like to buy more potions for 4 gold each?");
                String x = keyboard.next();
                if (x == "Yes") {
                    buy_potions(hero1, keyboard);
                }

                break;
            }

            double rand = Math.random();
            if (rand <= 0.5) {
                fight(hero1);
            }

        }
    }
    public static void buy_potions(Hero hero1, Scanner keyboard) {
        System.out.println("How many potions, for 4 gold each would you like to purchase?");
        int request_amount = keyboard.nextInt();
        int cost = request_amount*2;
        if (cost <= hero1.get_gold()) {
            hero1.set_gold(hero1.get_gold()-cost);
            int[] new_potion_array = new int[5];
            for (int i=0; i>hero1.get_potions().length-1; i++) {
                new_potion_array[i] = hero1.get_potions()[i];
            }
            new_potion_array[5] = hero1.get_potions()[5]+request_amount;
            hero1.set_potions(new_potion_array);
        }
    }

    public static void play_again_prompt() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Game over! Would you like to play again?");
        String x = keyboard.next();
    }

    public static void fight(Hero hero1) {
        Goblin goblin = new Goblin();
        while (hero1.get_health_points() > 0 && goblin.get_health_points()>0 ) {
            // hero attacks first
            int attack_power_remaining = hero1.get_attack_power();
            if (goblin.get_defense_power() - hero1.get_attack_power() < 0) {
                attack_power_remaining = hero1.get_attack_power() - goblin.get_defense_power();
                goblin.set_defense_power(0);
                if (goblin.get_health_points() - attack_power_remaining > 0) { // goblin alive
                    goblin.set_health_points(goblin.get_health_points() - attack_power_remaining);
                    attack_power_remaining = goblin.get_attack_power();
                    if (hero1.get_defense_power() - attack_power_remaining > 0) {
                        hero1.set_health_points(hero1.get_defense_power() - attack_power_remaining);
                        attack_power_remaining = attack_power_remaining - hero1.get_defense_power();
                        // affect health points
                    }
                    else { play_again_prompt(); }
                }
                else { // goblin dies
                    hero1.set_goblins_slain(hero1.get_goblins_slain() + 1);
                    hero1.set_gold(hero1.get_gold() + 2);
                }
            } else goblin.set_defense_power(goblin.get_defense_power() - hero1.get_attack_power());
//                if goblin.get_defense_power()-hero1.get_attack_power()
//                goblin.set_defense_power(goblin.get_defense_power()-hero1.get_attack_power());
        }
    }

}
