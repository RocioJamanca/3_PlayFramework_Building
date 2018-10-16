package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity

public class Usuari extends Model {//Para que sea una entidad de bases de datos
    public String email;
    public String password;
    public String fullname;
    public boolean isAdmin;

    @OneToMany (mappedBy="author",cascade = CascadeType.ALL) //Para saber de quien depende la relacion,
    // para en caso de que el usuario desaparezca, desaparezca con el mismo la relacion
    public List<Post> lposts= new ArrayList<Post>();

    public Usuari(String email, String password, String fullname){
        this.email=email;
        this.password=password;
        this.fullname=fullname;
    }



    /*public static Usuari connect(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }*/
}
