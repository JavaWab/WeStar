package com.westar.wab.common.response;

import com.westar.wab.common.reources.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * CustomReSponse
 *
 * @author Anbang Wang
 * @date 2016/11/4
 */
public class OKReSponse extends ResponseEntity<Map> {
    private static Map<String, Object> map = new HashMap<String, Object>();
    static {
        map.put("code", Resources.OK);
        map.put("message", Resources.SUCCESSFUL);

    }

    public OKReSponse() {
        super(map, HttpStatus.OK);
    }
}
