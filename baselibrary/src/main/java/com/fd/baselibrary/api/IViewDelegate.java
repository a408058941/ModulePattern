package com.fd.baselibrary.api;

import android.support.annotation.Keep;
import android.view.View;

import com.fd.baselibrary.base.BaseFragment;

@Keep
public interface IViewDelegate {
    BaseFragment getFragment(String name);

    View getView(String name);

}
