package shrbox.github.groupadmin;

import net.mamoe.mirai.message.GroupMessageEvent;
import java.util.function.Consumer;

public class GAGroupMessage implements Consumer <GroupMessageEvent> {
    @Override
    public void accept(GroupMessageEvent event) {
        //1=Administrator,0=Member
        if(event.getGroup().getBotPermission().getLevel()==0||event.getSender().getPermission().getLevel()>0) {
            return;
        }
        for (String s : GAMain.key_words_file.getStringList("key_words")) {
            if (event.getMessage().toString().toLowerCase().contains(s)) {
                event.getBot().recall(event.getMessage());
                int time = 600;
                event.getSender().muteAsync(time);
                break;
            }
        }
    }
}
