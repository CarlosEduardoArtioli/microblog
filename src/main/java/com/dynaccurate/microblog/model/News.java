package com.dynaccurate.microblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @NotNull
    private String content;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "news")
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "news_tag", joinColumns = @JoinColumn(name = "id_news")
            , inverseJoinColumns = @JoinColumn(name = "id_tags"))
    private List<Tag> tags = new ArrayList<>();


}
