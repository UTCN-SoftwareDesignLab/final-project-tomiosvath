<template>
    <v-card>
        <v-card-title>
            Rentals
            <v-spacer></v-spacer>
            <v-text-field
                    v-model="search"
                    append-icon="mdi-magnify"
                    label="Search"
                    single-line
                    hide-details
            ></v-text-field>
            <v-btn @click="switchViewCustomers">Switch to customers</v-btn>
            <v-btn @click="switchViewCars">Switch to cars</v-btn>
        </v-card-title>
        <v-data-table
                :headers="headers"
                :items="rentals"
                :search="search"
        ></v-data-table>
    </v-card>
</template>

<script>
    import api from "../api";
    import router from "../router"

    export default {
        name: "RentalList",
        data(){
            return {
                rentals: [],
                search: "",
                headers: [
                    {
                        text: "Customer",
                        align: "start",
                        sortable: false,
                        value: "customer",
                    },
                    { text: "Manufacturer", value: "manufacturer" },
                    { text: "Model", value: "model" },
                    { text: "Rented from", value: "from" },
                    { text: "Rented until", value: "until" },
                    { text: "Location", value: "location" },
                    { text: "Car's renting price per day (RON)", value: "price"},
                ],
                dialogVisible: false,
                connected: false,
            };
        },
        methods: {
            async refreshList() {
                this.dialogVisible = false;
                this.rentals = await api.rentals.allRentals();
            },
            switchViewCustomers(){
                router.push("/customers");
            },
            switchViewCars(){
                router.push("/cars");
            },
        },
        created() {
            this.refreshList();
        },
    }
</script>

<style scoped>

</style>