package com.kv.service;

import com.kv.proto.NodeOuterClass;
import org.springframework.web.bind.annotation.*;

/*
    CAP: 一致性，可用性和网络分区
    当发生网络分区，一般无法再兼容一致性和可用性。如果允许网络分区，必然要牺牲一致性和可用性。kafka是如何做的？



 */
@RestController
public class RecvController {
    @PutMapping("/kv/store")
    public void put(@RequestBody NodeOuterClass.Node request) {

    }

    /*
        注意，这里使用了相同的路径，但是请求方法不相同，这是可以的。@RequestMapping注解会将方法映射为
        @RequestMappingInfo对象，只要不满足对象equals的条件，就是可以重复的
     */
    @PutMapping("/kv/store/{key}")
    public NodeOuterClass.Node get(@PathVariable String key) {
        return null;
    }

    @DeleteMapping("/kv/store/{key}")
    public void delete(@PathVariable String key){

    }
}
