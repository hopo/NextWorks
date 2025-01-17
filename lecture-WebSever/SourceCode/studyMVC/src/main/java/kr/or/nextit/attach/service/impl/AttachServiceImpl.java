package kr.or.nextit.attach.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.multipart.MultipartFile;

import kr.or.nextit.attach.service.AttachService;
import kr.or.nextit.attach.service.AttachVo;

@Service("AttachService")
public class AttachServiceImpl implements AttachService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "propertyService")
	private Properties propertiesService;

	@Autowired
	private AttachMapper attachMapper;

	@Override
	public List<AttachVo> insertItemsPart(List<Part> params, String memId) throws Exception {

		List<AttachVo> result = new ArrayList<>();

		String fileGroup = UUID.randomUUID().toString();

		for (Part part : params) {

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
			item.setFileRegUser(memId);

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

		return result;
	}

	@Override
	public List<AttachVo> insertItemsMulitpart(List<MultipartFile> params, String memId) throws Exception {

		List<AttachVo> result = new ArrayList<>();

		String fileGroup = UUID.randomUUID().toString();

		for (MultipartFile part : params) {
			AttachVo item = new AttachVo();

			String fileId = UUID.randomUUID().toString();

			item.setFileGroup(fileGroup);
			item.setFileId(fileId);

			item.setFileType(part.getContentType());
			item.setFileOriginalFileName(part.getOriginalFilename());
			item.setFileSize(part.getSize());

			item.setFileSavePath("notice");
			item.setFileSaveFileName(fileId);
			item.setFileFlag("Y");
			item.setFileRegUser(memId);

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

			// file.delete();

		}

		return result;
	}

	@Override
	public List<AttachVo> selectItems(AttachVo attachVo) throws Exception {
		return attachMapper.selectItems(attachVo);
	}

	@Override
	public AttachVo selectDownloadItem(HashMap<String, Object> params) throws Exception {
		return attachMapper.selectDownloadItem(params);
	}

}
