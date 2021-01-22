package cd.amateurmobiledev.ecodim;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("takeContacts.php")
    Call<List<Contact>> getContacts();
}
