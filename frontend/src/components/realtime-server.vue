<template>
  <div>
    <div class="dot connected" v-if="connected"></div>
    <div class="dot disconnected" v-else @click="connect"></div>
  </div>
</template>

<script>
  import SockJS from "sockjs-client";
  import Stomp from "webstomp-client";

  export default {
    name: "realtime-server",
    data() {
      return {
        connected: false
      }
    },
    methods: {
      connect() {
        if (!this.connected) {
          this.socket = new SockJS("/api/ws-endpoint");
          this.stompClient = Stomp.over(this.socket);
          this.stompClient.connect(
            {},
            () => {
              this.connected = true;
              this.stompClient.subscribe("/topic/result", tick => {
                this.received_messages.push(JSON.parse(tick.body).content);
              });
            },
            () => {
              this.connected = false;
            }
          );
        }
      },
    },
    mounted() {
      this.$nextTick(this.connect())
    }
  }
</script>

<style scoped>

  @keyframes pulse {
    0% {
      box-shadow: 0 0 8px #66BB6A, inset 0 0 8px #66BB6A;
    }
    50% {
      box-shadow: 0 0 16px #66BB6A, inset 0 0 14px #66BB6A;
    }
    100% {
      box-shadow: 0 0 8px #66BB6A, inset 0 0 8px #66BB6A;
    }
  }

  @keyframes flash {
    0% {
      background-color: #d32f2f;
    }
    50% {
      background-color: #ef5350;
    }
  }

  .dot {
    height: 15px;
    width: 15px;
    border-radius: 50%;
    display: inline-block;
    margin-right: 1em;
    margin-top: 5px;
  }

  .connected {
    background-color: #388E3C;
    box-shadow: 0 0 8px #66BB6A, inset 0 0 8px #66BB6A;
    animation: pulse 2s linear 1s infinite;
  }

  .disconnected {
    background-color: #d32f2f;
    animation: flash 0.5s steps(1) infinite;
  }
</style>