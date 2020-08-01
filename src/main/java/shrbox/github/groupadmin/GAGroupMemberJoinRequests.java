package shrbox.github.groupadmin;

import net.mamoe.mirai.event.events.MemberJoinRequestEvent;

import java.util.function.Consumer;

public class GAGroupMemberJoinRequests implements Consumer<MemberJoinRequestEvent> {
    @Override
    public void accept(MemberJoinRequestEvent event) {
        for (Long l : GAMain.config_file.getLongList("accept_requests_groups")) {
            if (event.getGroup().getId() == l) {
                for (String s : GAMain.config_file.getStringList("accept_words")) {
                    if (event.getMessage().toLowerCase().contains(s)) {
                        event.accept();
                        break;
                    }
                }
            }
        }
    }
}