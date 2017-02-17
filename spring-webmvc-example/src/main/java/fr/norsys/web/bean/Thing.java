package fr.norsys.web.bean;

public class Thing {

    private String name;

    public Thing() {
        // default empty constructor
    }

    public Thing(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Thing [name=" + name + "]";
    }

}
