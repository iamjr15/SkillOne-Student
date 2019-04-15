package com.wizy.android.student.keyboard;

import android.view.ViewTreeObserver;


public interface Unregistrar {

    /**
     * unregisters the {@link ViewTreeObserver.OnGlobalLayoutListener} and there by does
     * provide any more callback to the {@link KeyboardVisibilityEventListener}
     */
    void unregister();

}

