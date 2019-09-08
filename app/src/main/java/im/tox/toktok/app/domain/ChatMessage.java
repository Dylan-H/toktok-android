package im.tox.toktok.app.domain;

public interface ChatMessage {
    int messageType();

    ChatMessage loremMessage = new FriendMessage(Friend.lorem, "Hello, how are you?",1);
    ChatMessage johnMessage = new FriendMessage(Friend.john, "Hey buddy, how's things?",1);
    ChatMessage groupMessage = new GroupMessage(Group.group, "Let's Go!");
}
