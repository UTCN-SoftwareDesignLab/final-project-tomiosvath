<template>
    <v-card>
        <v-card-title>
            Customers
            <v-spacer></v-spacer>
            <v-text-field
                    v-model="search"
                    append-icon="mdi-magnify"
                    label="Search"
                    single-line
                    hide-details
            ></v-text-field>
            <v-btn @click="addCustomer">Add Customer</v-btn>
            <v-btn @click="switchViewCars">Switch to cars</v-btn>
            <v-btn @click="switchViewRentals">Switch to rentals</v-btn>
        </v-card-title>
        <v-data-table
                :headers="headers"
                :items="customers"
                :search="search"
                @click:row="editCustomer"
        ></v-data-table>
        <CustomerDialog
                :opened="dialogVisible"
                :customer="selectedCustomer"
                @refresh="refreshList"
        ></CustomerDialog>
    </v-card>
</template>

<script>
    import api from "../api";
    import router from "../router";
    import CustomerDialog from "../components/CustomerDialog";
    
    export default {
        name: "CustomerList",
        components: { CustomerDialog},
        data(){
            return {
                customers: [],
                search: "",
                headers: [
                    {
                        text: "Name",
                        align: "start",
                        sortable: false,
                        value: "name",
                    },
                    { text: "Driving license number", value: "licenseNb" },
                    { text: "Personal Code", value: "personalNb" },
                    { text: "Phone number", value: "phoneNb" },
                    { text: "Address", value: "address" },
                ],
                dialogVisible: false,
                selectedCustomer: {},
                connected: false,

            };
        },
        methods: {
            editCustomer(customer) {
                this.selectedCustomer = customer;
                this.dialogVisible = true;
            },
            addCustomer() {
                this.dialogVisible = true;
            },
            async refreshList() {
                this.dialogVisible = false;
                this.selectedCustomer = {};
                this.customers = await api.customers.allCustomers();
            },
            switchViewCars(){
                router.push("/cars");
            },
            switchViewRentals(){
                router.push("/rentals");
            }
        },
        created() {
            this.refreshList();
        },
    };
</script>

<style scoped>

</style>