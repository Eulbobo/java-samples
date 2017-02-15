package fr.norsys.web.bean;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Greeting {

    @NotNull
    @Digits(integer=10, fraction = 0)
    private Long id;

    @NotEmpty
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }
}
