package com.softrunapps.rashapplication.base;

public class Presenter<T extends Presenter.View> {
    private T view;

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public interface View {
        default void showMessage(String message){}

        default void showLoading(){}

        default void hideLoading(){}
    }
}
