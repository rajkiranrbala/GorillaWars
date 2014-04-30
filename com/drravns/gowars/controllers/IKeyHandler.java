package com.drravns.gowars.controllers;

public interface IKeyHandler {
    public void handleKey(String key);

    public void setNext(IKeyHandler handler);
}