package com.jonathantownley.bugger.model;

public class Stage {

    private Long id;
    private String stage;

    public Stage(Long id, String stage) {
        this.id = id;
        this.stage = stage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
