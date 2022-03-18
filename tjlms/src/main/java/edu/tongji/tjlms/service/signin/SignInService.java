package edu.tongji.tjlms.service.signin;

import edu.tongji.tjlms.dto.SignInDto;

/**
 * @author Charles Gao
 * @date 2021/10/20
 * @description the sign in service implementation
 */
public interface SignInService {
    String signIn(SignInDto sid);
}
