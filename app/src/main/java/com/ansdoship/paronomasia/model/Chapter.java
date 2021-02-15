package com.ansdoship.paronomasia.model;

public class Chapter {
    private String name;
    private String title;
    private String story;
    private Choices choices;

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getStory() {
        return story;
    }

    public Choices getChoices() {
        return choices;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public void setChoices(Choices choices) {
        this.choices = choices;
    }
}

