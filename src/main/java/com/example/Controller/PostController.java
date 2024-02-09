package com.example.Controller;

import com.example.Payload.Postdto;
import com.example.Service.Post_service;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    Post_service ser;

    public PostController(Post_service ser) {
        this.ser = ser;
    }

    @PostMapping
    public ResponseEntity<Postdto> createpost(@RequestBody Postdto postdto)
    {
        Postdto postdata = ser.postdata(postdto);

        return new ResponseEntity<>(postdata, HttpStatus.CREATED);
        // http://localhost:8080/api/post
    }

    @GetMapping
    public ResponseEntity<?> getpost()
    {
        List<Postdto> getdata = ser.getdata();
        return new ResponseEntity<>(getdata,HttpStatus.OK);
        // http://localhost:8080/api/post
    }

    @GetMapping("/by_ID")
    public ResponseEntity<?> getdatabyid(@RequestParam("id") int id)
    {
        Postdto databyid = ser.databyid(id);
        return new ResponseEntity<>(databyid,HttpStatus.OK);
        // http://localhost:8080/api/post/by_ID?id=2
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id)
    {
        ser.deletedata(id);
        return new ResponseEntity<>("data is deleted" + id,HttpStatus.OK);
        // http://localhost:8080/api/post/3
    }

    @PutMapping
    public ResponseEntity<Postdto> update(@RequestParam("id") int id, @RequestBody Postdto postdto)
    {
        Postdto updatedata = ser.updatedata(postdto, id);
        return new ResponseEntity<>(updatedata,HttpStatus.OK);
        // http://localhost:8080/api/post?id=3
    }

}
