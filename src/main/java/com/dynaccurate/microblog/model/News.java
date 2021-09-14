package com.dynaccurate.microblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private Integer id;

    @NotNull
    private String title;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @NotNull
    private String content;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "news")
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "news_tag", joinColumns = @JoinColumn(name = "id_news")
            , inverseJoinColumns = @JoinColumn(name = "id_tag"))
    private List<Tag> tags;


    public News(String title, String content, User user) {
        super();
        this.title = title;
        this.content = content;
        this.user = user;
    }
}
