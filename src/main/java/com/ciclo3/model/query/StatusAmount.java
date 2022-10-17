package com.ciclo3.model.query;

public class StatusAmount {
    private Integer completed;
    private Integer cancelled;


    public StatusAmount(Integer cancelled, Integer completed) {
        this.cancelled = cancelled;
        this.completed = completed;
    }

    public Integer getCancelled() {
        return cancelled;
    }

    public void setCancelled(Integer cancelled) {
        this.cancelled = cancelled;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }
}
