package top.mc114.groupadmin;

import kotlinx.coroutines.Dispatchers;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.GroupMessage;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageUtils;

import javax.naming.ldap.Control;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class GroupMessageListener implements Consumer <GroupMessage> {
    @Override
    public void accept(GroupMessage event) {
        //System.out.println(event.getGroup().getBotPermission().getLevel());//1=Administrator,0=Member
        if(event.getGroup().getBotPermission().getLevel()==0||event.getSender().getPermission().getLevel()>0) {
            //System.out.println("Permission denied!");
            return;
        }
        String msg = event.getMessage().toString();
        List<String> list = new ArrayList<>();
        Collections.addAll(list,"ddos","傻逼","翻墙","vpn",
                "习近平","ssr","v2ray","社工","人肉","梯子","脑瘫","你妈");
        for(int a=0;a<list.size();a++) {
            if(event.getMessage().toString().toLowerCase().contains(list.get(a))) {
                event.getBot().recall(event.getMessage());
                int time = 600;
                event.getSender().muteAsync(time);
                event.getGroup().sendMessage(MessageUtils.newChain(new At(event.getSender()))
                        .plus("你使用了违禁词，你因此被禁言"+time+"秒，请注意自己的言行！"));
                return;
            }
        }
    }
}
