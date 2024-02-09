package com.example.Repository;

import com.example.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Post_repository extends JpaRepository<Post,Integer> {
}
