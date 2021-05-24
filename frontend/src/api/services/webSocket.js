import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
    connect() {
        let connected = false;
        this.socket = new SockJS("http://localhost:8088/gs-guide-websocket");
        this.stompClient = Stomp.over(this.socket);
        this.stompClient.connect(
            {"Access-Control-Allow-Origin": "*"},
            () => {
                connected = true;
            },
            error => {
                console.log(error);
                connected = false;
            }
        );
        return connected;
    },
    checkIn(manufacturer, model, customer) {
        if (this.stompClient && this.stompClient.connected) {
            const msg = {
                carManufacturer: manufacturer,
                carModel: model,
                customerName: customer,
            };
            this.stompClient.send("/app/return", JSON.stringify(msg), {});
        }
        else{
            console.log("Not connected");
        }
    }
}