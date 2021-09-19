package com.dynaccurate.microblog.resource;

import com.dynaccurate.microblog.dto.NewNewsDTO;
import com.dynaccurate.microblog.dto.NewTagDTO;
import com.dynaccurate.microblog.model.News;
import com.dynaccurate.microblog.model.Tag;
import com.dynaccurate.microblog.service.NewsService;
import com.dynaccurate.microblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsResource {

    @Autowired
    private NewsService newsService;

    @Autowired
    private TagService tagService;

    @GetMapping("/all")
    public List<News> listAll() {
        return newsService.ListAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> byId(@PathVariable Integer id) {
        News news = newsService.findById(id);
        return ResponseEntity.ok().body(news);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody NewNewsDTO newNewsDTO) {
        News news = newsService.fromDTO(newNewsDTO);
        List<Tag> tags = new ArrayList<>();
        for (NewTagDTO newTagDTO : newNewsDTO.getTags()) {
            tags.add(tagService.insert(newTagDTO));
        }
        news.setTags(tags);
        news = newsService.insert(news);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(news.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        newsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<News> update(@PathVariable Integer id, @RequestBody News news) {
        try {
            News newsSave = newsService.update(id, news);
            return ResponseEntity.ok(newsSave);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
