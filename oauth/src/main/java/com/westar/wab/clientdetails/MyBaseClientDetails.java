package com.westar.wab.clientdetails;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

/**
 * Created by ouyasukuni on 2016/10/31.
 */
@Document(collection = "baseClientDetails")
public class MyBaseClientDetails extends BaseClientDetails {
}
