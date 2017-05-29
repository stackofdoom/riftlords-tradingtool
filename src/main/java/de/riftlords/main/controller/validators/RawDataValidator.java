/**
 * 
 */
package de.riftlords.main.controller.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import de.riftlords.main.persistence.entity.RawData;



/**
 * @author pasc2de
 *
 */
public class RawDataValidator implements Validator {
	
	private static String VALIDATION_PATTERN = "^(?:.*\\n)*?(\\s*\\W\\s*(\\w*\\s*\\d*).*\\n)+.*$";

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return RawData.class.equals(clazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "valid.text");
		RawData rawdata = (RawData) target;
		if(!rawdata.getText().matches(VALIDATION_PATTERN)) {
			errors.rejectValue("text","valid.text");
		}
	}

}
