package cn.smbms.tools;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.core.convert.converter.Converter;

public class StringToDateConverter implements Converter<String, Date> {
	private String datePattern;
	public StringToDateConverter(String datePattern){
		this.datePattern = datePattern;
	}

	public StringToDateConverter() {
	}

	@Override
	public Date convert(String s) {
		Date date=null;
		try {
			date=new SimpleDateFormat(datePattern).parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}


