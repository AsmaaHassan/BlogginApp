package com.example.blogapp.data.remote;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Asmaa Hassan
 */
@IntDef({ErrorType.NETWORK, ErrorType.HTTP, ErrorType.UNEXPECTED, ErrorType.AUTH_ERROR})
@Retention(RetentionPolicy.RUNTIME)
public @interface ErrorType {
    int NETWORK = 1;
    int HTTP = 2;
    int UNEXPECTED = 3;
    int AUTH_ERROR = 4;
}
