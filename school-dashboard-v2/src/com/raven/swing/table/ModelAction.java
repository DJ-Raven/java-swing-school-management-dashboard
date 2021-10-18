package com.raven.swing.table;

import com.raven.model.ModelStudent;

public class ModelAction {

    public ModelStudent getStudent() {
        return student;
    }

    public void setStudent(ModelStudent student) {
        this.student = student;
    }

    public EventAction getEvent() {
        return event;
    }

    public void setEvent(EventAction event) {
        this.event = event;
    }

    public ModelAction(ModelStudent student, EventAction event) {
        this.student = student;
        this.event = event;
    }

    public ModelAction() {
    }

    private ModelStudent student;
    private EventAction event;
}
