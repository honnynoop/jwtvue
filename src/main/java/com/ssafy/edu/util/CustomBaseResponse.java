package com.ssafy.edu.util;

public class CustomBaseResponse<T> {
    private int errorCode;              // 문제 발생 시 반환될 Code (문제 없을 시 0)
    private String title;               // 응답 제목
    private String message;             // 응답 내용
    
    private T data;                     // 응답과 함께 전달될 객체

    
    public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public CustomBaseResponse() {
        this.errorCode = 0;
    }

    public boolean isSuccess() {
        return this.errorCode == 0;
    }

    public static CustomBaseResponse<Void> ok() {
        return ok(null);
    }

    public static <T> CustomBaseResponse<T> ok(T data) {
        CustomBaseResponse<T> response = new CustomBaseResponse<>();
        response.setData(data);
        response.setTitle("");
        response.setMessage("");
        return response;
    } 

    public static CustomBaseResponse<Void> error(int errorCode, String title, String message) {
        CustomBaseResponse<Void> response = new CustomBaseResponse<>();
        response.setTitle(title);
        response.setMessage(message);
        response.setErrorCode(errorCode);
        return response;
    }
} 