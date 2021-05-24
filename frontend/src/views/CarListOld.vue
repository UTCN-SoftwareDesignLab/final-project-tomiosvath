<template>
    <v-card>
        <v-card-title>
            Cars
            <v-spacer></v-spacer>
            <v-text-field
                    v-model="search"
                    append-icon="mdi-magnify"
                    label="Search"
                    single-line
                    hide-details
            ></v-text-field>
            <p v-if="myImage.length > 0"><img v-bind:src="myImage"></p>
            <v-btn @click="myFunction">Show Image</v-btn>
            <v-btn @click="addCar">Add Car</v-btn>
            <input type = "file" accept = "image/*" @change="upload($event)" id="file-input">
            <input v-model="imageName" placeholder="filename">
            <v-btn @click="download">Download Pic</v-btn>
            <v-btn @click="switchView">Switch to customers</v-btn>

        </v-card-title>
        <v-data-table
                :headers="headers"
                :items="cars"
                :search="search"
                @click:row="editCar"
        ></v-data-table>
        <CarDialog
                :opened="dialogVisible"
                :car="selectedCar"
                @refresh="refreshList"
        ></CarDialog>
    </v-card>
</template>



<script>
    import api from "../api";
    import router from "../router"
    import CarDialog from "../components/CarDialog";
    import axios from "axios";

    export default {
        name: "CarListOld",
        components: { CarDialog},
        data(){
            return {
                cars: [],
                search: "",
                headers: [
                    {
                        text: "Manufacturer",
                        align: "start",
                        sortable: false,
                        value: "manufacturer",
                    },
                    { text: "Model", value: "model" },
                    { text: "Manufacturing year", value: "year" },
                    { text: "Location", value: "location" },
                ],
                dialogVisible: false,
                selectedCar: {},
                connected: false,
                imageName: "",
                retrievedImage: "",
                isDownloaded: false,
                myImage:'',
            };
        },
        methods: {
            editCar(car) {
                this.selectedCar = car;
                this.dialogVisible = true;
            },
            async refreshList() {
                this.dialogVisible = false;
                this.selectedCar = {};
                this.cars = await api.cars.allCars();
            },
            upload(event) {
                const URL = "http://localhost:8088/api/images/upload"

                const data = new FormData();
                data.append('imageFile', event.target.files[0], event.target.files[0].name)

                axios.post(
                    URL,
                    data,
                    {}
                ).then(
                    response => {
                        console.log('image upload response > ', response)
                    }
                )
            },
            download(){
                axios.get(
                    "http://localhost:8088/api/images/get/" + this.imageName,
                ).then(
                    response => {
                        //console.log(response.data.picByte);
                        this.retrievedImage = 'data:image/jpeg;base64,' + response.data.picByte;
                        this.isDownloaded = true;
                    }
                )
            },
            switchView(){
                router.push("/customers");
            },
            myFunction: function () {
                this.myImage = "https://www.tutorialsplane.com/wp-content/uploads/2018/02/27867786_1714460465242017_6847995972742989230_n.jpg";
                this.myImage = this.retrievedImage;
            },
            addCar(){
                this.dialogVisible = true;
            }
        },
        computed : {
            retrieve() {
                return this.retrievedImage;
            }
        },
        created() {
            this.refreshList();
        },
    }
</script>

<style scoped>

</style>