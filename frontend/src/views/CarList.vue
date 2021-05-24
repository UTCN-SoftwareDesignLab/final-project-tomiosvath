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
            <v-btn @click="addCar">Add Car</v-btn>
            <v-btn @click="switchViewCustomers">Switch to customers</v-btn>
            <v-btn @click="switchViewRentals">Switch to rentals</v-btn>

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

    export default {
        name: "CarList",
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
                    { text: "Car's renting price per day (RON)", value: "price"},
                    { text: "Is rented", value: "rented"},
                ],
                dialogVisible: false,
                selectedCar: {},
                connected: false,
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
            switchViewCustomers(){
                router.push("/customers");
            },
            switchViewRentals(){
                router.push("/rentals");
            },
            addCar(){
                this.dialogVisible = true;
            }
        },
        created() {
            this.refreshList();
        },
    }
</script>

<style scoped>

</style>