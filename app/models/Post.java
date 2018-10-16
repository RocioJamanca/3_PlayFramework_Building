package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity

//We have declared the relation to the User class using @ManyToOne.
// That means that each Post is authored by a single User, and that
// each User can author several Post instances.

public class Post extends Model {
    public String title;
    public Date postedAt;
    @Lob //Here we use the @Lob annotation to tell JPA to use a large text database type to store the post content
    public String content;

    @ManyToOne //relación muchos post 1 autor
    public Usuari author;



    @OneToMany(mappedBy="post", cascade= CascadeType.ALL)
    public List<Comment> comments;

    public Post(Usuari author, String title, String content){
        this.author=author;
        this.title=title;
        this.content = content;
        this.postedAt=new Date();

    }
    public Post addComment(String author, String content) {
        Comment newComment = new Comment(this, author, content).save();
        this.comments.add(newComment);
        this.save();
        return this;
    }

}
