package top.mc114.groupadmin;

import net.mamoe.mirai.event.events.MemberJoinRequestEvent;

import java.util.List;
import java.util.function.Consumer;

public class GroupMemberRequest implements Consumer <MemberJoinRequestEvent> {
    List<String> list;
    @Override
    public void accept(MemberJoinRequestEvent event) {
        list = BotMain.accept_list;
        for(int a=0;a<list.size();a++) {
            if(event.getMessage().toLowerCase().contains(list.get(a))) {
                event.accept();
                event.getGroup().sendMessage(event.getFromNick()+"["+event.getFromId()+"]经过Bot的审核加入了本群。");
                return;
            }
        }
    }
}