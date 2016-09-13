package fr.norsys.springbootexample.bean;

public class Message {

    private final String message;

    private Message(final String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static Message build(final String message){
        return new Message(message);
    }

}
