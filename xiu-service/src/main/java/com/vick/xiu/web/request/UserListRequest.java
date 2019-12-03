package com.vick.xiu.web.request;

import com.vick.framework.page.PageRequest;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zyz
 * @since 2019-11-11
 */
@Getter
@Setter
public class UserListRequest extends PageRequest implements Serializable {
    
    private static final long serialVersionUID = 1197978498608930962L;
}
