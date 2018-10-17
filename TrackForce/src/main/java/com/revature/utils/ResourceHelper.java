package com.revature.utils;

import com.revature.entity.TfAssociate;
import io.jsonwebtoken.Claims;

public class ResourceHelper {

	private ResourceHelper() {}

	public static boolean isPayloadAssociate(Claims payload, TfAssociate associateInfo) {
		return (((String) payload.get("roleID")).equals("5") && !payload.getSubject().equals(associateInfo.getUser().getUsername()));
	}
}