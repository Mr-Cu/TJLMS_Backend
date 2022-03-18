package edu.tongji.tjlms.service.notice;

import edu.tongji.tjlms.dto.GetNoticeDto;
import edu.tongji.tjlms.dto.PostNoticeDto;
import edu.tongji.tjlms.model.NoticeEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.repository.NoticeRepository;
import edu.tongji.tjlms.repository.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NoticeServiceImpl implements NoticeService{

    @Resource
    NoticeRepository noticeRepository;

    @Resource
    TeacherRepository teacherRepository;
    @Override
    public List<GetNoticeDto> getAllTitles(Integer pageNum,Integer pageSize) {
        Page<NoticeEntity> list = noticeRepository.findAll(PageRequest.of(pageNum-1,pageSize));

        List<GetNoticeDto> ret = new ArrayList<>();
        for(NoticeEntity notice: list.getContent())
        {
            Optional<TeacherEntity> teacher = teacherRepository.findById(notice.getReleaser());

            ret.add(new GetNoticeDto(notice.getId(),notice.getTitle(),notice.getReleaseTime(),teacher.get().getName()));
        }
        return ret;
    }

    @Override
    public NoticeEntity getNoticeById(Integer id) {

        NoticeEntity notice =  noticeRepository.getById(id);
        if(notice.getTitle() == null)
        {
            return null;
        }
        return notice;
    }

    @Override
    public Map<String, Object> geyNoticeWithNameById(Integer id) {
        Map<String,Object> map = new HashMap<>();
        NoticeEntity notice =  noticeRepository.getById(id);
        if(notice.getTitle() == null)
        {
            return null;
        }
        map.put("notice",notice);
        map.put("name",teacherRepository.findById(notice.getReleaser()));
        return map;
    }

    @Override
    public List<NoticeEntity> getNoticeByReleaser(String teacherId) {
        return noticeRepository.findAllByReleaser(teacherId);
    }

    @Override
    public String postNotice(PostNoticeDto pnd) {
        NoticeEntity notice = new NoticeEntity();
        notice.setContent(pnd.getContent());
        notice.setReleaser(pnd.getReleaser());
        notice.setTitle(pnd.getTitle());
        notice.setReleaseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        noticeRepository.save(notice);
        return "通知发布成功";
    }

    @Override
    public String deleteNotice(Integer id) {
        noticeRepository.deleteById(id);
        return "删除成功";
    }
}
