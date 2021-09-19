package com.dynaccurate.microblog.repository;

import com.dynaccurate.microblog.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Integer> {
}
