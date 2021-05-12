package com.company;
import java.util.concurrent.ThreadLocalRandom;

// nextInt is normally exclusive of the top value,
// so add 1 to make it inclusive
public class Hero {


        private int health_points;
        private int attack_power;
        private int defense_power;
        private int[] potions;
        private int gold;
        private int level;
        private int goblins_slain;


        public Hero() {
                this.health_points = ThreadLocalRandom.current().nextInt(20, 30 + 1);
                this.attack_power = ThreadLocalRandom.current().nextInt(1, 3 + 1);
                this.defense_power = ThreadLocalRandom.current().nextInt(1, 5 + 1);
                this.potions = new int[]{2, 2, 2, 2, 2};
                this.gold = 0;
                this.level = 0;
                this.goblins_slain = 0;
        }

        public Hero(int gold) {
                this.health_points = ThreadLocalRandom.current().nextInt(20, 30 + 1);
                this.attack_power = ThreadLocalRandom.current().nextInt(1, 3 + 1);
                this.defense_power = ThreadLocalRandom.current().nextInt(1, 5 + 1);
                this.potions = new int[]{2, 2, 2, 2, 2};
                this.gold = gold;
        }

        public int get_health_points() { return health_points; }
        public int get_attack_power() { return attack_power; }
        public int get_defense_power() { return defense_power; }
        public int get_level() { return level; }
        public int get_gold() { return gold; }
        public int[] get_potions() { return potions;}
        public int get_goblins_slain() { return goblins_slain; }

        public void set_health_points(int health_points) { this.health_points = health_points; }
        public void set_attack_power(int attack_power) { this.attack_power = attack_power; }
        public void set_defense_power(int defense_power) { this.defense_power = defense_power; }
        public void set_level(int level) { this.level = level; }
        public void set_gold(int gold) { this.gold = gold; }
        public void set_potions(int[] potions) {this.potions = potions;}
        public void set_goblins_slain(int goblins_slain) { this.goblins_slain = goblins_slain; }
}
