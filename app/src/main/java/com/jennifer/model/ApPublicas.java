package com.jennifer.model;

/**
 * Created by Juan Rdz on 31/01/2016.
 */
public class ApPublicas {
    private String Id;
    private String Name;
    private String Description;
    private String FinalResult;
    private String DateTime;
    private String Award;

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setFinalResult(String FinalResult) {
        this.FinalResult = FinalResult;
    }

    public void setDateTime(String DateTime) {
        this.DateTime = DateTime;
    }

    public void setAward(String Award) {
        this.Award = Award;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getFinalResult() {
        return FinalResult;
    }

    public String getDateTime() {
        return DateTime;
    }

    public String getAward() {
        return Award;
    }
}
