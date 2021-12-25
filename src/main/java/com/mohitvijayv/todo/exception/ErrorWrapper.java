package com.mohitvijayv.todo.exception;

import java.util.Date;
import java.util.Map;

public class ErrorWrapper {
    private String code;
    private String partnerCode;



    private Date date = new Date();
    private InnerError innerError;

    public Date getDate() {
        return date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }


    public InnerError getInnerError() {
        return innerError;
    }

    public void setInnerError(InnerError innerError) {
        this.innerError = innerError;
    }

    public ErrorWrapper(String code, String partnerCode, InnerError innerError) {
        this.code = code;
        this.partnerCode = partnerCode;
        this.innerError = innerError;
    }

}
