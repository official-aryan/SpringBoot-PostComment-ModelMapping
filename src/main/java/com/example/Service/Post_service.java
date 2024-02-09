package com.example.Service;

import com.example.Entity.Post;
import com.example.ExceptionHandler.ResourceNotFoundException;
import com.example.Payload.Postdto;
import com.example.Repository.Post_repository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Post_service

{
    Post_repository prepo;
    ModelMapper modelMapper;

    public Post_service(Post_repository prepo, ModelMapper modelMapper) {
        this.prepo = prepo;
        this.modelMapper = modelMapper;
    }

    Post maptoentity(Postdto postdto)
    {
       Post post = modelMapper.map(postdto, Post.class);
       return post;
    }

    Postdto maptodto(Post post)
    {
        Postdto postdto=modelMapper.map(post,Postdto.class);
        return postdto;
    }


    public Postdto postdata(Postdto postdto)
    {
        Post post = maptoentity(postdto);
        Post save = prepo.save(post);
        Postdto dto = maptodto(save);
        return dto;
    }


    public List<Postdto> getdata() {

        List<Post> all = prepo.findAll();
        List<Postdto> dto = all.stream().map(r -> maptodto(r)).collect(Collectors.toList());
        return dto;
    }


    public Postdto databyid(int id) {

        Post post = prepo.findById(id).orElseThrow(

                () -> new ResourceNotFoundException("The data is not present in this id:" + id)
        );
        Postdto maptodto = maptodto(post);
        return maptodto;
    }

    public void deletedata(int id) {

        prepo.deleteById(id);
    }

    public Postdto updatedata(Postdto postdto, int id) {


        Post post = prepo.findById(id).get();

        post.setId(postdto.getId());
        post.setTitle(postdto.getTitle());
        post.setCaption(postdto.getCaption());

        Post save = prepo.save(post);
        Postdto dto = maptodto(save);
        return dto;
    }
}
