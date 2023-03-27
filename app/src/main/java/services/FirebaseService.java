package services;

import com.google.android.gms.tasks.Task;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;
import java.util.HashMap;
import java.util.Map;

public class FirebaseService {
    private FirebaseFunctions func;

    public FirebaseService() {
        func = FirebaseFunctions.getInstance();
    }

    public Task<HttpsCallableResult> getFlights() {
       return func.getHttpsCallable("helloWorld").call();

    }
}
