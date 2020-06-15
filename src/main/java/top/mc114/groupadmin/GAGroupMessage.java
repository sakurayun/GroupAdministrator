package top.mc114.groupadmin;

import net.mamoe.mirai.message.GroupMessage;
import net.mamoe.mirai.message.GroupMessageEvent;

import java.util.List;
import java.util.function.Consumer;

public class GAGroupMessage implements Consumer <GroupMessageEvent> {
    List<String> list;
    @Override
    public void accept(GroupMessageEvent event) {
        //1=Administrator,0=Member
        if(event.getGroup().getBotPermission().getLevel()==0||event.getSender().getPermission().getLevel()>0) {
            return;
        }
        list = BotMain.key_list;
        for(int a=0;a<list.size();a++) {
            if(event.getMessage().toString().toLowerCase().contains(list.get(a))) {
                event.getBot().recall(event.getMessage());
                int time = 600;
                event.getSender().muteAsync(time);
                return;
            }
        }
    }
}
