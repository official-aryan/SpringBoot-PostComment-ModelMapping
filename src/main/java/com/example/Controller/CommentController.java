package com.example.Controller;

import com.example.Payload.Commentdto;
import com.example.Repository.Comment_repository;
import com.example.Repository.Post_repository;
import com.example.Service.Comment_service;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {


    Comment_service ser;

    public CommentController(Comment_service ser) {
        this.ser = ser;
    }

    @GetMapping
    public ResponseEntity<?> getall()
{
    List<Commentdto> all = ser.getAll();
    return new ResponseEntity<>(all, HttpStatus.OK);
    // http://localhost:8080/api/comment
}

@PostMapping
    public ResponseEntity<Commentdto> save(@RequestBody Commentdto commentdto)
{
    Commentdto savedata = ser.savedata(commentdto);
    return new ResponseEntity<>(savedata,HttpStatus.CREATED);
    // http://localhost:8080/api/comment
}
@DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id)
{
    ser.deletedata(id);

    return new ResponseEntity<>("data is deleted at id @ "+id,HttpStatus.OK);
}

@PutMapping("/{id}/post/{postId}")
    public ResponseEntity<Commentdto> update(@RequestBody Commentdto commentdto,@PathVariable int id,@PathVariable int postId)
{
    Commentdto commentdto1 = ser.updateData(commentdto, id, postId);
    return new ResponseEntity<>(commentdto1,HttpStatus.OK);
}



}
