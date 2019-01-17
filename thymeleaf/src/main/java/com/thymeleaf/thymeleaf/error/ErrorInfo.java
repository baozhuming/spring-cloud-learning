package com.thymeleaf.thymeleaf.error;

public class ErrorInfo {
    private String time;//发生时间
    private String url;//访问Url
    private String error;//错误类型
    String stackTrace;//错误的堆栈轨迹
    private int statusCode;//状态码
    private String reasonPhrase;//状态码解析

    private String ipAdrress;//IP地址

    public ErrorInfo() {
    }

    public ErrorInfo(String time, String url, String error, String stackTrace, int statusCode, String reasonPhrase, String ipAdrress) {
        this.time = time;
        this.url = url;
        this.error = error;
        this.stackTrace = stackTrace;
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
        this.ipAdrress = ipAdrress;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    public String getIpAdrress() {
        return ipAdrress;
    }

    public void setIpAdrress(String ipAdrress) {
        this.ipAdrress = ipAdrress;
    }
}
