package com.jocata.api.impl;

import com.jocata.api.UserAPIService;
import com.jocata.datamodel.user.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserAPIServiceImpl implements UserAPIService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserForm login(String username, String password) {
        String url = "http://localhost:8080/user-service/api/users/login?username="+username+"&password="+password;
        return restTemplate.getForObject(url, UserForm.class);
    }

    @Override
    public UserForm getUser(String id) {
        String url = "http://localhost:8080/user-service/api/users/"+id;
        return restTemplate.getForObject(url,UserForm.class);
    }
}
