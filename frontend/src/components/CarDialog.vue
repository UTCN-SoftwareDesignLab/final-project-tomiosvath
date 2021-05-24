<template>
    <v-dialog
            transition="dialog-bottom-transition"
            max-width="900"
            :value="opened"
    >
        <template>
            <v-card>
                <v-toolbar color="primary" dark>
                    {{ isNew ? "Create Car" : "Operations" }}
                </v-toolbar>
                <v-form>
                    <v-text-field v-model = "car.manufacturer" label = "Car's manufacturer" />
                    <v-text-field v-model = "car.model" label = "Car's model" />
                    <v-text-field v-model = "car.year" label = "Manufacture year" />
                    <v-text-field v-model = "car.location" label = "Car's location" />
                    <v-text-field v-model = "car.price" label = "Car's renting price per day (RON)" />

                    <p v-if="myImage.length > 0"><img v-bind:src="myImage" ></p>
                    <input type = "file" accept = "image/*" @change="upload($event)" id="file-input">

                    <v-text-field v-model = "car.customerName" label = "Customer's name" />
                    <v-text-field v-model = "car.cnp" label = "Customer's CNP" />
                    <v-text-field v-model = "car.from" label = "From" />
                    <v-text-field v-model = "car.until" label = "Until" />


                </v-form>
                <v-card-actions>
                    <v-btn @click="persist">
                    {{ isNew ? "Create" : "Save" }}
                    </v-btn>
                    <v-btn @click="deleteCar">Delete car</v-btn>
                    <v-btn @click="showCar">Show car</v-btn>
                    <v-btn @click="rentCar">Rent car</v-btn>
                    <v-btn @click="returnCar">Return car</v-btn>
                </v-card-actions>
            </v-card>
        </template>
    </v-dialog>
</template>

<script>
    import api from "../api";
    import axios from "axios";
    import authHeader from "../api/http";

    export default {
        name: "CarDialog",
        props: {
            car: Object,
            rental: Object,
            opened: Boolean,
        },
        data(){
            return{
                myImage: "",
                connected: false,
            }
        },
        methods: {
            persist() {
                if (this.isNew) {
                    api.cars
                        .create({
                            manufacturer: this.car.manufacturer,
                            model: this.car.model,
                            year: this.car.year,
                            location: this.car.location,
                            price: this.car.price,
                        })
                        .then(() => this.$emit("refresh"));
                } else {
                    api.cars
                        .edit({
                            id: this.car.id,
                            manufacturer: this.car.manufacturer,
                            model: this.car.model,
                            year: this.car.year,
                            location: this.car.location,
                            price: this.car.price,
                        })
                        .then(() => this.$emit("refresh"));
                }
            },
            rentCar() {
                api.rentals.rent({
                    carId: this.car.id,
                    customerName: this.car.customerName,
                    cnp: this.car.cnp,
                    from: this.car.from,
                    until: this.car.until,
                    location: this.car.location,
                })
            },
            returnCar(){
                api.cars.returnCar({
                    id: this.car.id,
                });
                api.webSocket.checkIn(this.car.manufacturer, this.car.model, this.car.customerName);
            },
            deleteCar(){
                api.cars.delete({
                    id: this.car.id,
                })
                    .then(() => this.$emit("refresh"));
            },
            showCar: function (){
              this.myImage = 'data:image/jpeg;base64,' + this.car.picByte;
            },
            upload(event) {
                const URL = "http://localhost:8088/api/cars"

                const data = new FormData();
                data.append('manufacturer', this.car.manufacturer);
                data.append('model', this.car.model);
                data.append('year', this.car.year);
                data.append('location', this.car.location);
                data.append('price', this.car.price);
                data.append('imageFile', event.target.files[0], event.target.files[0].name)

                axios.post(
                    URL,
                    data,
                    {headers: authHeader()}
                ).then(
                    response => {
                        console.log('image upload response > ', response)
                    }
                )
            },
        },
        computed: {
            isNew: function () {
                return !this.car.id;
            },
        },
        created() {
            this.connected = api.webSocket.connect();
        },
    }
</script>

<style scoped>

</style>