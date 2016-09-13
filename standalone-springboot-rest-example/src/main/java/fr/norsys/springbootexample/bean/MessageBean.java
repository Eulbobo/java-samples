package fr.norsys.springbootexample.bean;

public class MessageBean {

    private final String value;

    public MessageBean(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
