package com.neo.ioc.overview.entity;

import com.neo.ioc.overview.anno.Vip;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Vip
@Data
@ToString(callSuper = true)
public class SupUser extends User {
    private int level;


}

