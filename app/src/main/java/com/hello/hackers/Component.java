package com.hello.hackers;

public class Component {
    private String how_it_works;
    private String side_effects;
    private String used_for;
    private String instructions;

    public Component() {
    }

    public Component(String how_it_works, String side_effects, String used_for, String instructions) {
        this.how_it_works = how_it_works;
        this.side_effects = side_effects;
        this.used_for = used_for;
        this.instructions = instructions;
    }

    public String getHow_it_works() {
        return how_it_works;
    }

    public void setHow_it_works(String how_it_works) {
        this.how_it_works = how_it_works;
    }

    public String getSide_effects() {
        return side_effects;
    }

    public void setSide_effects(String side_effects) {
        this.side_effects = side_effects;
    }

    public String getUsed_for() {
        return used_for;
    }

    public void setUsed_for(String used_for) {
        this.used_for = used_for;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
