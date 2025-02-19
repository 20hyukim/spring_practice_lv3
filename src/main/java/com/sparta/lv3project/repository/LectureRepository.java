package com.sparta.lv3project.repository;

import com.sparta.lv3project.entity.Instructor.Instructor;
import com.sparta.lv3project.entity.Lecture.Lecture;
import com.sparta.lv3project.entity.Lecture.LectureCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByUsername(String name);

    List<Lecture> findByCategory(LectureCategoryEnum category);

}
