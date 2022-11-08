package bagit.com.Controllers;

import bagit.com.entities.Admin;
import bagit.com.services.serviceImplementation.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegistrationController {

    private UserDetailService userDetailService;

    @Autowired
    RegistrationController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean> singUp(@RequestBody Admin credentials) {


        boolean result =  userDetailService.registerAdmin(credentials);

        return new ResponseEntity<>(result, result == true ? HttpStatus.OK : HttpStatus.BAD_REQUEST);

    }


}
