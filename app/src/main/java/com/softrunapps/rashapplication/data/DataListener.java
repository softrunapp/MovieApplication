package com.softrunapps.rashapplication.data;

public interface DataListener<T> {

    void onResponse(T response);

    void onError(String message);
}
