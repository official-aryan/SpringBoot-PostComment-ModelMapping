package com.example.Service;

import com.example.Entity.Comment;
import com.example.Entity.Post;
import com.example.ExceptionHandler.ResourceNotFoundException;
import com.example.Payload.Commentdto;
import com.example.Payload.Postdto;
import com.example.Repository.Comment_repository;
import com.example.Repository.Post_repository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Comment_service {

    Post_repository prepo;
    Comment_repository crepo;
    ModelMapper modelMapper;

    public Comment_service(Post_repository prepo, Comment_repository crepo, ModelMapper modelMapper) {
        this.prepo = prepo;
        this.crepo = crepo;
        this.modelMapper = modelMapper;
    }

    public Comment maptoentity(Commentdto commentdto)
    {
        Comment comment = modelMapper.map(commentdto, Comment.class);
        return comment;
    }

     Commentdto maptodto(Comment comment)
    {
        Commentdto dto = modelMapper.map(comment, Commentdto.class);
        return dto;
    }

     public List<Commentdto> getAll() {

        List<Comment> all = crepo.findAll();
         List<Commentdto> collect = all.stream().map(r -> maptodto(r)).collect(Collectors.toList());
        return collect;
    }

    public Commentdto savedata(Commentdto commentdto) {

        Comment maptoentity = maptoentity(commentdto);
        Comment save = crepo.save(maptoentity);
        Commentdto dto = maptodto(save);
        return dto;
    }

    public void deletedata(int id) {

        crepo.deleteById(id);
    }

    public Commentdto updateData(Commentdto commentdto, int id, int postId) {

        Post post = prepo.findById(postId).orElseThrow(

                () -> new ResourceNotFoundException("this post is not present")
        );

        Comment comment1 = crepo.findById(id).orElseThrow(

                () -> new ResourceNotFoundException("this comment is not present")
        );

        Comment comment = maptoentity(commentdto);
        comment.setId(comment.getId());// why use  setId again as we have allready done it in Modelmapping because if we dont get it again as ID is auto genearte it will set a new comment instead of updating it will create a new data.
        comment.setPosts(post);
        Comment save = crepo.save(comment);
        Commentdto dto = maptodto(save);

        return dto;

    }
}
