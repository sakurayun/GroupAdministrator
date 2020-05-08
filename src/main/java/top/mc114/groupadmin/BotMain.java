package top.mc114.groupadmin;

import net.mamoe.mirai.console.plugins.Config;
import net.mamoe.mirai.console.plugins.PluginBase;
import net.mamoe.mirai.event.events.MemberJoinEvent;
import net.mamoe.mirai.event.events.MemberJoinRequestEvent;
import net.mamoe.mirai.event.events.MemberLeaveEvent;
import net.mamoe.mirai.message.GroupMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BotMain extends PluginBase {
    public static List<String> list = new ArrayList<>();

    public void onLoad() {
        Collections.addAll(list,"ddos","傻逼","翻墙","vpn",
                "习近平","ssr","v2ray","社工","人肉","梯子","脑瘫","你妈");
    }

    public void onEnable() {
        this.getEventListener().subscribeAlways(GroupMessage.class, new GroupMessageListener());
        this.getEventListener().subscribeAlways(MemberJoinEvent.class, new GroupMemberJoinListener());
        this.getEventListener().subscribeAlways(MemberLeaveEvent.class, new GroupMemberLeaveListener());
        this.getEventListener().subscribeAlways(MemberJoinRequestEvent.class, new GroupMemberRequest());
    }
}