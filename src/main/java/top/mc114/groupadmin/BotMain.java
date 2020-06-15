package top.mc114.groupadmin;

import net.mamoe.mirai.console.command.BlockingCommand;
import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.JCommandManager;
import net.mamoe.mirai.console.plugins.Config;
import net.mamoe.mirai.console.plugins.PluginBase;
import net.mamoe.mirai.event.events.MemberJoinRequestEvent;
import net.mamoe.mirai.event.events.MemberLeaveEvent;
import net.mamoe.mirai.message.GroupMessageEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BotMain extends PluginBase {
    Config config;
    public static List<String> key_list;
    public static List<String> accept_list;
    public static boolean onLeaveMsg;
    public static boolean onAuditRequests;
    public void StartLoadConfig() {
        config = loadConfig("config.yml");//加载config
        List<String> keyword_list = new ArrayList<>();//新建一个临时list
        Collections.addAll(keyword_list,"傻逼","脑瘫","你妈");
        config.setIfAbsent("key_words", keyword_list);//如果config不存在，则将临时list内容导入config

        List<String> acceptword_list = new ArrayList<>();//新建一个临时list
        Collections.addAll(acceptword_list, "114514", "1919810");
        config.setIfAbsent("accept_words", acceptword_list);//如果config不存在，则将临时list内容导入config
        config.setIfAbsent("EnableLeaveMessage", false);
        config.setIfAbsent("EnableAuditRequests", false);
        config.save();
        acceptword_list.clear();//clear list
        keyword_list.clear();//clear list
        onLeaveMsg = config.getBoolean("EnableLeaveMessage");
        onAuditRequests = config.getBoolean("EnableAuditRequests");
        key_list = config.getStringList("key_words");
        accept_list = config.getStringList("accept_words");
    }
    public void onLoad() {
        StartLoadConfig();
    }

    public void onEnable() {
        this.getEventListener().subscribeAlways(GroupMessageEvent.class, new GAGroupMessage());
        if(onLeaveMsg) {//发送群员退群消息开关
            this.getEventListener().subscribeAlways(MemberLeaveEvent.class, new GAGroupMemberLeave());
        }
        if(onAuditRequests) {//自动同意加群请求开关
            this.getEventListener().subscribeAlways(MemberJoinRequestEvent.class, new GAGroupMemberJoinRequests());
        }
        JCommandManager.getInstance().register(this, new BlockingCommand( //注册command
                "gareload", new ArrayList<>(),"重载GroupAdministrator配置文件","/gareload"
        ) {
            @Override
            public boolean onCommandBlocking(@NotNull CommandSender commandSender, @NotNull List<String> list) {
                StartLoadConfig();
                commandSender.sendMessageBlocking("重载成功");
                return true;
            }
        });
    }
}