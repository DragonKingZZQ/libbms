/**
 * jQuery Form Validator Module: platform
 * ------------------------------------------
 * Created by Zhang Zuli
 */
(function($) {

	/*
	 * Validate that user nick name
	 */
	$.formUtils.addValidator({
		name : 'nickname',
		validatorFunction : function(val, $el, config, language) {
			// Allow empty nick name.
			if ($.trim(val) === '') return true;
			var patternStart = '^([\u0391-\uFFE5a-zA-Z0-9', patternEnd = ']+)$', additionalChars = $el
					.attr('data-validation-allowing'), pattern = '';

			if (additionalChars) {
				pattern = patternStart + additionalChars + patternEnd;
				var extra = additionalChars.replace(/\\/g, '');
				if (extra.indexOf(' ') > -1) {
					extra = extra.replace(' ', '');
					extra += ' and spaces ';
				}
				this.errorMessage = language.badAlphaNumeric
						+ language.badAlphaNumericExtra + extra;
			} else {
				pattern = patternStart + patternEnd;
				this.errorMessage = language.badAlphaNumeric;
			}

			var lengthCheck = true;
			var lengthAllowed = $el.valAttr('length');
			if (lengthAllowed != undefined) {
				lengthCheck = $.formUtils.validators.validate_length.validatorFunction(val, $el, config, language);
			}
			return lengthCheck && new RegExp(pattern).test(val);
		},
		errorMessage : '',
		errorMessageKey: 'badCustomVal'
	});
	/*
	 * Validate that user real name
	 */
	$.formUtils.addValidator({
		name : 'realname',
		validatorFunction : function(val, $el, config, language) {
			// Allow empty user real name.
			if ($.trim(val) === '') return true;
			var patternStart = '^([\u0391-\uFFE5', patternEnd = ']+)$', additionalChars = $el
					.attr('data-validation-allowing'), pattern = '';

			if (additionalChars) {
				pattern = patternStart + additionalChars + patternEnd;
				var extra = additionalChars.replace(/\\/g, '');
				if (extra.indexOf(' ') > -1) {
					extra = extra.replace(' ', '');
					extra += ' and spaces ';
				}
				this.errorMessage = language.badAlphaNumeric
						+ language.badAlphaNumericExtra + extra;
			} else {
				pattern = patternStart + patternEnd;
				this.errorMessage = language.badAlphaNumeric;
			}

			var lengthCheck = true;
			var lengthAllowed = $el.valAttr('length');
			if (lengthAllowed != undefined) {
				lengthCheck = $.formUtils.validators.validate_length.validatorFunction(val, $el, config, language);
			}
			return lengthCheck && new RegExp(pattern).test(val);
		},
		errorMessage : '',
		errorMessageKey: 'badCustomVal'
	});
	/*
	 * Validate that mobile phone number
	 */
	$.formUtils.addValidator({
		name : 'phone',
		validatorFunction : function(val, $el, config, language) {
			// Allow empty value.
			if ($.trim(val) === '') return true;
			var patternStart = '^(1[3|4|5|8][0-9]{9}', patternEnd = ')$', pattern = '';
	
			pattern = patternStart + patternEnd;
			this.errorMessage = language.badAlphaNumeric;
	
			return new RegExp(pattern).test(val);
		},
		errorMessage : '',
		errorMessageKey: 'badCustomVal'
	});
	/*
	 * Validate that email, email can be empty
	 */
	$.formUtils.addValidator({
		name : 'email_z',
		validatorFunction : function(val, $el, config, language) {
			// Allow empty value.
			if ($.trim(val) === '') return true;
			var lengthCheck = true;
			var lengthAllowed = $el.valAttr('length');
			if (lengthAllowed != undefined) {
				lengthCheck = $.formUtils.validators.validate_length.validatorFunction(val, $el, config, language);
			}
			return lengthCheck && $.formUtils.validators.validate_email.validatorFunction(val);
		},
		errorMessage : '',
		errorMessageKey: 'badCustomVal'
	});
	/*
	 * Validate that number, number can be empty
	 */
	$.formUtils.addValidator({
		name : 'number_z',
		validatorFunction : function(val, $el, config, language) {
			// Allow empty value.
			if ($.trim(val) === '') return true;
			var lengthCheck = true;
			var lengthAllowed = $el.valAttr('length');
			if (lengthAllowed != undefined) {
				lengthCheck = $.formUtils.validators.validate_length.validatorFunction(val, $el, config, language);
			}
			return lengthCheck && $.formUtils.validators.validate_number.validatorFunction(val, $el, config);
		},
		errorMessage : '',
		errorMessageKey: 'badCustomVal'
	});
	/*
	 * Validate that date, date can be empty
	 */
	$.formUtils.addValidator({
		name : 'date_z',
		validatorFunction : function(val, $el, config, language) {
			// Allow empty value.
			if ($.trim(val) === '') return true;
			var lengthCheck = true;
			var lengthAllowed = $el.valAttr('length');
			if (lengthAllowed != undefined) {
				lengthCheck = $.formUtils.validators.validate_length.validatorFunction(val, $el, config, language);
			}
			return lengthCheck && $.formUtils.validators.validate_date.validatorFunction(val, $el, config);
		},
		errorMessage : '',
		errorMessageKey: 'badCustomVal'
	});
	
	/*
	 * Validate that number, number can be empty
	 */
	$.formUtils.addValidator({
		name : 'size',
		validatorFunction : function(val, $el, config, language) {
			var sizeCheck = $.formUtils.validators.validate_number.validatorFunction(val, $el, config);
			if(!sizeCheck) return false;
			var sizeAllowed = $el.valAttr('size');
			if (sizeAllowed != undefined) {
				var sizeCheckResults = $.formUtils.numericRangeCheck(val, sizeAllowed);
				switch(sizeCheckResults[0] )
	            {   // outside of allowed range
	                case "out":
	                	sizeCheck = false;
	                    break;
	                // too short
	                case "min":
	                	sizeCheck = false;
	                    break;
	                // too long
	                case "max":
	                	sizeCheck = false;
	                    break;
	                // ok
	                default:
	                	sizeCheck = true;
	            }
			} else {
                var elementType = $el.get(0).nodeName;
                alert('Please add attribute "data-validation-size" to '+elementType+' named '+$el.attr('name'));
                return true;
			}
			return sizeCheck;
		},
		errorMessage : '',
		errorMessageKey: 'badCustomVal'
	});
	
	/*
	 * Validate that text
	 */
	
	$.formUtils.addValidator({
		name : 'text',
		validatorFunction : function(val, $el, config, language) {
		// Not allow empty .
		if ($.trim(val) === '') return false;		
		var lengthCheck = true;
		var lengthAllowed = $el.valAttr('length');
		if (lengthAllowed != undefined) {
			lengthCheck = $.formUtils.validators.validate_length.validatorFunction(val, $el, config, language);
		}
		return lengthCheck ;
		},
		errorMessage : '',
		errorMessageKey: 'badCustomVal'
	});
		
	
	/*
	 * Validate that letter
	 */
	
	$.formUtils.addValidator({
		name : 'letter',
		validatorFunction : function(val, $el, config, language) {
		// Not allow empty .
		if ($.trim(val) === '') return false;		
		this.errorMessage = language.badAlphaNumeric;
		
		for(var i=0;i<val.length;i++){
			if(val.charCodeAt(i) > 128){
				return false
			}
		}

		return true; 
	},
	errorMessage : '',
	errorMessageKey: 'badCustomVal'
});
	

	/*
	 * Validate that sfz
	 */
	$.formUtils.addValidator({
		name : 'sfz',
		validatorFunction : function(val, $el, config, language) {
			// not allow empty value.
			if ($.trim(val) === '') return false;
			this.errorMessage = language.badAlphaNumeric;

			var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
			var error;
			var varArray = new Array();
			var intValue;
			var lngProduct = 0;
			var intCheckDigit;
			var intStrLen = val.length;
			var idNumber = val;
			// initialize
			if ((intStrLen != 15) && (intStrLen != 18)) {
				// error = "输入身份证号码长度不对！";
				// alert(error);
				// frmAddUser.txtIDCard.focus();
				return false;
			}
			// check and set value
			for (i = 0; i < intStrLen; i++) {
				varArray[i] = idNumber.charAt(i);
				if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
					// error = "错误的身份证号码！.";
					// alert(error);
					// frmAddUser.txtIDCard.focus();
					return false;
				} else if (i < 17) {
					varArray[i] = varArray[i] * factorArr[i];
				}
			}
			if (intStrLen == 18) {
				// check date
				var date8 = idNumber.substring(6, 14);
				// calculate the sum of the products
				for (i = 0; i < 17; i++) {
					lngProduct = lngProduct + varArray[i];
				}
				// calculate the check digit
				intCheckDigit = 12 - lngProduct % 11;
				switch (intCheckDigit) {
				case 10:
					intCheckDigit = 'X';
					break;
				case 11:
					intCheckDigit = 0;
					break;
				case 12:
					intCheckDigit = 1;
					break;
				}
				// check last digit
				if (varArray[17].toUpperCase() != intCheckDigit) {
					// error = "身份证效验位错误!...正确为： " + intCheckDigit + ".";
					// alert(error);
					return false;
				}
			} else { // length is 15
				// check date
				var date6 = idNumber.substring(6, 12);
			}
			return true; 
		},
		errorMessage : '',
		errorMessageKey: 'badCustomVal'
	});
})(jQuery);