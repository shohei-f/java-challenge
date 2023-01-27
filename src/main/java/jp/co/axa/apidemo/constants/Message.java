package jp.co.axa.apidemo.constants;

import jp.co.axa.apidemo.entities.Employee;

/**
 * Constants used for messages
 * @author shohei
 */
public class Message {
    public static final String EMPLOYEE_NOT_FOUND = Employee.FN_ID + ": %s not found";
    public static final String INFO_EXEC = "Execute {}";
    public static final String INFO_EXEC_PRM = "Execute {} params={}";
    public static final String INFO_EXEC_BODY = "Execute {} body={}";
    public static final String INFO_EXEC_PRM_BODY = "Execute {} params={} body={}";
    public static final String INFO_RESULT = "RESULT={}";
    public static final String INFO_SAVEDSUCCESS = "Employee Saved Successfully";
    public static final String INFO_DELETEDSUCCESS = "Employee Deleted Successfully";
}
