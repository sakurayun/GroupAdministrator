package shrbox.github.groupadmin;

import net.mamoe.mirai.event.events.MemberLeaveEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageUtils;

import java.util.function.Consumer;

public class GAGroupMemberLeave implements Consumer <MemberLeaveEvent> {
    @Override
    public void accept(MemberLeaveEvent event) {
        //Image img = event.getGroup().uploadImage(new URL(event.getMember().getAvatarUrl()));
        event.getGroup().sendMessage(MessageUtils.newChain(new At(event.getMember()))
                .plus("["+event.getMember().getId()+"]离开了本群。"));
    }
}
