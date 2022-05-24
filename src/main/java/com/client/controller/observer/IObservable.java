package com.client.controller.observer;

import java.awt.*;

public interface IObservable {

    void notifyObservers();

    void  addUserInformation(IObserverText observerText);
}
