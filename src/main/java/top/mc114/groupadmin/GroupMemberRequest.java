package top.mc114.groupadmin;

import net.mamoe.mirai.event.events.MemberJoinRequestEvent;
import net.mamoe.mirai.message.data.Image;

import java.net.URL;
import java.util.function.Consumer;

public class GroupMemberRequest implements Consumer <MemberJoinRequestEvent> {
    @Override
    public void accept(MemberJoinRequestEvent event) {
        String msg = event.getMessage().toLowerCase();
        if(msg.contains("bedrockx")||msg.contains("bdx")) {
            event.accept();
            event.getGroup().sendMessage(event.getFromNick()+"["+event.getFromId()+"]经过Bot的审核加入了本群。");
        }
    }
}
