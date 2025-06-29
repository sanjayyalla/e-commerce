package com.jocata.service;

import com.jocata.datamodel.user.form.UserForm;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserAPIService {

    UserForm login(String username, String password);

}
