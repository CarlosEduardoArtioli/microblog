package com.dynaccurate.microblog.resource;

import com.dynaccurate.microblog.dto.NewCommentDTO;
import com.dynaccurate.microblog.model.Comment;
import com.dynaccurate.microblog.service.CommentService;
import com.dynaccurate.microblog.service.NewsService;
import com.dynaccurate.microblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/comment")
public class CommentResource {

    @Autowired
    private NewsService newsService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Comment> byId(@PathVariable Integer id) {
        Comment comment = commentService.findById(id);
        return ResponseEntity.ok().body(comment);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody NewCommentDTO newCommentDTO) {
        Comment comment = commentService.fromDTO(newCommentDTO);
        comment.setUser(userService.findById(newCommentDTO.getIdUser()));
        comment.setNews(newsService.findById(newCommentDTO.getIdNews()));
        comment = commentService.insert(comment);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comment.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(@PathVariable Integer id, @RequestBody Comment comment) {
        try {
            Comment commentSave = commentService.update(id, comment);
            return ResponseEntity.ok(commentSave);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
