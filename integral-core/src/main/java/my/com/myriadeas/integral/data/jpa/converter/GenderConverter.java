package my.com.myriadeas.integral.data.jpa.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import my.com.myriadeas.integral.data.jpa.domain.Gender;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Gender gender) {
		return gender.getCode();
	}

	@Override
	public Gender convertToEntityAttribute(Integer value) {
		for (Gender gender : Gender.values()) {
			if (gender.getCode() == value) {
				return gender;
			}
		}
		return Gender.NOT_KNOWN;
	}

}
