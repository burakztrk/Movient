package com.ozturkburak.movient.controller;

import com.ozturkburak.Utils.Util;
import com.ozturkburak.movient.model.Movie;
import com.ozturkburak.movient.search.SearchOptions;

/**
 * Created by burak on 5/16/17.
 */

public interface IFragmentCommunicator
{
    public void sendData(Util.PAGES whichPage , Object obj);
}
