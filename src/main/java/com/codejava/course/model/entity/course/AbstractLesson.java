package com.codejava.course.model.entity.course;


public abstract class AbstractLesson implements LessonStrategy{
    @Override
    public Long getLessonId() {
        return null;
    }

    @Override
    public Long getTypeId() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }
}
