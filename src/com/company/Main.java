package com.company;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        play();
//        Scanner keyboard = new Scanner(System.in);
//        System.out.println("Game over! Would you like to play again?");
//        String x = keyboard.next();
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
            if (rand <= 50) {
                Goblin goblin = new Goblin();
                // hero attacks first
                int goblin_current_health = goblin.get_defense_power()-hero1.get_attack_power();
//                if goblin.get_defense_power()-hero1.get_attack_power()
//                goblin.set_defense_power(goblin.get_defense_power()-hero1.get_attack_power());
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

}
