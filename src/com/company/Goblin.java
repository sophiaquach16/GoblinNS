package com.company;

import java.util.concurrent.ThreadLocalRandom;

// nextInt is normally exclusive of the top value,
// so add 1 to make it inclusive
public class Goblin {


    private int health_points;
    private int attack_power;
    private int defense_power;

    public Goblin() {
        this.health_points = ThreadLocalRandom.current().nextInt(5, 10 + 1);
        this.attack_power = ThreadLocalRandom.current().nextInt(2, 3 + 1);
        this.defense_power = ThreadLocalRandom.current().nextInt(1, 2 + 1);
    }

    public int get_health_points() { return health_points; }
    public int get_attack_power() { return attack_power; }
    public int get_defense_power() { return defense_power; }

    public void set_health_points(int health_points) { this.health_points = health_points; }
    public void set_attack_power(int attack_power) { this.attack_power = attack_power; }
    public void set_defense_power(int defense_power) { this.defense_power = defense_power; }


}
