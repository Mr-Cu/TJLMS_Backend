package edu.tongji.tjlms.service.email;

import edu.tongji.tjlms.dto.EmailDto;

/**
 * @author Charles Gao
 * @date 2021/10/20
 * @description the email sending service interface
 */
public interface EmailSendService {
    String sendEmail(EmailDto ed);
}
