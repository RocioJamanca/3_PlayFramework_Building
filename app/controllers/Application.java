package controllers;

import play.Play;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {
    @Before
    static void addDefaults() {
        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
    }

    public static void index() {

        Post frontPost=Post.find("order by postedAt desc").first();
        List<Post> olderPosts=Post.find("order by postedAt").from(1).fetch();
        render(frontPost,olderPosts);


    }

    public static void InitDB()
    {
        Usuari u =new Usuari("123@gmail.com","1234","Albert Miquel").save();
        Post p =new Post(u,"b","c").save();

        u.lposts.add(p); //Ahora añadimos al usuario las lista de posts
        u.save();

        //Formas de formular una consulta  lista post usuari fullname=lola
        Usuari f =Usuari.find("byFullname","Albert Miquel").first();
        Post p2 =new Post(f,"HOLA2","n").save();
        f.lposts.add(p2);
        f.save();


        Usuari g=Usuari.find("byFullname","Albert Miquel").first();
        List<Post> lpost= Post.find("byAuthor",u).fetch();
        //g.save();
    }

    public static void addUser(){
        Usuari k =new Usuari("rocio@gmail.com","1234","Rocio").save();
        Post t=new Post(k,"Blues","Comentari").save();

        k.lposts.add(t);//Añadir el post a la lista de posts
        k.save();

        renderText("Post "+k.lposts.get(0).content);
    }

    public  static void deleteUser(){
        Usuari k= Usuari.find("byFullname","Rocio").first();
        k.delete();

    }

    public void postComments() {
        // Create a new user and save it
        Usuari bob = new Usuari("bob@gmail.com", "secret", "Bob").save();

        // Create a new post
        Post bobPost = new Post(bob, "My first post", "Hello world").save();

        // Post a first comment
        new Comment(bobPost, "Jeff", "Nice post").save();
        new Comment(bobPost, "Tom", "I knew that !").save();

        // Retrieve all comments
        List<Comment> bobPostComments = Comment.find("byPost", bobPost).fetch();

    }



}