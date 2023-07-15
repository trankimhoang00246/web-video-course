package com.codejava.course.model.form.lesson;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class LessonVideoForm extends Base {
    private String multipartFile;
}
