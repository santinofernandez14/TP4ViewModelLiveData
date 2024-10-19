package com.mysoft.tp4viewmodellivedata;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel {
    private final MutableLiveData<List<Usuario>> userListLiveData = new MutableLiveData<>();

    public UserViewModel() {

        userListLiveData.setValue(new ArrayList<>());
    }


    public LiveData<List<Usuario>> getUserList() {
        return userListLiveData;
    }


    public void addUser(Usuario usuario) {
        List<Usuario> currentList = userListLiveData.getValue();
        if (currentList != null) {
            currentList.add(usuario);
            userListLiveData.setValue(currentList);
        }
    }
}

