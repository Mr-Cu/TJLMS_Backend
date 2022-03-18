package edu.tongji.tjlms.service.perm;

public interface PermService {
    boolean canGrade(String teacherId);
    boolean canRelease(String teacherId);
    boolean earlierThanDdl(Integer labId);
    boolean isResp(String teacherId);
}
