package com.example.Repository;

import com.example.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Comment_repository extends JpaRepository<Comment,Integer> {
}
