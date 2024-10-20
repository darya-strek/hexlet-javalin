package org.example.hexlet.dto.courses;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.example.hexlet.dto.BasePage;
import org.example.hexlet.model.Course;
import java.util.List;

@AllArgsConstructor
@Getter
public class CoursesPage extends BasePage {
    private List<Course> courses;
    private String header;
    private String term;
    private String description;

    public CoursesPage(List<Course> courses) {
        this.courses = courses;
    }
}
