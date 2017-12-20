package com.codechallenge.badrobot.bitcoinstats.utils;


import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.IntDef;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MessageWrapper {

    public static final int LENGTH_INDEFINITE = Snackbar.LENGTH_INDEFINITE;

    public static final int LENGTH_SHORT = Snackbar.LENGTH_SHORT;

    public static final int LENGTH_LONG = Snackbar.LENGTH_LONG;

    @IntDef({LENGTH_INDEFINITE, LENGTH_SHORT, LENGTH_LONG})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {}

    enum Type {
        TOAST,
        SNACKBAR
    }

    private int mTitleId = -1;

    private int mMessageId = -1;

    private String mTitle = null;

    private String mMessage = null;

    private Type mType = Type.TOAST;

    private int mDuration;

    private MessageWrapper() {}

    public static MessageWrapper withSnackBar(int messageId) {
        return withSnackBar(messageId, LENGTH_SHORT);
    }

    public static MessageWrapper withSnackBar(int messageId, @Duration int duration) {
        MessageWrapper messageWrapper = new MessageWrapper();
        messageWrapper.mMessageId = messageId;
        messageWrapper.mType = Type.SNACKBAR;
        messageWrapper.mDuration = duration;
        return messageWrapper;
    }

    public static MessageWrapper withToast(int messageId, @Duration int duration) {
        MessageWrapper messageWrapper = new MessageWrapper();
        messageWrapper.mMessageId = messageId;
        messageWrapper.mType = Type.TOAST;
        messageWrapper.mDuration = duration == LENGTH_LONG ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
        return messageWrapper;
    }

    public int getTitleId() {
        return mTitleId;
    }

    public int getMessageId() {
        return mMessageId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getMessage() {
        return mMessage;
    }

    public Type getType() {
        return mType;
    }

    public int getDuration() {
        return mDuration;
    }
}
