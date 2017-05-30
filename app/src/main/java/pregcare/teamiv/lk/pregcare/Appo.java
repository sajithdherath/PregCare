package pregcare.teamiv.lk.pregcare;

/**
 * Created by User on 4/9/2017.
 */

public class Appo {
    public static int id;
    public static String title;
    public static String time;
    public static String details;
    public static String day;

    public Appo(){

    }

    public Appo (String day){
        Appo.day =day;
    }

    public Appo (int id,String title,String time,String details){
        Appo.id=id;
        Appo.title=title;
        Appo.time=time;
        Appo.details=details;
    }
    public Appo (int id, String day){
        Appo.id =id;
        Appo.day=day;
    }

    public Appo (int id){
        Appo.id =id;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        Appo.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        Appo.title = title;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time) {
        Appo.time = time;
    }

    public String getDetails(){
        return details;
    }

    public void setDetails(String details) {
        Appo.details = details;
    }

    public String getDay(){
        return day;
    }

    public void setDay(String day) {
        Appo.day = day;
    }





}
