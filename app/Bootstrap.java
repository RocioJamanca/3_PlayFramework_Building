import models.Usuari;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart //tell Play that we want to run this job synchronously at application start-up
public class Bootstrap extends Job {

    public void doJob(){
        //Check if the database is empty
        if(Usuari.count()==0){
            Fixtures.loadModels("initial-data.yml");
        }
    }
}
