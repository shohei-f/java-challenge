package jp.co.axa.apidemo.constants;

import jp.co.axa.apidemo.entities.Employee;

/**
 * Constants used for API
 * @author shohei
 */
public class API {
    public static final String URL_EMPlOYEEAPI = "/api/v1";
    public static final String URL_EMPlOYEES   = "/employees";
    public static final String URL_EMPlOYEE_PARAM = "/{" + Employee.FN_ID + "}";
    public static final String URL_EMPlOYEE_ID = URL_EMPlOYEES + URL_EMPlOYEE_PARAM;
}
