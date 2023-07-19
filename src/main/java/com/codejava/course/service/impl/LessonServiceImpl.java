package com.codejava.course.service.impl;

import com.codejava.course.model.dto.LessonDto;
import com.codejava.course.model.entity.course.Course;
import com.codejava.course.model.entity.course.Lesson;
import com.codejava.course.model.entity.CategoryBase;
import com.codejava.course.model.entity.course.lesson.Text;
import com.codejava.course.model.entity.course.lesson.Video;
import com.codejava.course.model.form.lesson.Base;
import com.codejava.course.model.form.lesson.LessonTextForm;
import com.codejava.course.model.form.lesson.LessonVideoForm;
import com.codejava.course.repository.*;
import com.codejava.course.service.LessonService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public record LessonServiceImpl(
        LessonRepository lessonRepository,
        CategoryBaseRepository categoryBaseRepository,
        TextRepository textRepository,
        VideoRepository videoRepository,
        CourseRepository courseRepository) implements LessonService {

    //get all lesson
    @Override
    public List<LessonDto> getAll() {
        return lessonRepository.findAll()
                .stream()
                .map(lesson -> Lesson.toDto(lesson))
                .collect(Collectors.toList());
    }

    //get lesson by course id
    @Override
    public List<LessonDto> getAllByCourseId(Long courseId) {
        return lessonRepository.findByCourse_Id(courseId)
                .stream()
                .map((lesson -> Lesson.toDto(lesson)))
                .collect(Collectors.toList());
    }

    /*TODO:
        - Người dùng chọn vào khóa học
        - Click edit để thêm các lesson -> hiển thị màn hình nhập lesson
        - Nhập Title Bài học
        - Nhập số (lesson1, lesson2,...) của bài học
        - Chọn kiểu của bài học (Text/video)
        - Sau đó nhập Form
     */
    @Override
    public String save(Base lessonForm) {
        if(lessonForm instanceof LessonVideoForm) {
            Long id = saveVideoLesson((LessonVideoForm) lessonForm);
            return saveLesson(lessonForm, id);
        } else if (lessonForm instanceof LessonTextForm) {
            Long id = saveTextLesson((LessonTextForm) lessonForm);
            return saveLesson(lessonForm, id);
        } else {
            throw new IllegalArgumentException("Lesson valid!");
        }
    }

    private String saveLesson(Base lessonForm, Long id) {

        Course course = courseRepository.findById(lessonForm.getCourseId())
                .orElseThrow(() -> new NotFoundException("Course not found with id " + lessonForm.getCourseId()));

        var lesson = Lesson.builder()
                .title(lessonForm.getTitle())
                .number(lessonForm.getNumber())
                .lessonId(id)
                .typeId(lessonForm.getTypeId())
                .course(course)
                .build();
        lessonRepository.save(lesson);
        return "Successfully save lesson!";
    }

    private Long saveTextLesson(LessonTextForm lessonForm) {
        CategoryBase categoryBase = categoryBaseRepository.findById(lessonForm.getTypeId())
                .orElseThrow(() -> new NotFoundException("Type lesson not found with id " + lessonForm.getTypeId()));

        var text = Text.builder()
                .title(lessonForm.getTitle())
                .content(lessonForm.getContent())
                .categoryBase(categoryBase)
                .build();
        Text text1 = textRepository.save(text);

        return text1.getId();
    }


    private Long saveVideoLesson(LessonVideoForm lessonForm) {
        CategoryBase categoryBase = categoryBaseRepository.findById(lessonForm.getTypeId())
                .orElseThrow(() -> new NotFoundException("Type lesson not found with id " + lessonForm.getTypeId()));

        var video = Video.builder()
                .title(lessonForm.getTitle())
                .url("http://link.com")
                .duration(30)
                .categoryBase(categoryBase) //seconds
                .build();
        Video video1 = videoRepository.save(video);

        return video1.getId();
    }
}
