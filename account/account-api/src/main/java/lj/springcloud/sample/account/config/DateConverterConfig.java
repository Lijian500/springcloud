package lj.springcloud.sample.account.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 */
@Configuration
public class DateConverterConfig {
	private static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

	private static final String DATE_YMD = "yyyy-MM-dd";

	private static final String TIME_HMS = "HH:mm:ss";

	@Bean
	public ObjectMapper getObjectMapper() {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
		// 反序列化失败
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// long 转换为字符串
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);

		// 浮点型使用字符串
		simpleModule.addSerializer(Double.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Double.TYPE, ToStringSerializer.instance);
		simpleModule.addSerializer(BigDecimal.class, ToStringSerializer.instance);

		// jdk8 时间格式化
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME)));
		javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_YMD)));
		javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(TIME_HMS)));

		//日期反序列化
		javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME)));
		javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_YMD)));
		javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(TIME_HMS)));


		objectMapper
				.registerModule(simpleModule)
				.registerModule(new ParameterNamesModule())
				.registerModule(new Jdk8Module())
				.registerModule(javaTimeModule);

		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		// 为mapper注册一个带有SerializerModifier的Factory，处理null值
		// objectMapper.setSerializerFactory(objectMapper.getSerializerFactory()
		//         .withSerializerModifier(new CustomizeBeanSerializerModifier()));


		return objectMapper;
	}

	@Bean
	public Converter<String, LocalDateTime> LocalDateTimeConvert() {
		return new Converter<String, LocalDateTime>() {
			@Override
			public LocalDateTime convert(String source) {

				DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime date = null;
				try {
					date = LocalDateTime.parse(source, df);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return date;
			}
		};
	}

	@Bean
	public Converter<String, LocalDate> LocalDateConvert() {
		return new Converter<String, LocalDate>() {
			@Override
			public LocalDate convert(String source) {

				DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = null;
				try {
					date = LocalDate.parse(source, df);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return date;
			}
		};
	}

	@Bean
	public Converter<String, LocalTime> LocalTimeConvert() {
		return new Converter<String, LocalTime>() {
			@Override
			public LocalTime convert(String source) {

				DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
				LocalTime date = null;
				try {
					date = LocalTime.parse(source, df);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return date;
			}
		};
	}
}
