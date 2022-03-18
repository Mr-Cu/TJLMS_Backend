package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.FindBackPwdDto;
import edu.tongji.tjlms.dto.SafePwdDto;
import edu.tongji.tjlms.service.findbackpwd.FindBackPwdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@ResponseBody
public class FindBackPwdController {
    @Autowired
    FindBackPwdService findBackPwdService;

    @PostMapping("/find/pwd")
    public ResponseEntity<String> findBackPwd(@RequestBody FindBackPwdDto fbpd)
    {
        try
        {
            String ret = findBackPwdService.resetPwd(fbpd);
            switch (ret)
            {
                case "验证码错误":
                case "错误用户类型":
                {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
                }
                case "未找到用户信息":
                {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ret);

                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(ret);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/safe/pwd")
    public ResponseEntity<String> safePwd(@RequestBody SafePwdDto spd)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(findBackPwdService.safePwd(spd));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }
}
