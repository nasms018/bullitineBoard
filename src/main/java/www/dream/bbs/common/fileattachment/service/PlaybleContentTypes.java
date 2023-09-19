package www.dream.bbs.common.fileattachment.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import org.jcodec.api.FrameGrab;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;
import www.dream.bbs.common.fileattachment.model.dto.AttachFileDTO;

public enum PlaybleContentTypes {
	image(true), video(true), audio(false), unknown(false);

	private boolean isThumbnailTarget;
	
	private PlaybleContentTypes(boolean isThumbnailTarget) {
		this.isThumbnailTarget = isThumbnailTarget;
	}
	
	private static PlaybleContentTypes getContentType(String contentType) {
		for (PlaybleContentTypes targetType : PlaybleContentTypes.values()) {
			if (contentType.startsWith(targetType.toString())) {
				return targetType;
			}
		}
		return unknown;
	}

	public static PlaybleContentTypes createThumbnail(InputStream is, File savedOnServerFile, String ext) {
		PlaybleContentTypes playbleContentType = null;
		String contentType;
		try {
			contentType = Files.probeContentType(savedOnServerFile.toPath());
			playbleContentType = getContentType(contentType);
			if (playbleContentType != null && playbleContentType.isThumbnailTarget) {
				

				String pureFileName =  savedOnServerFile.getName().substring(0, savedOnServerFile.getName().indexOf(ext));
				File thumdFile = new File(savedOnServerFile.getParent(),
						AttachFileDTO.buildThumnailFileName(pureFileName));

				FileOutputStream thumbnailOutputStream = new FileOutputStream(thumdFile);
				if (playbleContentType == image) {
					Thumbnailator.createThumbnail(is, thumbnailOutputStream, "png", 100, 100);
				} else if (playbleContentType == video) {
					Picture picture = FrameGrab.getFrameFromFile(savedOnServerFile, 0);
					BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					ImageIO.write(bufferedImage, "png", os);
					is = new ByteArrayInputStream(os.toByteArray());
					Thumbnailator.createThumbnail(is, thumbnailOutputStream, 100, 100);
				}
				thumbnailOutputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return playbleContentType;
	}
}
