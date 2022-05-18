package com.lsx.seckill.vo;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lsx.seckill.utils.ValidatorUtil;
import com.lsx.seckill.validator.IsMobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean required = false;


    @Override
    public void initialize(IsMobile constraintAnnotation){
        required = constraintAnnotation.requires();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(required){
            return ValidatorUtil.isMobile(value);
        }else{
            if(StringUtils.isEmpty(value)){
                return true;
            }else{
                return ValidatorUtil.isMobile(value);
            }
        }
    }
}
