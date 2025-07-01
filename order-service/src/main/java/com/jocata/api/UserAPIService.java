package com.jocata.api;

import com.jocata.datamodel.user.form.UserForm;

public interface UserAPIService {

    UserForm login(String username, String password);

    UserForm getUser(String id);
}
