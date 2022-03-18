package edu.tongji.tjlms.service.preverify;

import edu.tongji.tjlms.dto.PreVerifyDto;
import edu.tongji.tjlms.dto.PreVerifyEmailDto;
import edu.tongji.tjlms.dto.PreVerifyExistsDto;

public interface PreVerifyService {
    boolean exists(PreVerifyDto pvd);
    boolean exists(PreVerifyExistsDto pved);
    boolean emailCorrect(PreVerifyEmailDto pved);
}
