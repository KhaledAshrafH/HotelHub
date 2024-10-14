package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
