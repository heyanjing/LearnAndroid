package android.he.com.okgodemo.bean;

import java.util.Map;

public class Result extends BaseBean {

    private static final long  serialVersionUID = 1L;

    public boolean             success;                                                                             // 成功标志
    public Integer             code;                                                                                      // 返回标示
    public String              msg;                                                                                         // 相关消息
    public Object              data;                                                                                      // 相关数据
    public Map<String, Object> errors;                                                                                // 错误详细

    public Result() {
        super();
    }

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, Integer code, Object data, String msg) {
        this(success);
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Result(Integer code, Map<String, Object> errors, String msg) {
        this.success = false;
        this.code = code;
        this.errors = errors;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public boolean hasErrors() {
        if (this.errors != null && this.errors.size() > 0) {
            return true;
        }
        return false;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, Object> errors) {
        this.errors = errors;
    }
}
