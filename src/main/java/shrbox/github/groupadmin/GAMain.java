package shrbox.github.groupadmin;

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

public class GAMain extends PluginBase {
    private static GAMain plugin;
    public static GAMain getPlugin() {
        return plugin;
    }

    public static Config config_file;
    public static Config key_words_file;

    public static void startLoadConfig() {
        config_file = getPlugin().loadConfig("config.yml");//加载config
        key_words_file = getPlugin().loadConfig("key_words.yml");

        List<String> temp_key_words_list = new ArrayList<>();//新建一个临时list
        Collections.addAll(temp_key_words_list,"傻逼","脑瘫","你妈");
        key_words_file.setIfAbsent("key_words", temp_key_words_list);//如果config不存在，则将临时list内容导入config

        List<String> temp_accept_words_list = new ArrayList<>();//新建一个临时list
        Collections.addAll(temp_accept_words_list, "114514", "1919810");
        config_file.setIfAbsent("accept_words", temp_accept_words_list);//如果config不存在，则将临时list内容导入config
        config_file.setIfAbsent("EnableLeaveMessage", false);
        config_file.setIfAbsent("EnableAuditRequests", false);

        config_file.save();
        key_words_file.save();

        temp_accept_words_list.clear();//clear list
        temp_key_words_list.clear();//clear list
    }

    public void onLoad() {}

    public void onEnable() {
        plugin = this;
        startLoadConfig();
        this.getEventListener().subscribeAlways(GroupMessageEvent.class, new GAGroupMessage());
        if(config_file.getBoolean("EnableLeaveMessage")) {//发送群员退群消息开关
            this.getEventListener().subscribeAlways(MemberLeaveEvent.class, new GAGroupMemberLeave());
        }
        if(config_file.getBoolean("EnableAuditRequests")) {//自动同意加群请求开关
            this.getEventListener().subscribeAlways(MemberJoinRequestEvent.class, new GAGroupMemberJoinRequests());
        }
        JCommandManager.getInstance().register(this, new BlockingCommand( //注册command
                "gareload", new ArrayList<>(),"重载GroupAdministrator配置文件","/gareload"
        ) {
            @Override
            public boolean onCommandBlocking(@NotNull CommandSender commandSender, @NotNull List<String> list) {
                startLoadConfig();
                commandSender.sendMessageBlocking("重载成功");
                return true;
            }
        });
    }
}