package ozturk.assignment.assignment.model;

import jakarta.persistence.*;

public class Message {
    private Integer id;

    private String body;

    public Message() {

    }

    public Message(Integer id, String body) {

        this.id = id;
        this.body = body;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return id + " - " + body;
    }
}
