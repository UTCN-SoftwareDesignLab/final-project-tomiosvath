<template>
  <v-card>
    <v-card-title>
      Users
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-btn @click="addUser">Add user</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="users"
      :search="search"
      @click:row="editUser"
    ></v-data-table>
    <UserDialog
            :opened="dialogVisible"
            :user="selectedUser"
            @refresh="refreshList"
    ></UserDialog>
  </v-card>
</template>

<script>
import api from "../api";
import UserDialog from "../components/UserDialog";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
  name: "UserList",
  components: { UserDialog },
  data() {
    return {
      users: [],
      search: "",
      headers: [
        {
          text: "Username",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "Email", value: "email" },
        { text: "Roles", value: "roles" },
      ],
      dialogVisible: false,
      selectedUser: {},
    };
  },
  methods: {
    connectAndSubscribe() {
      this.socket = new SockJS("http://localhost:8088/gs-guide-websocket");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
              {"Access-Control-Allow-Origin": "*"},
              () => {
                this.connected = true;
                this.stompClient.subscribe("/admin/return", tick => {
                  let response = JSON.parse(tick.body);
                    this.$alert(response.message);
                }, {"Access-Control-Allow-Origin": "*"});
              },
              error => {
                console.log(error);
                this.connected = false;
              }
      );
    },
    editUser(user) {
      this.selectedUser = user;
      this.dialogVisible = true;
    },
    addUser() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedUser = {};
      this.users = await api.users.allUsers();
    },
  },
  created() {
    this.refreshList();
    this.connectAndSubscribe();
  },
};
</script>

<style scoped></style>
