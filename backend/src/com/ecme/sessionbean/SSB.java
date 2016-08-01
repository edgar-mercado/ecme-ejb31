package com.ecme.sessionbean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class SSB
 */
@Stateless(mappedName = "ssb")
@LocalBean
public class SSB implements SSBRemote {

    /**
     * Default constructor. 
     */
    public SSB() {
        // TODO Auto-generated constructor stub
    }

}
