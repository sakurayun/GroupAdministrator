package top.mc114.groupadmin;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.MemberLeaveEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Consumer;

public class GroupMemberLeaveListener implements Consumer <MemberLeaveEvent> {
    @Override
    public void accept(MemberLeaveEvent event) {
        //Image img = event.getGroup().uploadImage(new URL(event.getMember().getAvatarUrl()));
        event.getGroup().sendMessage(MessageUtils.newChain(new At(event.getMember()))
                .plus("["+event.getMember().getId()+"]离开了本群。"));
    }
}
