package top.mc114.groupadmin;

import net.mamoe.mirai.console.plugins.PluginBase;
import net.mamoe.mirai.event.events.MemberJoinEvent;
import net.mamoe.mirai.event.events.MemberJoinRequestEvent;
import net.mamoe.mirai.event.events.MemberLeaveEvent;
import net.mamoe.mirai.message.GroupMessage;

class BotMain extends PluginBase {
    public void onLoad() {
        getLogger().info("Plugin loaded!");
    }

    public void onEnable() {
        getLogger().info("Plugin enabled!");
        this.getEventListener().subscribeAlways(GroupMessage.class, new GroupMessageListener());
        this.getEventListener().subscribeAlways(MemberJoinEvent.class, new GroupMemberJoinListener());
        this.getEventListener().subscribeAlways(MemberLeaveEvent.class, new GroupMemberLeaveListener());
        this.getEventListener().subscribeAlways(MemberJoinRequestEvent.class, new GroupMemberRequest());
    }
}