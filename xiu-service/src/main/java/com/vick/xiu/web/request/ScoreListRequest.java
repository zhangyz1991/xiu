package com.vick.xiu.web.request;

import com.vick.framework.page.PageRequest;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zyz
 * @since 2019-12-03
 */
@Getter
@Setter
public class ScoreListRequest extends PageRequest implements Serializable {

    private static final long serialVersionUID = -637444225077067703L;
}
