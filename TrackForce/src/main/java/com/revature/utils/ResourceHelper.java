package com.revature.utils;
import com.revature.entity.TfAssociate;
import io.jsonwebtoken.Claims;

//UNUSED CLASS??
class ResourceHelper {
	private ResourceHelper() {}

	//UNUSED??
	public static boolean isPayloadAssociate(Claims payload, TfAssociate associateInfo)
    { return (payload.getId().equals("5") && !payload.getSubject().equals(associateInfo.getUser().getUsername()));}
}