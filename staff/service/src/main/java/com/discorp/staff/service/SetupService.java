package com.discorp.staff.service;

import com.discorp.staff.xml.InformationResponse;
import com.discorp.staff.xml.InvationCodeResponse;
import com.discorp.staff.xml.SetupRequest;
import com.discorp.staff.xml.SetupResponse;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/17/12
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SetupService {
    public SetupResponse setupStaflink(SetupRequest setupRequest);

    public InvationCodeResponse checkValidateInvitationCode(String invationCode);

    public InformationResponse getInformation();
}
