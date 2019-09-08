package im.tox.toktok.app.event;

import im.tox.jtoxcore.ToxConnection;

public class ConnectionStatusEvent {
        public  ToxConnection connectionStatus;
        public  ConnectionStatusEvent(ToxConnection status){
            connectionStatus = status;
        }
}
