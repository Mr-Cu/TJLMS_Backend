package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.SignInDto;
import edu.tongji.tjlms.service.signin.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Charles Gao
 * @date 2021/10/5
 * @description the APIs for email and signing in
 */
@RestController
@RequestMapping("/api")
@ResponseBody
public class SignInController {
    @Autowired
    private SignInService signInService;
    /**
     * sign in
     * @param sid the data for signing in
     * @return response code with information
     */
    @PostMapping("/post/sign/in")
    public ResponseEntity<String> signIn(@RequestBody SignInDto sid)
    {
        try
        {
            String ret = signInService.signIn(sid);
            if(ret.equals("注册成功"))
            {
                return ResponseEntity.status(HttpStatus.OK).body(ret);
            }
            else
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }

}

