package www.dream.bbs.framework.util;

import java.io.UnsupportedEncodingException;

import org.apache.cxf.attachment.Rfc5987Util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class JsonUtil {
	public static String getJsonRepresentation(Object obj) throws Exception {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//피카추[!@#$%^&(){};',.].gif
		return Rfc5987Util.encode(gson.toJson(obj), "UTF-8");
	}

	public static Object deserialize(String jsonStr, Class cls) throws Exception {
		Gson gson = new Gson();
		try {
			return gson.fromJson(Rfc5987Util.decode(jsonStr, "UTF-8"), cls);
		} catch (JsonSyntaxException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return cls.newInstance();
	}

}
