package kr.or.nextit.notice.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.nextit.attach.service.AttachVo;
import kr.or.nextit.attach.service.impl.AttachMapper;
import kr.or.nextit.notice.service.NoticeSearchVo;
import kr.or.nextit.notice.service.NoticeService;
import kr.or.nextit.notice.service.NoticeVo;

@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "propertyService")
	private Properties propertiesService;

	@Autowired
	private NoticeMapper noticeMapper;

	@Autowired
	private AttachMapper attachMapper;

	@Override
	public List<NoticeVo> selectNoticeList(NoticeSearchVo noticeSearchVo) throws Exception {
		return noticeMapper.selectNoticeList(noticeSearchVo);
	}

	@Override
	public int selectTotalCount(NoticeSearchVo params) throws Exception {
		return noticeMapper.selectTotalCount(params);
	}

	@Override
	public void insertItem(NoticeVo noticeVo, List<Part> attachPart) throws Exception {

		List<AttachVo> result = new ArrayList<>();

		try {

			// TODO : 그룹키를 데이터베이스에서 조회해서 만드러 줄 예정 ? 날짜
			//String fileGroup = String.format("notice_%s", 10000);
			String fileGroup = UUID.randomUUID().toString();
			noticeVo.setFileGroup(fileGroup);

			for (Part part : attachPart) {
				
				// ;파일을 넘겨주지 않았을 경우
				if(part.getSubmittedFileName().isEmpty()) {
					continue;
				}

				AttachVo item = new AttachVo();

				String fileId = UUID.randomUUID().toString();

				item.setFileGroup(fileGroup);
				item.setFileId(fileId);

				item.setFileType(part.getContentType());
				item.setFileOriginalFileName(part.getSubmittedFileName());
				item.setFileSize(part.getSize());

				item.setFileSavePath("notice");
				item.setFileSaveFileName(fileId);
				item.setFileFlag("Y");
				item.setFileRegUser("test");

				result.add(item);
				log.debug("insertItems() {}", item);

				String fileUploadPath = propertiesService.getProperty("file.upload.path");

				StringBuilder savePath = new StringBuilder();
				savePath.append(fileUploadPath);
				savePath.append(File.separator);
				savePath.append(item.getFileSavePath());
				savePath.append(File.separator);
				savePath.append(item.getFileSaveFileName());

				File file = new File(savePath.toString());
				FileUtils.copyToFile(part.getInputStream(), file);

				attachMapper.insertItem(item);

				// ;(삭제시)
				// file.delete();

			}

			noticeMapper.insertItem(noticeVo);

		} catch (Exception e) {
			throw new Exception(String.format(">>> 데이터베이스 에러 : %s\n", e.getMessage()));
		}
	}

	@Override
	public NoticeVo selectItemView(NoticeVo noticeVo) throws Exception {

		noticeMapper.updateItemViewCount(noticeVo);

		return noticeMapper.selectItemView(noticeVo);
	}
	
	@Override
	public void updateCountTotal() throws Exception {
		noticeMapper.updateCountTotal();
	}
}
