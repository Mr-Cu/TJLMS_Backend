package edu.tongji.tjlms.service.login;

import edu.tongji.tjlms.dto.LoginDto;
/**
 * @author Charles Gao
 * @date 2021/10/20
 * @description the login service interface
 */
public interface LoginService {
    Object login(LoginDto ld);
}
