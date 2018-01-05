package jtli.com.simplereader.http;

import rx.Subscription;

public interface LifeSubscription {
    void bindSubscription(Subscription subscription);
}

