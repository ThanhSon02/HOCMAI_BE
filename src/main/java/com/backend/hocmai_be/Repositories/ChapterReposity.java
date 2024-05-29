package com.backend.hocmai_be.Repositories;

import com.backend.hocmai_be.Model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterReposity extends JpaRepository<Chapter, Integer> {
}
