package com.project.audioplayerproject.domain;

public class TestDomain {
    private long id = 0;
    private String text = null;

    public TestDomain() {
    }

    public TestDomain(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
