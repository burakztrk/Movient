package com.ozturkburak.movient.controller;

import com.ozturkburak.Utils.Util;

/**
 * Created by burak on 5/16/17.
 */

public interface IFragmentCommunicator
{
    public void sendData(Util.APP_PAGES whichPage , Object obj);
}
