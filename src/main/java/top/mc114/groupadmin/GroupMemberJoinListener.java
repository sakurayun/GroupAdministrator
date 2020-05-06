package top.mc114.groupadmin;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.MemberJoinEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Consumer;

public class GroupMemberJoinListener implements Consumer <MemberJoinEvent> {
    @Override
    public void accept(MemberJoinEvent event) {
        try {
            Image img = event.getGroup().uploadImage(new URL(event.getMember().getAvatarUrl()));
            event.getGroup().sendMessage(MessageUtils.newChain(img)
                    .plus(new At(event.getMember()))
                    .plus("["+event.getMember().getId()+"]加入了本群。")
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
