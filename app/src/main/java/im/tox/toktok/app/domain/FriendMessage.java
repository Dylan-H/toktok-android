package im.tox.toktok.app.domain;

public final class FriendMessage implements ChatMessage {
    public  Friend friend;
    public  String lastMessage;
    public  int type;
    public FriendMessage(
            Friend friend,
            String lastMessage,
            int type
    ) {
        this.friend = friend;
        this.lastMessage = lastMessage;
        this.type =type;
    }
    @Override
    public int messageType() {
        return 0;
    }
}
