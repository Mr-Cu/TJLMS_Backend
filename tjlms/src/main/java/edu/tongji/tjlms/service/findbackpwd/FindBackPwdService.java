package edu.tongji.tjlms.service.findbackpwd;

import edu.tongji.tjlms.dto.FindBackPwdDto;
import edu.tongji.tjlms.dto.SafePwdDto;

public interface FindBackPwdService {
    String resetPwd(FindBackPwdDto fbpd);
    String safePwd(SafePwdDto spd);
}
